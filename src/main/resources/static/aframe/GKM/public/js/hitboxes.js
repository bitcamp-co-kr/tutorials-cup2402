AFRAME.registerComponent('map-hitboxes', {
    init: function() {
        // 맵 경계 히트박스
        this.createHitbox({
            position: "-96 18 -60",
            scale: "1 1 1",
            width: 0.2,
            height: 40,
            depth: 300,
            description: "테투리1"
        });

        this.createHitbox({
            position: "141 18 -50",
            scale: "1 1 1",
            width: 0.2,
            height: 40,
            depth: 300,
            description: "테투리2"
        });

        this.createHitbox({
            position: "15 18 59",
            scale: "1 1 1",
            width: 300,
            height: 40,
            depth: 0.2,
            description: "테두리3"
        });

        this.createHitbox({
            position: "18 18 -156",
            scale: "1 1 1",
            width: 300,
            height: 40,
            depth: 0.2,
            description: "테두리4"
        });

        // 건물들의 히트박스
        this.createHitbox({
            position: "91.5 8.6 -49",
            scale: "1 1 1",
            width: 69,
            height: 20,
            depth: 184,
            description: "건물 1"
        });

        this.createHitbox({
            position: "-19 8.6 -113.5",
            scale: "1 1 1",
            width: 121,
            height: 20,
            depth: 53.5,
            description: "건물 2"
        });

        // 도로 히트박스들
        this.createHitbox({
            position: "15 -2.4 -119",
            scale: "1 1 1",
            width: 300,
            height: 2,
            depth: 100,
            description: "도로1"
        });

        this.createHitbox({
            position: "100 -2.4 -49",
            scale: "1 1 1",
            width: 120,
            height: 2,
            depth: 300,
            description: "도로2"
        });

        this.createHitbox({
            position: "-138 -2.4 -49",
            scale: "1 1 1",
            width: 120,
            height: 2,
            depth: 300,
            description: "도로3"
        });

        this.createHitbox({
            position: "21 -2.4 90",
            scale: "1 1 1",
            width: 380,
            height: 2,
            depth: 100,
            description: "도로4"
        });
    },

    createHitbox: function(config) {
        const hitbox = document.createElement('a-box');
        
        // 물리 엔진 속성 추가 - 정적 바디로 설정
        hitbox.setAttribute('static-body', {
            shape: 'box',  // 박스 형태의 콜라이더
            mass: 0,       // 정적 바디는 질량이 0
        });
        
        hitbox.setAttribute('position', config.position);
        hitbox.setAttribute('width', config.width);
        hitbox.setAttribute('height', config.height);
        hitbox.setAttribute('depth', config.depth);
        hitbox.setAttribute('material', 'opacity: 0.2; transparent: true; color: red');
        hitbox.setAttribute('visible', 'true');
        hitbox.setAttribute('class', 'hitbox');
        hitbox.setAttribute('data-description', config.description);
        
        // 충돌 필터 설정
        hitbox.setAttribute('collision-filter', {
            collidesWith: ['player', 'zombie'],
            group: 'building',
            collisionForces: true  // 물리적 충돌 활성화
        });
        
        this.el.sceneEl.appendChild(hitbox);
    }
}); 