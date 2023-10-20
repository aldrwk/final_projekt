$(function () {
// 일정 지정
    $(".participate-date-table").on("click", ".option-popup-cnt-down", function () {
        if ($(".option-popup-cnt").text() <= 0) {
            return;
        }
        let cnt = parseInt($(".option-popup-cnt").text());
        $(".option-popup-cnt").text(cnt - 1);
        totalPay();
    });
    $(".participate-date-table").on("click", ".option-popup-cnt-up", function () {
        let cnt = parseInt($(".option-popup-cnt").text());
        $(".option-popup-cnt").text(cnt + 1);
        totalPay();
    });

    function totalPay() {
        let price = $(".option-list.select").find(".option-price").text().replace(/\D/g, '');
        let cnt = $(".option-popup-cnt").text();
        console.log(price);
        $(".total-price").html((price * cnt).toLocaleString() + "원");
        $("#totalPrice").val((price * cnt));
    };

    // $(".goToPay").click(function () {
    //     const totalPrice = $(".total-price").text();
    //     const date = $(".date-selected").text();
    //     const time = $(".option-zeit").text();
    //     const productName = $(".participate-title").text();
    //     window.location.href = "/pay/1/pay-method?totalPrice=" + totalPrice + "&date=" + date + "&time=" + time + "$productName=" + productName;
    // });

    $(".option-list").click(function () {
        $(".option-list").removeClass('select');
        $(this).addClass('select');
        $(".option-popup-cnt").text("0");
        $(".total-price").text("0 원");
        $("#date").val($(this).children().first().text());
        $("#time").val($(this).children().eq(1).text());
        $("#productName").val($(".participate-title").text());
        if($(".option-popup-container").hasClass("option-popup-container")){
            return;
        }
        $(this).after(option_popup);
    });

    const option_popup = "<div class=\"option-popup-container\">\n" +
        "<div class=\"option-popup-title\">인원 선택</div>\n" +
        "<div class=\"option-popup\">\n" +
        "<div class=\"option-popup-cnt-down\">-</div>\n" +
        "<div class=\"option-popup-cnt\">0</div>\n" +
        "<div class=\"option-popup-cnt-up\">+</div>\n" +
        "</div>\n" +
        "</div>";

    $(".detail-body-cover-button").click(function () {
        if(!$(this).hasClass("more")){
        $(".detail-body-main").addClass("more-view");
        $(this).addClass("more");
        $(this).children().eq(0).html("간략히 보기");
        $(this).children().eq(1).addClass("arrow-turn");
            return false;
        }
        $(".detail-body-main").removeClass("more-view");
        $(this).removeClass("more");
        $(this).children().eq(0).html("상세정보 더보기 ");
        $(this).children().eq(1).removeClass("arrow-turn");
    });





});
