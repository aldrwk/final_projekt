$(function () {
    $(".teilnahme-zahl-area").on("click", ".option-popup-cnt-down", function () {
        let cnt = parseInt($(this).parent().children().eq(1).val());
        if (cnt <= 0) {
            return false;
        }
        $(this).parent().children().eq(1).val(cnt - 1);
        $(this).parent().children().eq(1).attr("value", $(this).parent().children().eq(1).val())
    });
    $(".teilnahme-zahl-area").on("click", ".option-popup-cnt-up", function () {
        let cnt = parseInt($(this).parent().children().eq(1).val());
        $(this).parent().children().eq(1).val(cnt + 1);
        $(this).parent().children().eq(1).attr("value", $(this).parent().children().eq(1).val())
    });

    $(document).on("blur", ".end", function () {
        console.log("test");
        date_check();
    });
    $(document).on("blur", ".start", function () {
        console.log("test");
        date_check();
    });

    $(document).on("click", ".schadule-box", function () {
        if ($(".schadule-box").hasClass("schadule-select")) {
            closeSchaduleList()
            return false;
        }
        openSchaduleList()
    })
    $(document).on("click", ".icon-arrow", function () {
        if ($(".schadule-box").hasClass("schadule-select")) {
            closeSchaduleList()
            return false;
        }
        openSchaduleList()
    })


    $(document).on("click", ".schadule-type", function () {
        const type = $(this).children().text();
        // $(".schadule-box").val(type);
        $(".schadule-box").attr("value", type);
        console.log("test");
        if ($(".schadule-box").val() == "매주") {
            if ($(".day-area").hasClass("day-area")) {
                closeSchaduleList();
                return false;
            }
            const day = ["월", "화", "수", "목", "금", "토", "일"];
            var day_area = "<div class=\"day-area frame\">"
            for (i = 0; i < day.length; i++) {
                day_area += "<button class=\"day-box\">" + day[i] + "</button>";
            }
            day_area += "</div>";
            $("#reserv_date").removeClass("disable");
            $(".everyday").remove();
            $(".schadule-box").parent().next().after(day_area);
        }
        if ($(".schadule-box").val() == "매일") {
            if ($(".everyday").hasClass("everyday")) {
                closeSchaduleList();
                return;
            }
            const everyday = " <div class=\"everyday frame\">           <div class=\"start-area\">\n" +
                "                    <input type=\"date\" class=\"form-control schadule-input start\" name=\"startDay\"\n" +
                "                           id=\"startDay\">\n" +
                "                </div>\n" +
                "                <span style=\"line-height: 50px; padding: 0 20px\">~</span>\n" +
                "                <div class=\"end-area\">\n" +
                "                    <input type=\"date\" class=\"form-control schadule-input end\" name=\"endDay\" id=\"endDay\">\n" +
                "                </div></div>";
            // $("#reserv_date").addClass("disable");
            $(".day-area").remove();
            const message = "<span class=\"message everyday\">당일부터 3개월까지 매일 일정이 등록 됩니다.</span>";
            $(".schadule-box").parent().next().after(message);
        }
        if ($(".schadule-box").val() == "반복없음") {
            $("#reserv_date").removeClass("disable");
            $(".everyday").remove();
            $(".day-area").remove();
        }
        closeSchaduleList();
    });

    $(document).on("click", ".day-box", function () {
        console.log($(this));
        if ($(this).hasClass("checked")) {
            $(this).removeClass("checked");
            return false;
        }
        $(this).addClass("checked");
    });


    function closeSchaduleList() {
        $(".schadule-box").removeClass("schadule-select");
        $(".schadule-type-area").css("display", "none");
    }

    function openSchaduleList() {
        $(".schadule-box").removeClass("konto-select");
        $(".schadule-type-area").css("display", "inline-block");
    }


});


