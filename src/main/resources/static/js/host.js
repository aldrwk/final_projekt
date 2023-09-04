$(function () {
    $(".custom-checkbox").on('click', ".all-check", (function () {
        console.log("test");
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
})