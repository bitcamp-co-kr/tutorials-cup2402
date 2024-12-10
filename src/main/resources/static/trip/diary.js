// 카카오 지도 API 초기화
var container = document.getElementById('map'); // 지도를 표시할 div
var options = {
    center: new kakao.maps.LatLng(37.5665, 126.9780), // 초기 지도 중심 좌표 (서울)
    level: 3 // 지도 확대 레벨
};

var map = new kakao.maps.Map(container, options); // 지도 생성

// 핑 추가 함수
function addMarker(lat, lng, color) {
    var markerImage = new kakao.maps.MarkerImage(
        `https://via.placeholder.com/15/${color}/000000?text=`,
        new kakao.maps.Size(15, 15)
    );

    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(lat, lng),
        image: markerImage
    });

    return marker;
}

// 예제 핑 추가
addMarker(37.5665, 126.9780, "ff0000"); // 빨간 핑 추가
