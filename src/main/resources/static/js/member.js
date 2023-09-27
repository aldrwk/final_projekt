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
            if (!$(".btn-submit").eq(1).prev().hasClass("message")) {
                $(".btn-submit").eq(1).prev().after(message);
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

    $(".mobile-area").on("click", ".check-request", function () {
        const number = $(".mobile_number_check").val();
        const regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (number == "" || !regPhone.test(number)) {
            if ($(".check-request").next().hasClass("message")) {
                return false;
            }
            if ($(".check-request").next().hasClass("check-area")) {
                // $(".check-request").next().after().html("");
                $(".check-request").next().html("");
                console.log("test");
            }
            const message = "<span class=\"message\">올바른 핸드폰 번호를 적어주세요.</span>"
            $(this).after(message);
            return false;
        }
        if ($(".check-request").next().hasClass("title-input")) {
            return false;
        }
        if($(".check-request").next().hasClass("message")){
            $(this).next().html("");
        }
        $(this).after(auth);
    });
    $(".check_number_auth").click(function () {

    });

    var auth = "<div class=\"check-area\" style=\"width: 100%\"> " +
        "<span class=\"title-input\" >인증 번호</span>" +
        "<div class=\"check-input\"> <input class=\"text-input check_number\" type=\"text\" name=\"check_number\"\n" +
        "placeholder=\"인증 번호를 입력해주세요.\">\n" +
        "<button type=\"button\" class=\"btn btn-submit check_number_auth \">확인 하기</button></div></div>"
})

