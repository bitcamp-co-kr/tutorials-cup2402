const express = require('express');
const router = express.Router();
const Scene = require('../models/Scene');

// 최고 점수 목록 가져오기
router.get('/highscores', async (req, res) => {
    try {
        const highScores = await Scene.find()
            .sort({ score: -1 })
            .limit(10);
        res.json(highScores);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// 새로운 점수 저장
router.post('/score', async (req, res) => {
    const scene = new Scene({
        playerName: req.body.playerName,
        score: req.body.score,
        playTime: req.body.playTime,
        collisionType: req.body.collisionType,
        totalDistance: req.body.totalDistance,
        outOfBoundCount: req.body.outOfBoundCount,
        difficulty: req.body.difficulty
    });

    try {
        const newScene = await scene.save();
        res.status(201).json(newScene);
    } catch (err) {
        console.error('Score save error:', err);
        res.status(400).json({ message: err.message });
    }
});

module.exports = router; 