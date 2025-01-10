/**
 * Enemy component.
 * Handle enemy animation and dying behavior.
 * Favor using threejs `object3D` property of entities rather than `setAttribute()`
 * for optimization.
 */
AFRAME.registerComponent('enemy', {
  schema: {
    type: {type: 'number', default: 1}
  },

  init: function () {
    this.alive = true;
    this.exploding = false;
    this.moving = false;
    
    // 적 타입별 특성 설정
    switch (this.data.type) {
      case 1:
        this.jumpHeight = 1.0;
        this.jumpSpeed = 0.1;
        this.points = 50;
        break;
      case 2:
        this.jumpHeight = 5.0;
        this.jumpSpeed = 0.1;
        this.points = 75;
        break;
      case 3:
        this.jumpHeight = 8.0;
        this.jumpSpeed = 0.03;
        this.points = 100;
        break;
    }

    this.currentJumpHeight = 0;
    this.jumpDirection = 1;
    this.originalY = this.el.object3D.position.y;
    
    this.el.addEventListener('hit', () => {
      const gameManager = document.querySelector('[game-manager]');
      if (gameManager && gameManager.components['game-manager'].isPlaying) {
        gameManager.components['game-manager'].score += this.points;
        gameManager.components['game-manager'].updateScore();
        this.die();
      }
    });
  },

  tick: function (time, timeDelta) {
    if (!this.alive || this.exploding) return;

    // 점프 애니메이션
    this.currentJumpHeight += this.jumpSpeed * this.jumpDirection;
    
    if (this.currentJumpHeight >= this.jumpHeight) {
      this.jumpDirection = -1;
    } else if (this.currentJumpHeight <= 0) {
      this.jumpDirection = 1;
      this.currentJumpHeight = 0;
    }
    
    this.el.object3D.position.y = this.originalY + this.currentJumpHeight;
  },

  die: function () {
    if (this.exploding) return;
    this.exploding = true;
    this.alive = false;

    // 폭발 효과
    const explosionEl = document.querySelector('#' + this.el.id + 'expl');
    if (explosionEl) {
      explosionEl.object3D.position.copy(this.el.object3D.position);
      explosionEl.setAttribute('visible', true);
      setTimeout(() => {
        explosionEl.setAttribute('visible', false);
      }, 500);
    }

    // 적 숨기기
    this.el.setAttribute('visible', false);

    // 적 재생성
    setTimeout(() => {
      this.el.setAttribute('visible', true);
      this.exploding = false;
      this.alive = true;
      this.currentJumpHeight = 0;
    }, 2000);
  }
});
