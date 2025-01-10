const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();
const path = require('path');

const app = express();

// 미들웨어 설정
app.use(cors());
app.use(express.json());
app.use(express.static('public'));
app.use('/a-painter', express.static(path.join(__dirname, 'public/a-painter')));
app.use('/a-blast', express.static(path.join(__dirname, 'public/a-blast')));

// MongoDB 연결
mongoose.connect(process.env.MONGODB_URI)
    .then(() => console.log('MongoDB 연결 성공'))
    .catch(err => console.error('MongoDB 연결 실패:', err));

// 라우트 설정
const sceneRouter = require('./routes/scene');
app.use('/api/scenes', sceneRouter);

// 서버 시작
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`서버가 ${PORT} 포트에서 실행 중입니다.`);
}); 