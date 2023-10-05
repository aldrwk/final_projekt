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

    $(".btn-submit.join").click(function (e) {
        console.log("submit btn test")
        if ($(".privateinfo-check,.service-check").prop("checked") == false && $(".service-check").prop("checked") == false) {
            if (!$(".btn-submit.join").prev().hasClass("message")) {
                let message = '<p class="message fail">필수 항목 동의해주세요</p>';
                $(".btn-submit.join").prev().after(message);
            }
            e.preventDefault();
        }
        if ($("#email").val() == "") {
            if ($("#email").next().hasClass("message")) {
                $("#email").next().remove();
            }
            const message = "<span class=\"message fail\">이메일을 입력해주세요.</span>";
            $("#email").after(message);
            e.preventDefault();
        }
        if ($("#password").val() == "") {
            if ($("#password").next().hasClass("message")) {
                $("#password").next().remove();
            }
            const message = "<span class=\"message fail\">비밀번호를 입력해주세요.</span>";
            $("#password").after(message);
            e.preventDefault();
        }
        if ($(".mobile_number_check").val() == "") {
            if ($(".check-request").next().hasClass("message")) {
                $(".check-request").next().remove();
            }
            const message = "<span class=\"message fail\">핸드폰 번호를 입력해주세요.</span>";
            $(".check-request").after(message);
            e.preventDefault();
        }
        if ($(".check_number").val() == "") {
            if ($(".check_number_auth").next().hasClass("message")) {
                $(".check_number_auth").next().remove();
            }
            const message = "<span class=\"message fail\">인증 번호를 입력해주세요.</span>";
            $(".check_number_auth").after(message);
            e.preventDefault();
        }
        if ($(".check_number_auth").next().hasClass("message") && $(".check_number_auth").next().hasClass("fail")) {
            e.preventDefault();
        }

    })

    $(".change-password").click(function () {
        $(".info-container").html("").html(pass_change);
    });
    var pass_change = "    <div class=\"change-password-container\">\n" +
        "        <p class=\"head-title\">비밀번호 변경</p>\n" +
        "        <div class=\"change-password-body\">\n" +
        "            <form action=\"/\" method=\"post\">" +
        " <div class=\"orgin-password-area\">" +
        "                <span class=\"info-name\">기존 비밀번호</span>\n" +
        "                <input type=\"password\" class=\"origin-password eingangplatz\" name=\"oldPassword\" placeholder=\"현재 비밀번호를 입력해주세요.\"></div>\n" +
        "<div class=\"new-password-area\">" +
        "                <span class=\"info-name\">새로운 비밀번호</span>\n" +
        "                <input type=\"password\" class=\"new-password eingangplatz\" name=\"newPassword\" placeholder=\"영문+숫자+특수문자 조합 8자리이상 입력해주세요.\"></div>\n" +
        "<div class=\"new-password-check-area\">" +
        "                <span class=\"info-name\">새로운 비밀번호 확인</span>\n" +
        "                <input type=\"password\" class=\"new-password-check eingangplatz\" name=\"newPasswordCheck\"></div>\n" +
        "                <button class=\"change-password-btn \">변&nbsp;&nbsp;경&nbsp;&nbsp;하&nbsp;&nbsp;기</button>\n" +
        "            </form>\n" +
        "        </div>\n" +
        "    </div>";

    $(".mobile_number_check").keyup(function () {
        if (isNaN($(this).val())) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
        }
    });

    csrf = $("#csrf").val();
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
            }
            const message = "<span class=\"message fail\">올바른 핸드폰 번호를 적어주세요.</span>"
            $(this).after(message);
            return false;
        }
        if ($(".check-request").next().hasClass("check-area") || $(".check-request").next().hasClass("fail")) {
            return false;
        }
        if ($(".check-request").next().hasClass("message")) {
            $(this).next().remove();
        }
        console.log("test");
        SmsAuth(number);
        $(this).after(auth);
    });

    function SmsAuth(mobile) {
        $.ajax({
            type: "Post",
            url: "api/naver/mobileAuth",
            data: {
                mobile: mobile,
            },
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
        if ($(".check_number_auth").next().hasClass("message")) {
            $(this).next().remove();
        }
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
                } else {
                    const message = "<span class=\"message fail\">인증번호가 일치하지 않습니다.</span>";
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
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
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
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
            const message = "<span class=\"message fail\">비밀번호가 일치하지 않습니다.</span>";
            $(this).after(message);
        } else {
            $(this).next().remove();
            const message = "<span class=\"message ok\">비밀번호가 일치합니다.</span>";
            $(this).after(message);
        }
    });

    $(document).on("focusout", ".new-password-check" ,function () {
        pass = $(".new-password").val();
        pass_check = $(this).val();
        console.log("test");
        if (pass != pass_check) {
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
            const message = "<span class=\"message fail\">비밀번호가 일치하지 않습니다.</span>";
            $(this).after(message);
        } else {
            $(this).next().remove();
            const message = "<span class=\"message ok\">비밀번호가 일치합니다.</span>";
            $(this).after(message);
        }
    });

    $(document).on("keyup", ".new-password ",function () {
        regpassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/
        pass = $(this).val();
        if (!regpassword.test(pass)) {
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
            const message = "<span class=\"message fail\">영문+숫자+특수문자 조합 8자리이상 입력해주세요.</span>";
            $(this).after(message);
        } else {
            $(this).next().remove();
            const message = "<span class=\"message ok\">사용 가능한 비밀번호입니다.</span>";
            $(this).after(message);
        }
    });


    $(document).on("click", ".change-password-btn", function (e) {
        const old_password = $(".origin-password").val();
        const new_password = $(".new-password").val();
        const new_password_check = $(".new-password-check").val();
        e.preventDefault();
        if (old_password == "") {
            if ($(".origin-password").next().hasClass("message")) {
                $(".origin-password").next().remove();
            }
            const message = "<span class=\"message fail\">현재 비밀번호를 입력해주세요.</span>";
            $(".origin-password").after(message);
            return false;
        }
        if (new_password == "") {
            if ($(".new-password").next().hasClass("message")) {
                $(".new-password").next().remove();
            }
            const message = "<span class=\"message fail\">영문+숫자+특수문자 조합 8자리이상 입력해주세요.</span>";
            $(".new-password").after(message);
            return false;
        }
        if (new_password_check == "") {
            if ($(".new-password-check").next().hasClass("message")) {
                $(".new-password-check").next().remove();
            }
            const message = "<span class=\"message fail\">비밀번호를 입력해주세요.</span>";
            $(".new-password-check").after(message);
            return false;
        }
        if (new_password != new_password_check) {
            return false;
        }

        password_update(old_password, new_password);


    });

    function password_update(old_password, new_password) {
        $.ajax({
            type: "Post",
            url: "info/passwordupdate",
            data: { oldPassword: old_password,
                    newPassword: new_password},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.result == "Success") {
                    location.href = "/";
                } else if(data.oldPassword == "not matched") {
                    if ($(".origin-password").next().hasClass("message")) {
                        $(".origin-password").next().remove();
                    }
                    const message = "<span class=\"message fail\">비밀번호가 일치하지 않습니다.</span>";
                    $(".origin-password").after(message);
                }
            },
        });

    }






})

