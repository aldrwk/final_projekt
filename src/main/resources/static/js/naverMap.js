$(function () {
    var map = null;
    var marker = null;
    var point = null;
    function initMap() {
        map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.3595316, 127.1052133 ),
        });
        marker = new naver.maps.Marker({
            position: point,
            map: map
        });

    }
    function searchAddressToCoordinate(address) {
            naver.maps.Service.geocode({
                query: address
            }, function(status, response) {
                if (status === naver.maps.Service.Status.ERROR) {
                    return alert('Something Wrong!');
                }
                if (response.v2.meta.totalCount === 0) {
                    return alert('totalCount' + response.v2.meta.totalCount);
                }
                var htmlAddresses = [],
                    item = response.v2.addresses[0],
                    point = new naver.maps.Point(item.x, item.y);

                map.setCenter(point);
                marker.setPosition(point);

            });
        }
    searchAddressToCoordinate($(".detail-platz-address").text()) //주소값 대입
    initMap();
});
