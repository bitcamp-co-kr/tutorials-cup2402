AFRAME.registerComponent('game-manager', {
    init: function() {
        this.state = {
            score: 0,
            level: 1,
            isPlaying: false,
            isPaused: false
        };

        // 난이도 설정
        this.difficultyLevels = [
            { 
                score: 0,
                asteroidSettings: {
                    spawnInterval: 2000,    // 2초마다
                    spawnCount: 2,          // 2개씩
                    speed: 0.1
                },
                itemSettings: {
                    spawnInterval: 3000,    // 3초마다
                    spawnCount: 1,          // 1개씩
                    speed: 0.1
                },
                fuelDrain: 0.3
            },
            { 
                score: 50,
                asteroidSettings: {
                    spawnInterval: 1500,
                    spawnCount: 3,
                    speed: 0.12
                },
                itemSettings: {
                    spawnInterval: 2500,
                    spawnCount: 1,
                    speed: 0.1
                },
                fuelDrain: 0.4
            },
            { 
                score: 100,
                asteroidSettings: {
                    spawnInterval: 1200,
                    spawnCount: 4,
                    speed: 0.15
                },
                itemSettings: {
                    spawnInterval: 2000,
                    spawnCount: 2,
                    speed: 0.12
                },
                fuelDrain: 0.5
            }
        ];

        // 초기 난이도 설정
        this.currentDifficulty = this.difficultyLevels[0];
        
        // 이벤트 리스너
        this.el.addEventListener('score-updated', this.checkDifficulty.bind(this));
        
        // 시작 버튼 이벤트
        const startButton = document.querySelector('#start-button');
        if (startButton) {
            startButton.addEventListener('click', () => {
                this.startGame();
            });
        }
    },

    startGame: function() {
        if (this.isGameRunning) return;
        
        this.isGameRunning = true;
        this.state.isPlaying = true;
        this.state.isPaused = false;

        // 소기 난이도 설정 적용
        this.updateDifficulty();

        // 스포너 시작
        setTimeout(() => {
            const asteroidSpawner = document.querySelector('[asteroid-spawner]');
            const itemSpawner = document.querySelector('[item-spawner]');

            if (asteroidSpawner) {
                asteroidSpawner.components['asteroid-spawner'].startSpawning();
            }
            if (itemSpawner) {
                itemSpawner.components['item-spawner'].startSpawning();
            }
        }, 1000);
    },

    updateDifficulty: function() {
        // 현재 점수에 맞는 난이도 찾기
        let newDifficulty = this.difficultyLevels[0];
        for (let level of this.difficultyLevels) {
            if (this.state.score >= level.score) {
                newDifficulty = level;
            }
        }

        // 난이도 설정 업데이트
        const asteroidSpawner = document.querySelector('[asteroid-spawner]');
        const itemSpawner = document.querySelector('[item-spawner]');

        if (asteroidSpawner) {
            asteroidSpawner.components['asteroid-spawner'].updateSettings(newDifficulty.asteroidSettings);
        }
        if (itemSpawner) {
            itemSpawner.components['item-spawner'].updateSettings(newDifficulty.itemSettings);
        }

        // 우주선 연료 소비율 업데이트
        const spaceship = document.querySelector('#player');
        if (spaceship && spaceship.components['spaceship-control']) {
            spaceship.components['spaceship-control'].baseFuelDrain = newDifficulty.fuelDrain;
        }
    },

    updateScore: function(points) {
        this.state.score += points;
        const scoreDisplay = document.querySelector('#score-display');
        if (scoreDisplay) {
            scoreDisplay.textContent = `점수: ${this.state.score}`;
        }
        this.checkDifficulty();
    },

    checkDifficulty: function() {
        let newLevel = 1;
        for (let i = this.difficultyLevels.length - 1; i >= 0; i--) {
            if (this.state.score >= this.difficultyLevels[i].score) {
                newLevel = i + 1;
                break;
            }
        }

        if (newLevel !== this.state.level) {
            this.state.level = newLevel;
            this.updateDifficulty();
            this.showLevelMessage();
        }
    },

    showLevelMessage: function() {
        const levelMessage = document.createElement('div');
        levelMessage.className = 'level-message';
        
        let message;
        switch(this.state.level) {
            case 1:
                message = '우주 탐사 시작!';
                break;
            case 2:
                message = '소행성 군집 증가!';
                break;
            case 3:
                message = '아이템 생성량 증가!';
                break;
            case 4:
                message = '아이템 생성 주기 감소!';
                break;
            default:
                message = `Level ${this.state.level}!`;
        }
        
        levelMessage.textContent = message;
        document.body.appendChild(levelMessage);

        // 효과음 재생
        const levelUpSound = document.querySelector('#levelUpSound');
        if (levelUpSound) {
            levelUpSound.currentTime = 0;
            levelUpSound.play().catch(() => {});
        }

        setTimeout(() => {
            if (levelMessage.parentNode) {
                levelMessage.parentNode.removeChild(levelMessage);
            }
        }, 2000);
    },

    checkLevelProgress: function() {
        // 주기적으로 게임 진행 상태 체크
        setInterval(() => {
            if (!this.isGameRunning) return;
            
            // 추가 게임 로직
        }, 1000);
    }
});