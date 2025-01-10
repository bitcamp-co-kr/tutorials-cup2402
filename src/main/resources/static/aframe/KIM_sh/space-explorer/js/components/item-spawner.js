AFRAME.registerComponent('item-spawner', {
    schema: {
        spawnDistance: {type: 'number', default: 30}
    },

    init: function() {
        this.items = [];
        this.isSpawning = false;
        this.spawnItem = this.spawnItem.bind(this);
        
        // 기본 설정
        this.settings = {
            spawnInterval: 3000,    // 3초마다
            spawnCount: 1,          // 1개씩
            speed: 0.1
        };
    },

    updateSettings: function(newSettings) {
        console.log('이전 설정:', this.settings);
        
        // 현재 스폰 인터벌 중지
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
        }

        // 새로운 설정 적용
        this.settings = {...this.settings, ...newSettings};
        console.log('새로운 설정 적용됨:', this.settings);

        // 새로운 설정으로 스폰 시작
        if (this.isSpawning) {
            this.startSpawning();
        }
    },

    startSpawning: function() {
        if (!this.isSpawning) {
            this.isSpawning = true;
            this.spawnIntervalId = setInterval(() => {
                for (let i = 0; i < this.settings.spawnCount; i++) {
                    this.spawnItem();
                }
            }, this.settings.spawnInterval);
        }
    },

    stopSpawning: function() {
        this.isSpawning = false;
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
            this.spawnIntervalId = null;
        }
    },

    spawnItem: function() {
        const player = document.querySelector('#player');
        if (!player || !this.isSpawning) return;
        
        const playerPos = player.object3D.position;

        // 아이템 컨테이너 생성
        const itemContainer = document.createElement('a-entity');
        
        // 랜덤 위치 계산
        const x = Math.random() * 30 - 15;
        const y = Math.random() * 20 - 10;
        const z = playerPos.z - this.data.spawnDistance;
        
        // 아이템 타입 결정 (70% 연료, 30% 점수)
        const isFuel = Math.random() < 0.7;
        const itemValue = isFuel ? 20 : 100;
        
        // 아이템 모델
        const itemModel = document.createElement('a-entity');
        itemModel.setAttribute('geometry', {
            primitive: isFuel ? 'box' : 'sphere',
            width: 0.5,
            height: 0.5,
            depth: 0.5,
            radius: 0.3
        });
        itemModel.setAttribute('material', {
            color: isFuel ? '#00ff00' : '#ffff00',
            emissive: isFuel ? '#00ff00' : '#ffff00',
            emissiveIntensity: 0.5,
            metalness: 0.8,
            roughness: 0.2
        });

        // 아이템 히트박스 추가
        const hitbox = document.createElement('a-entity');
        hitbox.setAttribute('geometry', {
            primitive: 'sphere',
            radius: 0.4
        });
        hitbox.setAttribute('material', {
            color: isFuel ? '#00ff00' : '#ffff00',
            opacity: 0.3,
            transparent: true,
            shader: 'flat'
        });
        hitbox.setAttribute('class', 'item-hitbox');
        
        // 위치 설정
        itemContainer.setAttribute('position', `${x} ${y} ${z}`);
        
        // collectable 컴포넌트 추가
        itemContainer.setAttribute('collectable', {
            type: isFuel ? 'fuel' : 'score',
            value: itemValue
        });
        
        // 컨테이너에 모델과 히트박스 추가
        itemContainer.appendChild(itemModel);
        itemContainer.appendChild(hitbox);
        
        // 씬에 추가
        this.el.sceneEl.appendChild(itemContainer);
        
        // 아이템 목록에 추가
        this.items.push({
            el: itemContainer,
            speed: 0.1
        });

        console.log('아이템 생성:', isFuel ? '연료' : '점수');
    },

    tick: function() {
        if (!this.isSpawning) return;

        const player = document.querySelector('#player');
        if (!player) return;

        const playerPos = player.object3D.position;
        
        // 아이템 업데이트
        for (let i = this.items.length - 1; i >= 0; i--) {
            const item = this.items[i];
            
            if (!item.el.parentNode) {
                this.items.splice(i, 1);
                continue;
            }

            // 위치 업데이트
            const pos = item.el.getAttribute('position');
            pos.z += item.speed;
            item.el.setAttribute('position', pos);

            // 화면 밖으로 나간 아이템 제거
            if (pos.z > playerPos.z + 5) {
                item.el.parentNode.removeChild(item.el);
                this.items.splice(i, 1);
            }
        }
    },

    remove: function() {
        if (this.spawnIntervalId) {
            clearInterval(this.spawnIntervalId);
        }
        // 남은 아이템 제거
        this.items.forEach(item => {
            if (item.el.parentNode) {
                item.el.parentNode.removeChild(item.el);
            }
        });
        this.items = [];
    }
});