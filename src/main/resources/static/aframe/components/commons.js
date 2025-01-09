

AFRAME.registerComponent('portal-url', {
    init: function () {
        const el = this.el;
        el.setAttribute('obb-collider', ``);
        el.addEventListener('obbcollisionstarted', function (e) {
            console.log('Player collided start ');
            if (el.hasAttribute('href')) {
                window.location.href = el.getAttribute('href');
            }
        });
        el.addEventListener('obbcollisionended', function (e) {
            console.log('Player collided end ');
        });
        console.log('collider component initialized : ');
    }
});

