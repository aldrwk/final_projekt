$(function () {
    $(document).on('click', ".all-check", (function () {
        if ($(".all-check").prop("checked") == true) {
            $(".service-check").prop("checked", true);
            $(".privateinfo-check").prop("checked", true);
        } else {
            $(".service-check").prop("checked", false);
            $(".privateinfo-check").prop("checked", false);
        }


    }))
})