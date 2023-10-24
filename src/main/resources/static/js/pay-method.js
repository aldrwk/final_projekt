$(function () {

    DateRetouch(reservationDate, option);

    function DateRetouch(reservationDate, option) {
        date = new Date(reservationDate.reservDate);
        ampm = date.toLocaleString().split(" ")[3];
        days = ["일", "월", "화", "수", "목", "금", "토"];
        if (date.getMinutes() == 0) {
            Minutes = "00";
        } else {
            Minutes = date.getMinutes();
        }
        reservDate = date.getMonth() + 1 + "월 " + date.getDate() + "일 (" + days[date.getDay()] + ") " + ampm + " " + date.getHours() + " : " + Minutes;
        $(".pay-product-date").text(reservDate);
        $(".pay-product-info").text(option.optionName);
        console.log(option)
    }

    totalPrice = parseInt(totalPrice).toLocaleString();
    $(".pay-price-total").text(totalPrice + "원");

});
