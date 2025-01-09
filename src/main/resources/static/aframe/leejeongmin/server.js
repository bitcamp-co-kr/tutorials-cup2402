const express = require('express');
const mongoose = require('mongoose');
const path = require('path');
const app = express();

// 미들웨어 설정을 상단으로 이동
app.use(express.json());
app.use(express.static(path.join(__dirname)));

// MongoDB 연결 및 에러 핸들링 추가
mongoose.connect('mongodb://localhost:27017/hackerton', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => console.log('MongoDB Connected...'))
.catch(err => console.log('MongoDB Connection Error:', err));

// MongoDB 연결 이벤트 리스너 추가
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    console.log("Database connected successfully");
});

// Player 스키마 수정
const playerSchema = new mongoose.Schema({
    name: { 
        type: String, 
        required: true,
        unique: false  // unique 제약 조건 제거
    },
    clearTime: { 
        type: Number, 
        default: null 
    },
    createdAt: { 
        type: Date, 
        default: Date.now 
    }
});

// 기존 인덱스 삭제
mongoose.connection.once('open', async () => {
    try {
        await mongoose.connection.collection('players').dropIndexes();
        console.log('Indexes dropped successfully');
    } catch (err) {
        console.log('Error dropping indexes:', err);
    }
});

const Player = mongoose.model('Player', playerSchema);

// 새 플레이어 등록 - 에러 처리 개선
app.post('/api/players', async (req, res) => {
    try {
        console.log('Received player data:', req.body);

        if (!req.body.name) {
            return res.status(400).json({ error: 'Player name is required' });
        }

        const player = new Player({
            name: req.body.name.trim()  // 공백 제거
        });

        const savedPlayer = await player.save();
        console.log('Saved player:', savedPlayer);
        res.json(savedPlayer);
    } catch (error) {
        console.error('Error saving player:', error);
        res.status(400).json({ 
            error: error.message,
            details: error.code === 11000 ? 'Duplicate player name' : error.message 
        });
    }
});

// 플레이어 기록 업데이트
app.put('/api/players/:id', async (req, res) => {
    try {
        const player = await Player.findByIdAndUpdate(
            req.params.id,
            { clearTime: req.body.clearTime },
            { new: true }
        );
        res.json(player);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

// 랭킹 조회
app.get('/api/rankings', async (req, res) => {
    try {
        const rankings = await Player.find({ clearTime: { $ne: null } })
            .sort({ clearTime: 1 })
            .limit(10);
        res.json(rankings);
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
