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

    $(".regist-container").on('click', ".all-check", (function () {
        if ($(".all-check").prop("checked") == true) {
            $(".service-check").prop("checked", true);
            $(".privateinfo-check").prop("checked", true);
        } else {
            $(".service-check").prop("checked", false);
            $(".privateinfo-check").prop("checked", false);
        }
    }))

    $(".btn-submit.join").click(function (e) {
        console.log("submit btn test")
        if ($(".privateinfo-check,.service-check").prop("checked") == false) {
            if (!$(".btn-submit:first").prev().hasClass("message")) {
                $(".btn-submit:first").prev().after(message);
            }
            e.preventDefault();
            return false;
        }
    })
    let message = '<p class="message">필수 항목 동의해주세요</p>';


    $(".change-password").click(function () {
        $(".info-container").html("").html(pass_change);

    });
    var pass_change = "    <div class=\"change-password-container\">\n" +
        "        <p class=\"head-title\">비밀번호 변경</p>\n" +
        "        <div class=\"change-password-body\">\n" +
        "            <form action=\"/\" method=\"post\">\n" +
        "                <span class=\"info-name\">기존 비밀번호</span>\n" +
        "                <input type=\"password\" class=\"orgin-password eingangplatz\" name=\"orgin-password\">\n" +
        "                <span class=\"info-name\">새로운 비밀번호</span>\n" +
        "                <input type=\"password\" class=\"new-password eingangplatz\" name=\"new-password\">\n" +
        "                <span class=\"info-name\">새로운 비밀번호 확인</span>\n" +
        "                <input type=\"password\" class=\"new-password-check eingangplatz\" name=\"new-password-check\">\n" +
        "                <button class=\"change-password-btn \">변&nbsp;&nbsp;경&nbsp;&nbsp;하&nbsp;&nbsp;기</button>\n" +
        "            </form>\n" +
        "        </div>\n" +
        "    </div>";

})

