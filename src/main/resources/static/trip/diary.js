const map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5665, 126.9780),
    zoom: 10
});

// 예시로 핑 추가
new naver.maps.Marker({
    position: new naver.maps.LatLng(37.5665, 126.9780),
    map: map,
    title: '예제 핑'
});
