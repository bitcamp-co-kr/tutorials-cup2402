AFRAME.registerComponent('asteroid-spawner', {
    schema: {
        spawnDistance: {type: 'number', default: 30}
    },

    init: function() {
        this.asteroids = [];
        this.isSpawning = false;
        this.spawnAsteroid = this.spawnAsteroid.bind(this);
        
        // 기본 설정
        this.settings = {
            spawnInterval: 2000,    // 2초마다
            spawnCount: 2,          // 2개씩
            speed: 0.1              // 기본 속도
        };
    },

    updateSettings: function(newSettings) {
        console.log('이전 소행성 설정:', this.settings);
        
        // 스포닝 중지
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
            this.spawnIntervalId = null;
        }

        // 새로운 설정 적용
        if (newSettings) {
            this.settings = {
                spawnInterval: newSettings.spawnInterval || 2000,
                spawnCount: newSettings.spawnCount || 2,
                speed: newSettings.speed || 0.1
            };
        }
        
        console.log('새로운 소행성 설정:', this.settings);

        // 스포닝 재시작
        if (this.isSpawning) {
            this.startSpawning();
        }
    },

    startSpawning: function() {
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
        }
        
        console.log('소행성 스포너 시작 - 설정:', this.settings);
        this.isSpawning = true;
        
        // 즉시 첫 소행성 생성
        this.spawnAsteroid();
        
        // 주기적으로 소행성 생성
        this.spawnIntervalId = setInterval(() => {
            for (let i = 0; i < this.settings.spawnCount; i++) {
                this.spawnAsteroid();
            }
        }, this.settings.spawnInterval);
    },

    stopSpawning: function() {
        this.isSpawning = false;
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
            this.spawnIntervalId = null;
        }
    },

    spawnAsteroid: function() {
        if (!this.isSpawning) return;

        const player = document.querySelector('#player');
        if (!player) return;
        
        const playerPos = player.object3D.position;

        // 소행성 생성 위치 계산
        const x = Math.random() * 40 - 20;  // -20 ~ 20
        const y = Math.random() * 30 - 15;  // -15 ~ 15
        const z = playerPos.z - this.data.spawnDistance;

        // 소행성 엔티티 생성
        const asteroid = document.createElement('a-entity');
        asteroid.setAttribute('class', 'asteroid');
        asteroid.setAttribute('position', `${x} ${y} ${z}`);

        // 소행성 모델
        const radius = Math.random() * 0.8 + 0.3;
        asteroid.setAttribute('geometry', {
            primitive: 'sphere',
            radius: radius,
            segmentsWidth: 8,
            segmentsHeight: 8
        });
        asteroid.setAttribute('material', {
            color: '#666666',
            roughness: 0.8,
            metalness: 0.2
        });

        // 히트박스 추가
        const hitbox = document.createElement('a-entity');
        hitbox.setAttribute('class', 'asteroid-hitbox');
        hitbox.setAttribute('geometry', {
            primitive: 'sphere',
            radius: radius
        });
        hitbox.setAttribute('material', {
            color: '#ff0000',
            opacity: 0.0,
            transparent: true
        });
        asteroid.appendChild(hitbox);

        // 씬에 추가
        this.el.sceneEl.appendChild(asteroid);

        // 소행성 정보 저장
        this.asteroids.push({
            el: asteroid,
            speed: this.settings.speed * (Math.random() * 0.5 + 0.8),
            rotSpeed: {
                x: Math.random() * 2 - 1,
                y: Math.random() * 2 - 1,
                z: Math.random() * 2 - 1
            }
        });
    },

    tick: function() {
        if (!this.isSpawning) return;

        const player = document.querySelector('#player');
        if (!player) return;

        const playerPos = player.object3D.position;
        
        // 소행성 업데이트
        for (let i = this.asteroids.length - 1; i >= 0; i--) {
            const asteroid = this.asteroids[i];
            
            if (!asteroid.el.parentNode) {
                this.asteroids.splice(i, 1);
                continue;
            }

            // 위치 업데이트
            const pos = asteroid.el.getAttribute('position');
            pos.z += asteroid.speed;
            asteroid.el.setAttribute('position', pos);
            
            // 회전 업데이트
            const rot = asteroid.el.getAttribute('rotation') || {x: 0, y: 0, z: 0};
            rot.x += asteroid.rotSpeed.x;
            rot.y += asteroid.rotSpeed.y;
            rot.z += asteroid.rotSpeed.z;
            asteroid.el.setAttribute('rotation', rot);

            // 화면 밖으로 나간 소행성 제거
            if (pos.z > playerPos.z + 5) {
                asteroid.el.parentNode.removeChild(asteroid.el);
                this.asteroids.splice(i, 1);
            }
        }
    }
}); 