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
        if ($(".consumer-check, .privateinfo-check, .service-check").prop("checked") == true) {
            $(".regist-header-active:last").parent().next(":first").children(":first-child").addClass("regist-header-active");
            $(".regist-main").html("").html(account_email);
        }
        if ($(".email_check").hasClass("checked")) {
            $(".regist-header-active:last").parent().next(":first").children(":first-child").addClass("regist-header-active");
            let email = $("#email").val();
            $(".regist-main").html("").html(account_regist);
            $("#email").val(email);
        }
    });

    $(".regist-container").on("click", ".email_check", function () {
        $(this).addClass("checked");
    })

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
        '<form action="host/registproc" class="signnup-form">' +
        '<span class="title-input">이메일</span>' +
        '<input class="text-input checked-email" type="text" name="email" id="email" readonly>' +
        '<span class="title-input">비밀번호</span>' +
        '<input class="text-input" type="password" name="password" id="password" placeholder="영문+숫자+특수문자 조합 8자리이상 입력해주세요.">' +
        '<span class="title-input">비밀번호 확인</span>' +
        '<input class="text-input" type="password" name="password_check" id="password_check">' +
        '<span class="title-input">이름</span>' +
        '<input class="text-input" type="text" name="name" id="name" placeholder="이름을 입력해주세요.">' +
        '<span class="title-input">호스트명</span>' +
        '<input class="text-input" type="text" name="hostname" id="hostname" placeholder="호스트명을 입력해주세요.">' +
        '<div class="btn-container">' +
        '<button class="btn regist-main-btn before" type="button">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전</button>' +
        '<button class="btn regist-main-btn">등&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;록</button></div>' +
        '</form>';

})
