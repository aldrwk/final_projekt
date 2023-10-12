$(function () {
    $('.slider').slick({
        autoplay: true,
        autoplaySpeed: 10000,
        slidesToShow: 1,
        slidesToScroll: 1,
    });

    $(".nav-left-frame").hover(function () {

    });
    $(".nav-left-frame").click(function () {
        $(".nav-left-frame").children().removeClass("on");
        $(this).children().addClass("on");
    });

    $("#profile").click(function () {
        getPage($(this).attr("href"));
        return false;
    });

    $("#konto").click(function () {
        getPage($(this).attr("href"));
        return false;
    });

    function getPage(url) {
        $.ajax({
            type: "Post",
            url: url,
        })
            .done(function (data) {
                $(".manage-frame").html(data);
            })
    }

    $(document).on("change", "#hostprofile", function (e) {
        const inputfile = $(this).val().split('\\');
        const filename = inputfile[inputfile.length - 1];		//inputfile.length - 1 = 2
        const pattern = /(gif|jpg|jpeg|png)$/i;						//i(ignore case) : 대소문자 무시를 의미한다

        if (pattern.test(filename)) {
            const reader = new FileReader();						//파일을 읽기 위해 객체 생성
            reader.readAsDataURL(e.target.files[0]);
            console.log(reader);
            reader.onload = function () {								//읽기에 성공한 경우 실행되는 이벤트 핸들러
                $('.manage-info-profile-img').attr('src', this.result).css('display', 'inline-block');

            };
        } else {
            alert('이미지 파일(gif, jpg, jpeg, png)가 아닌 경우 업로드되지 않습니다.');
            $(this).val('');
        }
    });

    $(document).on("click", ".konto-bank", function () {
        if ($(".konto-bank").hasClass("konto-select")) {
            $(this).removeClass("konto-select");
            $(".bank-list-area").css("display","none");
            return false;
        }
        $(".konto-bank").addClass("konto-select");
        $(".bank-list-area").css("display","inline-block");
    })

    $(document).on("click", ".bank", function () {
        const bank = $(this).children().text();
        $(".konto-bank").val(bank);
        $(".konto-bank").removeClass("konto-select");
        $(".bank-list-area").css("display","none");
    });
});