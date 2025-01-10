AFRAME.registerComponent('collectable', {
    schema: {
        type: {type: 'string', default: 'score'},
        value: {type: 'number', default: 100}
    },

    init: function() {
        this.collected = false;
        this.checkCollision = this.checkCollision.bind(this);
    },

    tick: function() {
        if (this.collected) return;
        this.checkCollision();
    },
    collect: function(){
        this.data.value += 10;  
    },

    checkCollision: function() {
        const player = document.querySelector('#player');
        if (!player) return;

        const playerPos = player.object3D.position;
        const itemPos = this.el.object3D.position;
        
        // 거리 계산
        const distance = new THREE.Vector3().subVectors(playerPos, itemPos).length();
        
        // 충돌 감지 (거리가 1 이하일 때)
        if (distance < 1) {
            this.collected = true;
            
            // 게임 매니저 찾기
            const gameManager = document.querySelector('[game-manager]').components['game-manager'];
            
            if (this.data.type === 'score') {
                // 점수 증가
                gameManager.updateScore(this.data.value);
                console.log('점수 획득:', this.data.value);
            } else if (this.data.type === 'fuel') {
                // 연료 증가
                const spaceship = player.components['spaceship-control'];
                if (spaceship) {
                    spaceship.addFuel(this.data.value);
                    console.log('연료 획득:', this.data.value);
                }
            }

            // 효과음 재생
            const collectSound = document.querySelector('#collectSound');
            if (collectSound) {
                collectSound.currentTime = 0;
                collectSound.play().catch(() => {});
            }

            // 아이템 제거
            this.el.parentNode.removeChild(this.el);
        }
    }
}); 