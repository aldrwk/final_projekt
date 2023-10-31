$(function () {
    $('.slider').slick({
        autoplay: true,
        autoplaySpeed: 10000,
        slidesToShow: 1,
        slidesToScroll: 1,
    });

    if (result == "fail") {
        alert("예약 변경에 실패하였습니다. 이미 예약된 일정이 있는지 확인바랍니다.");
    }

    $(".nav-left-frame").hover(function () {

    });
    $(".nav-left-frame").click(function () {
        menu = $(this).attr("id")
        if ($(this).children().hasClass("on")) {
            return false;
        }
        $(".nav-left-frame").children().removeClass("on");
        $(this).children().addClass("on");
        getPage($("#" + menu).attr("href"));
        return false;
    });

    $(".btn-free-regist").click(function () {
        $("#free-regist").click();
    });
    $(document).on("click", ".action-btn", function () {
        if ($(this).hasClass("registed-free-move")) {
            return true;
        }
        getPage($(this).attr("href"));
        // if (firstCategoryName == "" || firstCategoryName == null) {
        //     getSecondCategory(firstCategoryName);
        // }
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
            closeBankList()
            return false;
        }
        openBankList()
    })
    $(document).on("click", ".icon-arrow", function () {
        if ($(".konto-bank").hasClass("konto-select")) {
            closeBankList()
            return false;
        }
        openBankList()
    })

    $(document).on("click", ".bank", function () {
        const bank = $(this).children().text();
        $(".konto-bank").val(bank);
        closeBankList()
    });

    function closeBankList() {
        $(".konto-bank").removeClass("konto-select");
        $(".bank-list-area").css("display", "none");
    }

    function openBankList() {
        $(".konto-bank").removeClass("konto-select");
        $(".bank-list-area").css("display", "inline-block");
    }

    function closeList(id) {
        $("#" + id).removeClass("selected");
        $("." + id + "-list-area").css("display", "none");
    }

    function openList(id) {
        $("#" + id).removeClass("selected");
        $("." + id + "-list-area").css("display", "inline-block");
    }

    $(document).on("click", ".catef", function () {
        if ($(this).hasClass("selected")) {
            closeList("categoryf");
            return false;
        }
        openList("categoryf");
    })

    $(document).on("click", ".categoryf", function () {
        const category = $(this).children().text();
        $("#categoryf").val(category);
        getSecondCategory(category)
        closeList("categoryf")
    });


    $(document).on("click", ".cates", function () {
        if ($(this).hasClass("selected") || $(this).hasClass("category-disable")) {
            closeList("categorys");
            return false;
        }
        openList("categorys");
    })

    $(document).on("click", ".categorys", function () {
        const category = $(this).children().text();
        $("#categorys").val(category);

        closeList("categorys")
    });

    function getSecondCategory(name) {
        secondCategoryjson = JSON.parse(secondCategory);
        const keywords = Object.keys(secondCategoryjson);
        console.log(keywords)
        console.log(secondCategoryjson[name]);
        console.log(secondCategoryjson[name][0]);
        categorys = "";
        for (categoryname of secondCategoryjson[name]) {
            categorys += "<li class=\"category categorys\">" +
                "<span>"+categoryname+"</span></li>"
        }
        $(".cates").removeClass("category-disable");
        closeList("categorys");
        $(".categorys-list").children().remove();
        $(".cates").val("2차카테고리");
        $(".categorys-list").append(categorys);
    }
    console.log(totalProfit)
    totalProfit = totalProfit.toLocaleString();
    $("#totalprofit").text(totalProfit + " 원");
    profitInThisMonth = profitInThisMonth.toLocaleString();
    $("#profitInThisMonth").text(profitInThisMonth + " 원");


});