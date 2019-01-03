$(function () {
    var pageSize = 5;
    randerTrailerList();
    randerLabelList();

    function randerTrailerList() {
        layui.use("flow", function () {
            var flow = layui.flow;
            flow.load({
                elem: "#trailer ul",
                scrollElem: "#trailer ul",
                isAuto: true,
                done: function (page, next) {
                    $.ajax({
                        url: "/video/videoTrailerServer/selectAll",
                        async: false,
                        success: function (result) {
                            var datas = result.slice((page - 1) * pageSize, page * pageSize);

                            var lis = [];
                            $.each(datas, function (idx, data) {
                                lis.push(`<li data-vgid="${data.vgid}">
                                        <img src='` + encodeURI(data.coverSrc) + `'>
                                        <div>
                                            <p>${data.name}</p>
                                            <p>作者:<span>${data.author}</span></p>
                                            <p>上传时间:` + timeStamp2String(new Date(data['uploadtime']), "yyyy-MM-dd") + `</p>
                                        </div>
                                      </li>`)
                            });
                            next(lis.join(''), page < Math.ceil(result.length / pageSize));

                            $("#trailer li").off('click').on('click', function () {
                                var vgid = $(this).data("vgid");
                                $("#videoContent").empty().load("/video/video");
                                localStorage.setItem("video-vgid", vgid);
                            })
                        }
                    });
                }
            })
        })
    }

    function randerLabelList() {
        $.ajax({
            url: "/video/videoTipServer/selectAll",
            async: false,
            success: function (result) {
                $.each(result, function (idx, val) {
                    $(".labels ul").append(`<li data-tipname=${val.tipname}>${val.tipname}</li>`);
                });

                $("#labels ul").on("click", 'li', function () {
                    localStorage.setItem('video-tipname', $(this).data("tipname"));
                    localStorage.setItem('video-pagefrom', "videoTip");
                    $("#videoContent").load('/video/videoWall');
                });
            }
        });
    }
})