document.addEventListener('DOMContentLoaded', function () {
    var today = new Date();
    var endDate = new Date(today);
    endDate.setMonth(endDate.getMonth() + 3);

    const calendarEl = document.getElementById('calendar')
    const calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev',
            center: 'title',
            right: 'next'
        },
        views: {
            month: {
                validRange: {
                    start: today,     // 현재 날짜
                    end: endDate       // 3개월 후의 날짜
                }
            }
        },
        eventOverlap: false,
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar
        initialView: 'dayGridMonth',
        selectMirror: true,
        locale: "ko",
        fixedWeekCount: false,
        validRange: {
            start: new Date(), // 현재 날짜 이후의 날짜만 허용
        },
        navLinks: true,
        navLinkDayClick: function (date) {
            datearr = date.toLocaleString().split(".")
            date = datearr[0].trim() + "-" + datearr[1].trim() + "-" + datearr[2].trim();
            $("#reserv_date").val(date);
            $(".regist-footer").removeClass("disable");
            $(".update-footer").addClass("disable");
            schadule_reset();
            $(".btn-primary.schedule-modal").click();
            console.log($(".bnt-modal.schedule-regist").click())
        },
        eventClick: function (data) {
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


    $(document).on("click", ".btn-schadule-add", function () {
        const date = $("#reserv_date").val();
        const start = $("#start").val();
        const end = $("#end").val();
        const maxPeople = $(".maxPeople").val();
        const maxPerson = $(".maxPerson").val();
        console.log(maxPeople, maxPerson);
        if (!person_check(maxPeople, maxPerson)) {
            return false;
        }
        if (!date_check()) {
            console.log("test")
            return false;
        }
        calendar.addEvent({
            id: date + " " + start,
            start: date + "T" + start,
            end: date + "T" + end,
            allDay: false,
            daysOfWeek: ['0'],
            extendedProps: {
                maxPeople: maxPeople,
                maxPerson: maxPerson
            }
        })
        $(".close").click();
    });

    $(document).on("click", ".btn-schadule-update", function () {
        const date = $("#reserv_date").val();
        const start = date + "T" + $("#start").val();
        const end = date + "T" + $("#end").val();
        const maxPeople = $("#maxPeople").val();
        const maxPerson = $("#maxPerson").val();
        if (!person_check(maxPeople, maxPerson)) {
            return false;
        }
        if (!date_check()) {
            return false;
        }
        calendar_update = calendar.getEventById(id);
        calendar_update.setDates(start, end);
        calendar_update.setExtendedProp("maxPeople", maxPeople);
        calendar_update.setExtendedProp("maxPerson", maxPerson);
        $(".close").click();
    });

    $(document).on("click", ".btn-regist", function () {
        var allEvent = calendar.getEvents(); // .getEvents() 함수로 모든 이벤트를 Array 형식으로 가져온다. (FullCalendar 기능 참조)
        var events = new Array(); // Json 데이터를 받기 위한 배열 선언
        for (var i = 0; i < allEvent.length; i++) {
            var obj = new Object();     // Json 을 담기 위해 Object 선언
            // alert(allEvent[i]._def.title); // 이벤트 명칭 알람
            obj.id = allEvent[i]._def.publicId; // 이벤트 명칭  ConsoleLog 로 확인 가능.
            obj.start = allEvent[i]._instance.range.start; // 시작
            obj.end = allEvent[i]._instance.range.end; // 끝
            obj.maxPeople = allEvent[i].extendedProps.maxPeople;
            obj.maxPerson = allEvent[i].extendedProps.maxPerson;
            events.push(obj);
        }
        var jsondata = JSON.stringify(events);
        console.log(jsondata);
    });

    $(".btn-delete").click(function () {
        schedule_remove(id);
    });

    function schedule_remove(data) {
        calendar.getEventById(data).remove();
        $(".close").click();
    }
})

function date_check() {
    const start = $(".start").val();
    const end = $(".end").val();
    if (start > end) {
        if ($(".end").parent().next().hasClass("message")) {
            return false;
        }
        const message = "<span class=\"message time\">종료시간은 시작시간보다 빠를 수 없습니다.</span>";
        $(".end").parent().after(message);
        return false;
    }
    if (end == "" ||start == "") {
        if ($(".end").parent().next().hasClass("message")) {
            return false;
        }
        const message = "<span class=\"message time\">운영 시간을 설정하시길 바랍니다.</span>";
        $(".end").parent().after(message);
        return false;
    }
    if (start < end) {
        $(".message.time").remove();
        return true;
    }
}

function person_check(maxPeople, maxPerson) {
    if (maxPerson > maxPeople) {
        if ($(".teilnahme-zahl-area").eq(1).next().hasClass("message")) {
            return false;
        }
        const message = "<span class=\"message person\">인당 최대 등록 인원은 최대 모집 인원을 초과할 수 없습니다.</span>";
        $(".teilnahme-zahl-area").eq(1).after(message);
    }
    if (maxPeople == "0" || maxPerson == "0") {
        if ($(".teilnahme-zahl-area").eq(1).next().hasClass("message")) {
            return false;
        }
        const message = "<span class=\"message person\">인원 설정을 하시길 바랍니다.</span>";
        $(".teilnahme-zahl-area").eq(1).after(message);
    }
    if (maxPerson <= maxPeople) {
        $(".message.person").remove();
        return true;
    }
}

function schadule_reset() {
    $(".schadule-box").attr("value", "반복없음");
    $("#start").val("");
    $("#end").val("");
    $(".maxPerson").val("0");
    $(".maxPeople").val("0");
}








