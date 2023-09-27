$(function () {
    const search_reset_bt = `<button type="button" class="search-remove">
                    <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3E %3Cg fill='none' fill-rule='nonzero'%3E %3Ccircle cx='8' cy='8' r='8' fill='%23BBB'/%3E %3Cpath fill='%23FFF' d='M7.823 7.116l2.475-2.475 1.06 1.06-2.474 2.476 2.298 2.298-1.06 1.06-2.299-2.298-2.121 2.122-1.06-1.06 2.12-2.122-2.298-2.298 1.061-1.061 2.298 2.298z'/%3E %3C/g%3E %3C/svg%3E"
                        alt="">
                </button>`;

    let isCancleButton = false;
    $('.search-data').on('keyup', function () {
        if (!isCancleButton) {
            $(this).after(search_reset_bt)
            isCancleButton = true;
        }
        if ($('.search-data').val() == "") {
            $('.search-remove').remove();
            isCancleButton = false;
        }
    });

    $(document).on('click', '.search-remove', function () {
        $('.search-data').val("");
        $('.search-remove').remove();
        isCancleButton = false;
    });
    $(".search").on('focus', '.search-data', function () {
        $('.search-box').css("display", "block");
        console.log("search box")
    })
    $(document).on('blur', '.search-data', function () {
        $('.search-box').css("display", "none");
    })

    $(".search").submit(function () {
        if ($(".search-data").val() == "") {
            return false;
        }
    })
    var currentUrl = document.URL;
    var urlObject = new URL(currentUrl);
    var path = urlObject.pathname;
    $(".login-bt").attr("href", "/login?redirectPath=" + path);

    $(".navi-list").on("click", ".user-menu", function () {
        console.log("test")
        $(".menu-box-container").css("display", "block");
    })

    $(document).on('click', document, function (event) {
        const target = event.target;
        if ($(target).hasClass("user-menu") || $(target).parent().hasClass("user-menu")) {
            return false;
        }
        $('.menu-box-container').css("display", "none");
    });

})