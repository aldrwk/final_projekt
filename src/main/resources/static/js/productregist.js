$(function () {
    $(document).on("click",".regist-menu",function () {
        $(".checked").removeClass("checked");
        $(this).addClass("checked");
        if ($(".checked").text() == "기본 정보") {
            $(".clicked").removeClass("clicked");
            $(".default-info").addClass("clicked");
        }
        if ($(".checked").text() == "판매 정보") {
            $(".clicked").removeClass("clicked");
            $(".sales-info").addClass("clicked");
        }
        if ($(".checked").text() == "프리 설명") {
            $(".clicked").removeClass("clicked");
            $(".content-info").addClass("clicked");
        }
    });

    $(document).on("keyup", "#title", function () {
        titlelength = $("#title").val().length;
        $(".text-length").text("");
        $(".text-length").text(titlelength + "/40");
    });

    $(document).on("click", ".btn-address-search", function () {
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                $(".address").val(data.address); // 주소 넣기
            }
        }).open();
    });

    $(document).on("change", "#thumnail", function (e) {
        const inputfile = $(this).val().split('\\');
        const filename = inputfile[inputfile.length - 1];
        const pattern = /(gif|jpg|jpeg|png)$/i;
        if (pattern.test(filename)) {
            const reader = new FileReader();
            reader.readAsDataURL(e.target.files[0]);
            console.log(reader);
            reader.onload = function () {
                $('.thumnail-img').attr('src', this.result).css('display', 'inline-block').addClass("addImg").next().remove();
            };
        } else {
            alert('이미지 파일(gif, jpg, jpeg, png)가 아닌 경우 업로드되지 않습니다.');
            $(this).val('');
        }
    });









});