AFRAME.registerComponent('collision-detector', {
    init: function () {
        this.el.sceneEl.addEventListener('loaded', () => {
            this.asteroids = document.querySelectorAll('.asteroid-hitbox');
        });
        this.isColliding = false;
        this.lastCheck = 0;
        this.checkInterval = 100; // 충돌 체크 간격 늘림
    },

    tick: function (time) {
        if (time - this.lastCheck < this.checkInterval) return;
        this.lastCheck = time;
        
        if (this.isColliding) return;

        const shipBox = new THREE.Box3();
        const shipPosition = new THREE.Vector3();
        this.el.object3D.getWorldPosition(shipPosition);
        
        // 히트박스 크기 조임
        shipBox.setFromCenterAndSize(
            shipPosition,
            new THREE.Vector3(0.8, 0.8, 0.8) // 1.2에서 0.8로 줄임
        );
        
        // 소행성 충돌 체크
        this.asteroids = document.querySelectorAll('.asteroid-hitbox');
        this.asteroids.forEach(asteroid => {
            if (this.isColliding) return;

            const asteroidBox = new THREE.Box3();
            const asteroidPosition = new THREE.Vector3();
            asteroid.object3D.getWorldPosition(asteroidPosition);
            
            const asteroidRadius = asteroid.getAttribute('geometry').radius;
            asteroidBox.setFromCenterAndSize(
                asteroidPosition,
                new THREE.Vector3(asteroidRadius * 1.5, asteroidRadius * 1.5, asteroidRadius * 1.5) // 충돌 범위 조정
            );
            
            // 실제 충돌 거리 계산 추가
            const distance = shipPosition.distanceTo(asteroidPosition);
            if (distance < asteroidRadius + 0.8 && shipBox.intersectsBox(asteroidBox)) {
                this.isColliding = true;
                const spaceship = document.querySelector('#player');
                if (spaceship) {
                    const spaceshipControl = spaceship.components['spaceship-control'];
                    if (spaceshipControl && !spaceshipControl.isGameOver) {
                        spaceshipControl.gameOver('소행성 충돌!');
                    }
                }
            }
        });

        // 아이템 충돌 체크
        const items = document.querySelectorAll('.item-hitbox');
        items.forEach(item => {
            const itemBox = new THREE.Box3();
            const itemPosition = new THREE.Vector3();
            item.object3D.getWorldPosition(itemPosition);
            
            itemBox.setFromCenterAndSize(
                itemPosition,
                new THREE.Vector3(0.8, 0.8, 0.8)
            );
            
            if (shipBox.intersectsBox(itemBox)) {
                const itemContainer = item.parentElement;
                if (itemContainer && itemContainer.components.collectable) {
                    console.log('',itemContainer.components.collectable );
                    itemContainer.components.collectable.collect();
                }
            }
        });
    }
}); 