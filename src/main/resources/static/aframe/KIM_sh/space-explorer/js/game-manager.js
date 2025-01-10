AFRAME.registerComponent('game-manager', {
    init: function() {
        this.state = {
            score: 0,
            level: 1,
            isPlaying: false,
            isPaused: false
        };

        // 게임 시작 메튼 이벤트
        const startButton = document.querySelector('#start-button');
        const startScreen = document.querySelector('#start-screen');
        const gameUI = document.querySelector('#game-ui');

        if (startButton && startScreen && gameUI) {
            startButton.addEventListener('click', () => {
                startScreen.style.display = 'none';
                gameUI.style.display = 'block';
                this.startGame();
            });
        }

        // 배경음악 초기화
        this.bgm = document.querySelector('#bgm');
        if (this.bgm) {
            this.bgm.volume = 0.5;
        }

        // 이벤트 리스너
        document.addEventListener('keydown', (e) => {
            if (e.code === 'Escape' && this.state.isPlaying) {
                this.togglePause();
            }
        });

        // UI 업데이트 함수 바인딩
        this.updateScore = this.updateScore.bind(this);
        this.updateLevel = this.updateLevel.bind(this);
    },

    startGame: function() {
        if (this.isGameRunning) return;
        
        this.isGameRunning = true;
        this.state.isPlaying = true;
        this.state.isPaused = false;

        // 초기 난이도 설정 적용
        this.updateDifficulty();

        // 스포너 시작 (딜레이 없이 바로 시작)
        const asteroidSpawner = document.querySelector('#asteroidSpawner');
        if (asteroidSpawner && asteroidSpawner.components['asteroid-spawner']) {
            console.log('소행성 스포너 시작');
            asteroidSpawner.components['asteroid-spawner'].startSpawning();
        }

        const itemSpawner = document.querySelector('#itemSpawner');
        if (itemSpawner && itemSpawner.components['item-spawner']) {
            console.log('아이템 스포너 시작');
            itemSpawner.components['item-spawner'].startSpawning();
        }

        // 게임 시작 메시지 표시
        this.showLevelMessage();
    },

    togglePause: function() {
        this.state.isPaused = !this.state.isPaused;
        
        if (this.state.isPaused) {
            // 게임 일시정지
            if (this.bgm) this.bgm.pause();
            // 일시정지 UI 표시
            // TODO: 일시정지 UI 구현
        } else {
            // 게임 재개
            if (this.bgm) this.bgm.play();
        }
    },

    updateScore: function(points) {
        this.state.score += points;
        const scoreDisplay = document.querySelector('#score-display');
        if (scoreDisplay) {
            scoreDisplay.textContent = `점수: ${this.state.score}`;
        }

        // 레벨업 체크
        if (this.state.score >= this.state.level * 1000) {
            this.state.level++;
            this.updateLevel();
        }
    },
    updateDifficulty: function() {
        console.log('here');
    },
    showLevelMessage: function(){
        console.log('here');        
    },
    updateLevel: function() {
        // 난이도 증가
        const asteroidSpawner = document.querySelector('[asteroid-spawner]');
        if (asteroidSpawner && asteroidSpawner.components['asteroid-spawner']) {
            const spawnerComp = asteroidSpawner.components['asteroid-spawner'];
            spawnerComp.data.spawnInterval *= 0.9;  // 생성 주기 감소
            spawnerComp.data.speed *= 1.1;          // 속도 증가
        }

        // 레벨업 메시지 표시
        const levelMessage = document.createElement('div');
        levelMessage.className = 'level-message';
        levelMessage.textContent = `Level ${this.state.level}!`;
        document.body.appendChild(levelMessage);

        setTimeout(() => {
            if (levelMessage.parentNode) {
                levelMessage.parentNode.removeChild(levelMessage);
            }
        }, 2000);
    },

    remove: function() {
        // 정리 작업
        if (this.bgm) {
            this.bgm.pause();
            this.bgm.currentTime = 0;
        }
    }
});