$(function () {
    $(".regist-container").on('click', ".all-check", (function () {
        if ($(".all-check").prop("checked") == true) {
            $(".service-check").prop("checked", true);
            $(".privateinfo-check").prop("checked", true);
            $(".consumer-check").prop("checked", true);
        } else {
            $(".service-check").prop("checked", false);
            $(".privateinfo-check").prop("checked", false);
            $(".consumer-check").prop("checked", false);
        }
    }))

    $(".regist-container").on('click', ".regist-main-btn", function () {
        if ($(".consumer-check").prop("checked") == true && $(".privateinfo-check").prop("checked") == true && $(".service-check").prop("checked") == true) {
            $(".regist-header-active:last").parent().next(":first").children(":first-child").addClass("regist-header-active");
            $(".regist-main").html("").html(account_email);
            return false;
        }
        if ($(".email_check").hasClass("checked_b")) {
            $(".regist-header-active:last").parent().next(":first").children(":first-child").addClass("regist-header-active");
            let email = $("#email").val();
            $(".regist-main").html("").html(account_regist);
            $("#email").val(email);
        } else if ($(".email_check").hasClass("checked_a")) {
            $(".regist-header-active:last").parent().next(":first").children(":first-child").addClass("regist-header-active");
            let email = $("#email").val();
            $(".regist-main").html("").html(account_regist_2);
            $("#email").val(email);
        } else {
            if ($(".btn-container").prev().hasClass("message")) {
                $(".btn-container").prev().remove();
            }
            if ($("#email").val() == "") {
                const message = "<span class=\"message fail\">이메일을 입력해주세요.</span>";
                $(".btn-container").before(message);
                return false;
            }
        }
    });

    $(".regist-container").on("click", ".email_check", function () {
        const pattern = /^\w+@\w+[.]\w{3}$/;
        const email = $("#email").val();
        if ($(".btn-container").prev().hasClass("message")) {
            $(".btn-container").prev().remove();
        }
        if ($("#email").val() == "") {
            const message = "<span class=\"message fail\">이메일을 입력해주세요.</span>";
            $(".btn-container").before(message);
            return false;
        }
        if (!pattern.test(email)) {
            const message = "<span class=\"message fail\">이메일 형식이 맞지 않습니다.</span>";
            $(".btn-container").before(message);
            return false;
        }
        email_check($("#email").val());
    })

    function email_check(email) {
        $.ajax({
            type: "Get",
            url: "/memberCheck",
            data: {email: email},
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data.result == "1") {
                    const message = "<span class=\"message ok\">등록된 이메일이 있습니다.<br>다음을 클릭하여 나머지 정보를 입력해주세요.</span>";
                    $(".btn-container").before(message);
                    $(".email_check").addClass("checked_a");
                } else {
                    const message = "<span class=\"message\">등록된 이메일이 없습니다.<br>일반회원 가입과 동시 가입됩니다.</span>";
                    $(".btn-container").before(message);
                    $(".email_check").addClass("checked_b");
                }

            }


        });
    }

    $(".regist-container").on("click", ".before", function () {
        if ($(".regist-main-title").text() == '호스트 정보') {
            $(".regist-header-active:last").removeClass("regist-header-active");
            $(".regist-main").html("").html(account_email);
        } else if ($(".regist-main-title").text() == '계정 정보') {
            $(".regist-header-active:last").removeClass("regist-header-active");
            $(".regist-main").html("").html(account_agree);
        }

    })

    const account_agree = '<div class="regist-main-title">이용 약관</div>' +
        '<div class="regist-main-info">프립 서비스 이용 계약을 포함하는 약관(이용약관, 개인정보 처리방침)이니 꼭 확인해주세요.' +
        "프립 서비스 이용 수수료는 [19.8% (PG 수수료 및 부가세 포함)]입니다.<br>" +
        "자세한 사항은 이용약관을 확인해주시기 바랍니다." +
        "</div>" +
        '<div class="regist-main-agreement">' +
        '<div class="custom-checkbox">' +
        '<input class="check-box all-check" type="checkbox" name="all-check" id="check-control">' +
        '<label for="check-control" class="check-label">' +
        "<span>전체 동의</span>" +
        "</label>" +
        "<input class=\"check-box privateinfo-check\" type=\"checkbox\" name=\"privateinfo-check\" id=\"privateinfo-check\">" +
        "<label for=\"privateinfo-check\" class=\"check-label\">" +
        "<span>[필수] 개인정보 처리방침</span>" +
        "</label>" +
        "<input class=\"check-box service-check\" type=\"checkbox\" name=\"service-check\" id=\"service-check\">" +
        "<label for=\"service-check\" class=\"check-label\">" +
        "<span>[필수] 이용약관</span>" +
        "</label>" +
        "<input class=\"check-box consumer-check\" type=\"checkbox\" name=\"service-check\" id=\"consumer-check\">" +
        "<label for=\"consumer-check\" class=\"check-label\">" +
        "<span>[필수] 이용약관(구매자)</span>" +
        "</label></div></div>" +
        "<button class=\"btn regist-main-btn \">다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;음</button>"

    const account_email = '<div class="regist-main-title">계정 정보</div>' +
        '<div class="signnup-form">' +
        '<span class="title-input">이메일</span>' +
        '<div class="email-platz">' +
        '<input class="text-input email-input" type="text" name="email" id="email" placeholder="이메일을 입력해주세요.">' +
        '<button type="button" class="btn btn-submit email_check">이메일 확인</button></div>' +
        '<div class="btn-container">' +
        '<button class="btn regist-main-btn before">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전</button>' +
        '<button class="btn regist-main-btn">다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;음</button></div>';


    const account_regist = '<div class="regist-main-title">호스트 정보</div>' +
        '<form method="post" action="registproc" class="signnup-form">' +
        ' <span class="title-input">이메일</span>\n' +
        '                <input class="text-input checked-email" type="text" name="email" id="email" readonly>\n' +
        '                <span class="title-input">비밀번호</span>\n' +
        '                <input class="text-input" type="password" name="password" id="password"\n' +
        '                       placeholder="영문+숫자+특수문자 조합 8자리이상 입력해주세요.">\n' +
        '                <span class="title-input">비밀번호 확인</span>\n' +
        '                <input class="text-input" type="password" name="password_check" id="password_check">\n' +
        '                <span class="title-input">핸드폰 번호</span>\n' +
        '                <div class="mobile-area">\n' +
        '                    <input class="text-input mobile_number_check" type="text" name="mobileNum"\n' +
        '                           placeholder="핸드폰 번호를 입력해주세요.">\n' +
        '                    <button type="button" class="btn btn-submit check-request">인증 요청</button>\n' +
        '                </div>'+
        '<span class="title-input">이름</span>' +
        '<input class="text-input" type="text" name="name" id="name" placeholder="이름을 입력해주세요.">' +
        '<span class="title-input">호스트명</span>' +
        '<input class="text-input" type="text" name="hostname" id="hostname" placeholder="호스트명을 입력해주세요.">' +
        '<div class="btn-container">' +

        '<button class="btn regist-main-btn before" type="button">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전</button>' +
        '<button class="btn regist-main-btn regist">등&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록</button></div>' +
        '</form>';


    const account_regist_2 = '<div class="regist-main-title">호스트 정보</div>' +
        '<form method="post" action="registproc" class="signnup-form">' +
        '<span class="title-input">이메일</span>' +
        '<input class="text-input checked-email" type="text" name="email" id="email" readonly>' +
        '<span class="title-input">호스트명</span>' +
        '<input class="text-input" type="text" name="hostname" id="hostname" placeholder="호스트명을 입력해주세요.">' +
        '<div class="btn-container">' +
        '<input type="hidden" id="csrf" th:if="${_csrf != null}" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">'+
        '<button class="btn regist-main-btn before" type="button">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전</button>' +
        '<button class="btn regist-main-btn regist">등&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록</button></div>' +
        '</form>';


    $(document).on('keyup','.mobile_number_check', function () {
        if (isNaN($(this).val())) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
        }
    });

    csrf = $("#csrf").val();
    $(document).on("click", ".check-request", function () {
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
            url: "/api/naver/mobileAuth",
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
            url: "/api/naver/mobileAuthOk",
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

    $(document).on("keyup", "#password", function () {
        regpassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/
        pass = $(this).val();
        if (!regpassword.test(pass)) {
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();
            }
            const message = "<span class=\"message fail\">영문+숫자+특수문자 조합 8자리이상 입력해주세요.</span>";
            $(this).after(message);
        } else {
            if ($(this).next().hasClass("message")) {
                $(this).next().remove();}
            const message = "<span class=\"message ok\">사용 가능한 비밀번호입니다.</span>";
            $(this).after(message);
        }
    });

    $(document).on("keyup", "#password_check", function () {
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


    $(document).on("click", ".regist-main-btn.regist",function (e) {
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
})
