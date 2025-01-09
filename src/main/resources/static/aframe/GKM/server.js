require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const path = require('path');

const app = express();
const port = 3000;

// JSON 파싱 미들웨어
app.use(express.json());
// 정적 파일 제공
app.use(express.static('public'));

// MongoDB 연결
mongoose.connect(process.env.MONGODB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
})
.then(() => console.log('MongoDB Atlas 연결 성공'))
.catch(err => console.error('MongoDB Atlas 연결 오류:', err));

// Record 스키마 정의 수정
const recordSchema = new mongoose.Schema({
  player_name: {
    type: String,
    required: true
  },
  survival_time: {
    type: Number,
    required: true
  },
  created_at: {
    type: Date,
    default: Date.now
  }
});

const Record = mongoose.model('Record', recordSchema);

// 기록 저장 API
app.post('/api/records', async (req, res) => {
  try {
    const { playerName, clearTime: survivalTime } = req.body;
    
    if (!playerName || !survivalTime) {
      return res.status(400).json({ error: '필수 정보가 누락되었습니다.' });
    }

    const record = new Record({
      player_name: playerName,
      survival_time: survivalTime
    });

    await record.save();
    
    res.json({ 
      success: true, 
      record: record 
    });
  } catch (err) {
    console.error('기록 저장 오류:', err);
    res.status(500).json({ error: '기록 저장에 실패했습니다.' });
  }
});

// 기록 조회 API (상위 10개)
app.get('/api/records', async (req, res) => {
  try {
    const records = await Record
      .find()
      .sort({ survival_time: -1 })
      .limit(10);
    
    res.json(records);
  } catch (err) {
    console.error('기록 조회 오류:', err);
    res.status(500).json({ error: '기록 조회에 실패했습니다.' });
  }
});

// 서버 시작
app.listen(port, () => {
  console.log(`서버가 http://localhost:${port} 에서 실행 중입니다.`);
});

// 프로세스 종료 시 MongoDB 연결 종료
process.on('SIGINT', async () => {
  try {
    await mongoose.connection.close();
    console.log('MongoDB 연결이 안전하게 종료되었습니다.');
    process.exit(0);
  } catch (err) {
    console.error('MongoDB 종료 오류:', err);
    process.exit(1);
  }
}); 