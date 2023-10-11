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

    $("#profile").click(function (event) {
        getProfile(1);
        return false;
    });

    function getProfile(hostnum) {
        console.log("test test");
        $.ajax({
            type: "Post",
            url: "/host/profile",
            data: {
                hostNum: hostnum,
            }
        })
            .done(function (data) {
                $(".manage-frame").html(data);
            })
    }


});