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
        let message = '<p class="message fail">필수 항목 동의해주세요</p>';
        if ($(".privateinfo-check,.service-check").prop("checked") == false) {
            if (!$(".btn-submit.join").prev().hasClass("message")) {
                $(".btn-submit.join").prev().after(message);
            }
            if ($("#email").val() == "") {
                if ($("#email").next().hasClass("message")) {
                    $("#email").next().remove();
                }
                const message = "<span class=\"message fail\">이메일을 입력해주세요.</span>";
                $("#email").after(message);
            }
            if ($("#password").val() == "") {
                if ($("#password").next().hasClass("message")) {
                    $("#password").next().remove();
                }
                const message = "<span class=\"message fail\">비밀번호를 입력해주세요.</span>";
                $("#password").after(message);
            }
            if ($(".mobile_number_check").val() == "") {
                if ($(".check-request").next().hasClass("message")) {
                    $(".check-request").next().remove();
                }
                const message = "<span class=\"message fail\">핸드폰 번호를 입력해주세요.</span>";
                $(".check-request").after(message);
            }

            e.preventDefault();
            return false;
        }
    })


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


    $(".mobile_number_check").keyup(function () {
        if (isNaN($(this).val())) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
        }
    });


    $(".mobile-area").on("click", ".check-request", function () {
        const number = $(".mobile_number_check").val();
        const regPhone = /^(01[0|1|6|7|8|9])+([0-9]{7,8})$/;
        if (number == "" || !regPhone.test(number)) {
            if ($(".check-request").next().hasClass("message")) {
                return false;
            }
            if ($(".check-request").next().hasClass("check-area")) {
                // $(".check-request").next().after().html("");
                $(".check-request").next().remove();
                console.log("test");
            }
            const message = "<span class=\"message fail\">올바른 핸드폰 번호를 적어주세요.</span>"
            $(this).after(message);
            return false;
        }
        if ($(".check-request").next().hasClass("check-area")) {
            return false;
        }
        if ($(".check-request").next().hasClass("message")) {
            $(this).next().remove();
        }
        SmsAuth(number);
        $(this).after(auth);
    });

    function SmsAuth(mobile) {
        $.ajax({
            type: "Post",
            url: "api/naver/mobileAuth",
            data: {mobile: mobile},
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data == true) {
                    const message = "<span class=\"message fail\">등록된 핸드폰 번호입니다.</span>";
                    $(".check-request").after(message);
                }
            }
        })
    }

    $(document).on("click", ".check_number_auth", function () {
        const AuthNum = $(".check_number").val();
        console.log(AuthNum);
        SmsAuthOk(AuthNum);
    });

    function SmsAuthOk(AuthNum) {
        $.ajax({
            type: "Post",
            url: "api/naver/mobileAuthOk",
            data: {AuthNum: AuthNum},
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data == true) {
                    const message = "<span class=\"message ok\">인증되었습니다.</span>";
                    $(".check_number_auth").after(message);
                }
            }
        })
    }

    var auth = "<div class=\"check-area\" style=\"width: 100%\"> " +
        "<span class=\"title-input\" >인증 번호</span>" +
        "<div class=\"check-input\"> <input class=\"text-input check_number\" type=\"text\" name=\"mobile_check\"" +
        "placeholder=\"인증 번호를 입력해주세요.\">" +
        "<button type=\"button\" class=\"btn btn-submit check_number_auth \">확인 하기</button></div></div>";


    $("#email").focusout(function () {
        const pattern = /^\w+@\w+[.]\w{3}$/;
        const email = $(this).val();
        if (!pattern.test(email)) {
            $(this).next().remove();
            const message = "<span class=\"message fail\">이메일 형식이 맞지 않습니다.</span>";
            $(this).after(message);
        } else {
            email_check(email)
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
        }
    });

    function email_check(email) {
        $.ajax({
            type: "Get",
            url: "memberCheck",
            data: {email: email},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.result == 1) {
                    const message = "<span class=\"message fail\">사용중인 이메일입니다.</span>";
                    $("#email").after(message);
                } else {
                    const message = "<span class=\"message ok\">사용 가능한 이메일입니다.</span>";
                    $("#email").after(message);
                }
            },
        });
    }

    $("#password").keyup(function () {
        regpassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/
        pass = $(this).val();
        if (!regpassword.test(pass)) {
            $(this).next().remove();
            const message = "<span class=\"message fail\">영문+숫자+특수문자 조합 8자리이상 입력해주세요.</span>";
            $(this).after(message);
        } else {
            $(this).next().remove();
            const message = "<span class=\"message ok\">사용 가능한 비밀번호입니다.</span>";
            $(this).after(message);
        }
    });

    $("#password_check").keyup(function () {
        pass = $("#password").val();
        pass_check = $(this).val();

        if (pass != pass_check) {
            $(this).next().remove();
            const message = "<span class=\"message fail\">비밀번호가 일치하지 않습니다.</span>";
            $(this).after(message);
        } else {
            $(this).next().remove();
            const message = "<span class=\"message ok\">비밀번호가 일치합니다.</span>";
            $(this).after(message);
        }
    });


})

