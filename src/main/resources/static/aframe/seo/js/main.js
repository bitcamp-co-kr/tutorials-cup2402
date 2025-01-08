// 씬 데이터를 서버에 저장하는 함수
function saveScene() {
    const sceneData = {
        name: "My Scene",
        objects: [
            {
                type: "box",
                position: "-1 0.5 -3",
                rotation: "0 45 0",
                color: "#4CC3D9"
            },
            {
                type: "sphere",
                position: "0 1.25 -5",
                radius: 1.25,
                color: "#EF2D5E"
            },
            // 나머지 객체들도 필요하다면 추가
        ]
    };

    $.ajax({
        url: '/api/scenes',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sceneData),
        success: function(response) {
            console.log('씬이 저장되었습니다:', response);
        },
        error: function(error) {
            console.error('저장 실패:', error);
        }
    });
}

// 물체를 생성하는 함수 추가
function createObject(type) {
    const scene = document.querySelector('a-scene');
    const entity = document.createElement(type);
    
    // 기본 속성 설정
    entity.setAttribute('dynamic-body', '');
    entity.setAttribute('position', `${Math.random() * 8 - 4} 10 -3`);
    
    // 물체 타입별 추가 속성
    switch(type) {
        case 'a-box':
            entity.setAttribute('color', '#4CC3D9');
            entity.setAttribute('mass', '2');
            break;
        case 'a-sphere':
            entity.setAttribute('radius', '0.5');
            entity.setAttribute('color', '#EF2D5E');
            entity.setAttribute('mass', '1');
            break;
        case 'a-cylinder':
            entity.setAttribute('radius', '0.5');
            entity.setAttribute('height', '1.5');
            entity.setAttribute('color', '#FFC65D');
            entity.setAttribute('mass', '3');
            break;
    }
    
    scene.appendChild(entity);
}

// 키보드 이벤트 리스너 추가
document.addEventListener('keydown', (event) => {
    switch(event.key) {
        case 'b': // 박스 생성
            createObject('a-box');
            break;
        case 's': // 구 생성
            createObject('a-sphere');
            break;
        case 'c': // 실린더 생성
            createObject('a-cylinder');
            break;
    }
}); 