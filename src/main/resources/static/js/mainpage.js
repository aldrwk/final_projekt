$(function () {
    $('.slider').slick({
        autoplay: true,
        autoplaySpeed: 3000,
        slidesToShow: 1,
        slidesToScroll: 1,
    });

    $(".bt-item").click(function () {
        $(".bt-item.on").removeClass('on');
        $(this).addClass('on').css("box-shadow", "none");
    });
});