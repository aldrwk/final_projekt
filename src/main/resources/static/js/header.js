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
    $(".").on('focus', '.search-data', function () {
        $('.search-box').css("display", "block");
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

    $(".navi-list").on("click", ".user-menu",function () {
        console.log("test")
        $(".box-area").after(menu_box);
    });

    var menu_box="    <div th:if=\"${not #lists.isEmpty(session.user_info)}\" th:with=\"info=${session.user_info}\">\n" +
        "        <div class=\"menu-box-container\">\n" +
        "            <div class=\"menu-box-area\">\n" +
        "                <div class=\"user-name-box\">\n" +
        "                    <span class=\"user-name\">[[${info.nickname}]] 님</span>\n" +
        "                </div>\n" +
        "                <div class=\"menu-text-area\">\n" +
        "                    <a class=\"menu-text\" href=\"mypage\">마이 페이지</a>\n" +
        "                </div>\n" +
        "                <div class=\"logout-area\">\n" +
        "                    <a class=\"menu-text\" href=\"logout\">로그아웃</a>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>";

});