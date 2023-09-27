$(function () {
    var map = null;

    function initMap() {
        map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.3595704, 127.105399)
        });
        marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(37.3595704, 127.105399),
            map: map
        });
    }

    initMap();
});
