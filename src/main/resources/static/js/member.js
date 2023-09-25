$(function () {
    $(".custom-checkbox").on('click', ".all-check", (function () {
        if ($(".all-check").prop("checked") == true) {
            $(".service-check").prop("checked", true);
            $(".privateinfo-check").prop("checked", true);
        } else {
            $(".service-check").prop("checked", false);
            $(".privateinfo-check").prop("checked", false);
        }


    }))


    $(".kakao-login-btn").click(function () {
        window.location.href = 'api/login/kakaoauth';
    });
    $(".naver-login-btn").click(function () {
        closePopup();
    });
    $(".google-login-btn").click(function () {
        window.open("test", "카카오 로그인", "width=400,height=400");
    });


})

