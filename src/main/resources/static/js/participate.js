$(function () {
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
                    start: today,
                    end: endDate
                }
            }
        }, eventOverlap: false, editable: false, droppable: false,
        initialView: 'dayGridMonth', selectMirror: true, locale: "ko", fixedWeekCount: false, validRange: {
            start: new Date(),
        }, eventClick: function (data) {
            const start = data.event._instance.range.start;
            const end = data.event._instance.range.end;
            const maxPeople = data.event.extendedProps.maxPeople;
            const maxPerson = data.event.extendedProps.maxPerson;
            const id = data.event._def.publicId;
            $("#reservId").val(id);
            getEventOption(start, end, id);
        },
    })
    calendar.render();

    for (date of dates) {
        calendar.addEvent({
            id: date.reservationId,
            start: date.reservDate,
            end: date.endDate,
            allDay: false,
            extendedProps: {
                maxPeople: date.maxPeople, maxPerson: date.maxPerson
            }
        })
    }

    function getEventOption(start, end, id) {
        $(".option-list").remove();
        for (option of options) {
            if (option.reservationId == id) {
                var startDate = new Date(start.getTime() - (9 * 60 * 60 * 1000)); // GMT+9
                var endDate = new Date(end.getTime() - (9 * 60 * 60 * 1000)); // GMT+9
                EventMonth = startDate.toLocaleString().split(".")[1];
                EventDay = startDate.toLocaleString().split(".")[2];
                EventDate = EventMonth + "월 " + EventDay + "일";
                $(".date-selected").text("").text(EventDate);
                startDate = startDate.toLocaleString().split(" ");
                startTime = startDate[4].split(":");
                startTime = startDate[3] + " " + startTime[0] + ":" + startTime[1];
                endDate = endDate.toLocaleString().split(" ");
                endTime = endDate[4].split(":");
                endTime = endDate[3] + " " + endTime[0] + ":" + endTime[1];
                EventTime = startTime + " ~ " + endTime;
                localprice = parseInt(option.price).toLocaleString();
                optionHtml = "<div class=\"option-list\">" +
                    "<div class=\"option-title\">" + option.optionName + "</div>" +
                    "<span class=\"option-zeit\">" + EventTime + "</span>" +
                    "<div class=\"option-price\">" + localprice + " 원" + "</div>" +
                    "<span style='display: none'>" + option.optionId + "</span>"
                "</div>";
                $(".date-header").after(optionHtml);
            }
        }
    }
});
