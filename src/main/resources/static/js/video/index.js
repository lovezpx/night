$(function () {
    createMenu();
    initTrailer();
})

function createMenu(data) {
    $.ajax({
        url: "/video/dicVideoType/selectByIsUsing",
        data: {
            isusing: 1
        },
        async: false,
        success: function (data) {
            localStorage.removeItem('video-pagefrom');
            var headStr = "";
            $videoHead = $("#videoHead");
            for (let i = 0; i < data.length; i++) {
                headStr += `<li data-type="${data[i].type}" data-remark="${data[i].remark}">${data[i].name}</li>`
            }
            $videoHead.append(headStr);

            $('#videoHead li').on('click', function () {
                $(this).addClass('active_ul').siblings().removeClass('active_ul');
                localStorage.setItem('video-type', $(this).data("type"));
                localStorage.setItem('video-pagefrom', "videoType");
                $("#videoContent").load('/video/videoWall');
            })
            $('#videoHead li:eq(0)').click();
        }
    });
}

function initTrailer() {
    $("#trailerContent").load('/video/trailer');
}