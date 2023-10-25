$(function () {

    $(document).on("click", ".regist-menu", function () {
        $(".checked").removeClass("checked");
        $(this).addClass("checked");
        if ($(".checked").text() == "기본 정보") {
            $(".clicked").removeClass("clicked");
            $(".default-info").addClass("clicked");
        }
        if ($(".checked").text() == "판매 정보") {
            $(".clicked").removeClass("clicked");
            $(".sales-info").addClass("clicked");
            if (!$("#calendar").hasClass("fc")) {
                callCalendar();
            }
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
            oncomplete: function (data) { //선택시 입력값 세팅
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

    $(document).on("keyup", ".preis", function (e) {
        if (isNaN($(this).val())) {
            $(this).val($(this).val().substring(0, $(this).val().length - 1));
        }
    });

    $(document).on("click", ".option.add", function () {
        const option_add = "<div class=\"option-row\">" +
            "<div class=\"option-box\">\n" +
            "<span class=\"option-name\">옵션명</span>\n" +
            "<input type=\"text\" class=\"manage-info-input option-input optionName\" maxlength=\"20\" name=\"optionName\">\n" +
            "</div>\n" +
            "<div class=\"option-box\">\n" +
            "<span class=\"option-name\">판매가</span>\n" +
            "<input type=\"text\" class=\"manage-info-input option-input preis price\" maxlength=\"12\" id=\"price\" name=\"price\">\n" +
            "<span class=\"preis-won\">원</span>\n" +
            "</div>\n" +
            "<div class=\"option-box option-btn-frame\">\n" +
            "<button class=\"option add\" type=\"button\">\n" +
            "<img class=\"option-add-img\" src=\"/image/util/add.85e31315.svg\">\n" +
            "</button>\n" +
            " <button class=\"option delete\" type=\"button\">\n" +
            " <img class=\"option-delete-img\" src=\"/image/util/delete.6d6ddd61.svg\">\n" +
            " </button></div></div>"

        if ($(".option-row").length == 5) {
            return false;
        }
        ;
        $(".option-row:last").after(option_add);
    });

    $(document).on("click", ".option.delete", function () {
        $(this).parent().parent().remove();
    });

    function callCalendar() {
        var today = new Date();
        var endDate = new Date(today);
        endDate.setMonth(endDate.getMonth() + 3);
        const calendarEl = document.getElementById('calendar')
        calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
                left: 'prev', center: 'title', right: 'next'
            }, views: {
                month: {
                    validRange: {
                        start: today,     // 현재 날짜
                        end: endDate       // 3개월 후의 날짜
                    }
                }
            }, eventOverlap: false, editable: true, droppable: true, // this allows things to be dropped onto the calendar
            initialView: 'dayGridMonth', selectMirror: true, locale: "ko", fixedWeekCount: false, validRange: {
                start: new Date(), // 현재 날짜 이후의 날짜만 허용
            }, navLinks: true, navLinkDayClick: function (date) {
                datearr = date.toLocaleString().split(".")
                console.log(datearr);
                day = datearr[2].trim();
                month = datearr[1].trim();
                if (day.length == 1) {
                    day = 0 + day;
                }
                if (month.length == 1) {
                    month = 0 + month;
                }
                date = datearr[0].trim() + "-" + month + "-" + day;
                $("#reserv_date").val(date);
                $(".regist-footer").removeClass("disable");
                $(".update-footer").addClass("disable");
                schadule_reset();
                $(".btn-primary.schedule-modal").click();
                console.log($(".bnt-modal.schedule-regist").click())
            }, eventClick: function (data) {
                console.log(data);
                const start = data.event._instance.range.start.toISOString().split("T");
                const end = data.event._instance.range.end.toISOString().split("T");
                const maxPeople = data.event.extendedProps.maxPeople;
                const maxPerson = data.event.extendedProps.maxPerson;
                id = data.event._def.publicId;
                startTime = start[1].split(".");
                endTime = end[1].split(".");
                $("#reserv").val(start[0]);
                $("#start").val(startTime[0]);
                $("#end").val(endTime[0]);
                $(".maxPerson").val(maxPerson);
                $(".maxPeople").val(maxPeople);
                $(".regist-footer").addClass("disable");
                $(".update-footer").removeClass("disable");
                $(".bnt-modal.schedule-regist").click();
            },
        })
        calendar.render();
    }


    $(document).on("click", ".product-submit", function () {
        var allEvent = calendar.getEvents(); // .getEvents() 함수로 모든 이벤트를 Array 형식으로 가져온다. (FullCalendar 기능 참조)
        var events = new Array(); // Json 데이터를 받기 위한 배열 선언
        for (var i = 0; i < allEvent.length; i++) {
            var obj = new Object();     // Json 을 담기 위해 Object 선언
            obj.id = allEvent[i]._def.publicId; // 이벤트 명칭  ConsoleLog 로 확인 가능.
            obj.start = allEvent[i]._instance.range.start; // 시작
            obj.end = allEvent[i]._instance.range.end; // 끝
            obj.maxPeople = allEvent[i].extendedProps.maxPeople;
            obj.maxPerson = allEvent[i].extendedProps.maxPerson;
            events.push(obj);
        }
        var jsondata = JSON.stringify(events);
        var options = new Array();
        for (var i = 0; i < $(".optionName").length; i++) {
            var obj = new Object();
            obj.optionName = $(".optionName").eq(i).val();
            obj.price = $(".price").eq(i).val();
            obj.validPerson = $("#validPerson").val();
            obj.maxRegisterPerOne = $("#maxRegisterPerOne").val();
            options.push(obj);
        }
        var optionJson = JSON.stringify(options);
        console.log(optionJson);
        $("#events").val(jsondata);
        $("#options").val(optionJson);
        $("#product_regist").submit();
    });

});