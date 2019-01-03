$(function() {
    initTopMenu();
});

function initTopMenu(){
    $.ajax({
        url: "/night/menu/selectByParentId",
        data: {
            parentId: "subsystemList"
        },
        async: false,
        success: function (data) {
            createMenu(data);
        }
    })
}

function createMenu(data) {
    var headStr = "";
    $head = $("#head");

    for (let i = 0; i < data.length; i++) {
        headStr += `<li data-target="${data[i].target}" data-url="${data[i].url}">${data[i].name}</li>`
    }

    $head.append(headStr);
    $('#head li').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
        if ($(this).data("target") == "_top") {
            top.window.location.href = $(this).data("url");
            return;
        }
        $('#content').load($(this).data("url"));
    })
    $('#head li:eq(0)').click();
}