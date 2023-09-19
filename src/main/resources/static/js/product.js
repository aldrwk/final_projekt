$(function () {
// 일정 지정
    $(".option-list").on("click", ".option-popup-cnt-down", function () {
        if ($(".option-popup-cnt").text() <= 0) {
            return;
        }
        let cnt = parseInt($(".option-popup-cnt").text());
        $(".option-popup-cnt").text(cnt - 1);
        totalPay();
    });
    $(".option-list").on("click", ".option-popup-cnt-up", function () {
        let cnt = parseInt($(".option-popup-cnt").text());
        $(".option-popup-cnt").text(cnt + 1);
        totalPay();
    });


    function totalPay() {
        let price = $(".option-price").text().replace(/\D/g, '');
        let cnt = $(".option-popup-cnt").text();
        $(".total-price").html((price * cnt).toLocaleString() + "원");
    };

    $(".goToPay").click(function () {
        const totalPrice = $(".total-price").text();
        const date = $(".date-selected").text();
        const time = $(".option-zeit").text();
        const productName = $(".participate-title").text();
        window.location.href = "/pay/1/pay-method?totalPrice="+totalPrice+"&date="+date+"&time="+time+"$productName="+productName;
    });
})
