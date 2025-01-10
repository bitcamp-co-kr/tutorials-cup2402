AFRAME.registerComponent('spaceship-control', {
    init: function() {
        this.fuel = 100;
        this.score = 0;
        this.isGameOver = false;
        this.enginePlaying = false;
        this.lowFuelWarningPlayed = false;
        this.isGameStarted = false;
        this.baseFuelDrain = 0.5; // 기본 연료 소비율

        // 키 상태 초기화
        this.keys = {
            KeyW: false,
            KeyS: false,
            KeyA: false,
            KeyD: false,
            Space: false
        };

        // 사운드 초기화
        this.sounds = {
            bgm: document.querySelector('#bgm'),
            engine: document.querySelector('#engineSound'),
            thrust: document.querySelector('#thrustSound'),
            collision: document.querySelector('#collisionSound'),
            gameOver: document.querySelector('#gameOverSound'),
            lowFuel: document.querySelector('#lowFuelSound')
        };

        // 이벤트 리스너 바인딩
        this.onKeyDown = this.onKeyDown.bind(this);
        this.onKeyUp = this.onKeyUp.bind(this);
        
        // 이벤트 리스너 등록
        document.addEventListener('keydown', this.onKeyDown);
        document.addEventListener('keyup', this.onKeyUp);

        // 게임 시작 이벤트 리스너
        const startButton = document.querySelector('#start-button');
        if (startButton) {
            startButton.addEventListener('click', () => {
                this.isGameStarted = true;
                this.startGame();
            });
        }

        // 점수 관련 변수 추가
        this.lastScoreTime = 0;
        this.scoreInterval = 1000; // 1초
    },

    startGame: function() {
        // 초기화
        this.fuel = 100;
        this.score = 0;
        this.isGameOver = false;
        this.updateFuelDisplay();
    },

    onKeyDown: function(e) {
        if (this.keys.hasOwnProperty(e.code)) {
            this.keys[e.code] = true;
            
            // 엔진 사운드 처리
            if (e.code === 'Space' && !this.enginePlaying) {
                this.playSound('engine', true);
                this.enginePlaying = true;
            }
        }
    },

    onKeyUp: function(e) {
        if (this.keys.hasOwnProperty(e.code)) {
            this.keys[e.code] = false;
            
            // 엔진 사운드 중지
            if (e.code === 'Space' && this.enginePlaying) {
                this.stopSound('engine');
                this.enginePlaying = false;
            }
        }
    },

    tick: function(time, timeDelta) {
        if (!this.isGameStarted || this.isGameOver) return;

        // 기본 1초당 1점 증가
        if (time - this.lastScoreTime >= this.scoreInterval) {
            this.addScore(1);
            
            // 스페이스바 사용 중이면 추가 1점
            if (this.keys.Space) {
                this.addScore(1);
            }
            
            this.lastScoreTime = time;
        }

        // 기본 연료 소비 (수정된 부분)
        const baseFuelConsumption = this.baseFuelDrain * (timeDelta / 1000);
        this.fuel = Math.max(0, this.fuel - baseFuelConsumption);

        // 스페이스바 사용 시 추가 연료 소비
        if (this.keys.Space) {
            const additionalFuelConsumption = 0.1 * (timeDelta / 16.67);
            this.fuel = Math.max(0, this.fuel - additionalFuelConsumption);
        }

        // 연료 디스플레이 업데이트
        this.updateFuelDisplay();

        // 연료 부족 경고
        if (this.fuel <= 20 && !this.lowFuelWarningPlayed) {
            this.playSound('lowFuel');
            this.lowFuelWarningPlayed = true;
        }

        // 연료 고갈 체크
        if (this.fuel <= 0) {
            this.gameOver('연료 고갈!');
            return;
        }

        // 우주선 이동
        const moveSpeed = 0.1;
        const position = this.el.object3D.position;

        if (this.keys.KeyW) position.y += moveSpeed;
        if (this.keys.KeyS) position.y -= moveSpeed;
        if (this.keys.KeyA) {position.x -= moveSpeed;
            }
        if (this.keys.KeyD) {position.x += moveSpeed;
            }
        if (this.keys.Space) position.z -= moveSpeed * 2;
    },

    playSound: function(soundName, loop = false) {
        const sound = this.sounds[soundName];
        if (sound) {
            sound.loop = loop;
            sound.currentTime = 0;
            sound.play().catch(() => {
                console.log(`${soundName} autoplay prevented`);
            });
        }
    },

    stopSound: function(soundName) {
        const sound = this.sounds[soundName];
        if (sound) {
            sound.pause();
            sound.currentTime = 0;
        }
    },

    updateFuelDisplay: function() {
        const fuelDisplay = document.querySelector('#fuel-display');
        if (fuelDisplay) {
            fuelDisplay.textContent = `연료: ${Math.round(this.fuel)}%`;
        }
    },

    addFuel: function(amount) {
        this.fuel = Math.min(100, this.fuel + amount);
        this.updateFuelDisplay();
        this.lowFuelWarningPlayed = false;
    },

    addScore: function(points) {
        this.score += points;
        const gameManager = document.querySelector('[game-manager]');
        if (gameManager && gameManager.components['game-manager']) {
            gameManager.components['game-manager'].updateScore(points);
        }
    },

    gameOver: function(reason) {
        if (!this.isGameOver) {
            this.isGameOver = true;
            this.stopSound('engine');
            
            if (reason === '소행성 충돌!') {
                this.playSound('collision');
                setTimeout(() => {
                    this.playSound('gameOver');
                }, 500);
            } else {
                this.playSound('gameOver');
            }

            // UI 업데이트
            const gameOverDiv = document.getElementById('game-over');
            const reasonElement = document.getElementById('game-over-reason');
            const scoreElement = document.getElementById('final-score');

            reasonElement.textContent = reason;
            scoreElement.textContent = `최종 점수: ${this.score}`;
            gameOverDiv.style.display = 'flex';
        }
    },

    remove: function() {
        document.removeEventListener('keydown', this.onKeyDown);
        document.removeEventListener('keyup', this.onKeyUp);
        Object.values(this.sounds).forEach(sound => {
            if (sound) {
                sound.pause();
                sound.currentTime = 0;
            }
        });
    }
}); 