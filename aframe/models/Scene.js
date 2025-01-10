const mongoose = require('mongoose');

const SceneSchema = new mongoose.Schema({
    playerName: {
        type: String,
        required: true
    },
    score: {
        type: Number,
        required: true
    },
    playTime: {
        type: Number,
        required: true
    },
    collisionType: {
        type: String,
        enum: ['bus', 'car'],
        required: true
    },
    totalDistance: {
        type: Number,
        default: 0
    },
    outOfBoundCount: {
        type: Number,
        default: 0
    },
    difficulty: {
        type: Number,
        default: 1
    },
    jumpCount: { 
        type: Number, 
        default: 0
    },
    deathPosition: {
        x: { type: Number },
        z: { type: Number }
    },
    vehicleCount: {
        cars: { type: Number, default: 0 },
        buses: { type: Number, default: 0 }
    },
    gameStartTime: {
        type: Date,
        default: Date.now
    },
    gameEndTime: {
        type: Date,
        default: Date.now
    },
    sessionDuration: {
        type: Number,
        default: 0
    },
    deviceInfo: {
        browser: String,
        platform: String,
        screenSize: String
    },
    gameVersion: {
        type: String,
        default: '1.0.0'
    }
});

module.exports = mongoose.model('Scene', SceneSchema); 