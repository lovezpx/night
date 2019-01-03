$(function () {
    $.ajax({
        url: "/manager/menuManage/selectByParentId",
        data: {
            parentId: "dicManage"
        },
        async: false,
        success: function (data) {
            initDicMenu(data);
        }
    });

    function initDicMenu(data) {
        var dicMenu = '';
        data.forEach(function (c, i) {
            dicMenu = `<li data-type="${c.type}" data-url="${c.url}">${c.name}</li>`;
            $('#aside ul').append(dicMenu);
        });

        $("#aside ul li").on('click', function () {
            $(this).addClass('active_bar').siblings().removeClass('active_bar');
            $("#table").load($(this).data("url"));
        })
        $("#aside ul li:eq(0)").click();
    }
})