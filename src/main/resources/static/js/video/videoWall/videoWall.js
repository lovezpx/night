var totalPage = 1;
var pageSize = 18;
var pageNum = 1;
var datas = [];

$(function () {
    initVideoWall();

    initVideoWallSearch();

    $(".videoDiv").on('click', function (e) {
        var vgid = $(this).data("vgid");
        $("#videoContent").empty().load("/video/video");
        localStorage.setItem("video-vgid", vgid);
    })
})

function initVideoWall() {
    var type = localStorage.getItem('video-type');
    var tipname = localStorage.getItem('video-tipname');
    var pagefrom = localStorage.getItem('video-pagefrom');
    var breadcrumb = "";

    var url = "";
    var data = {};
    if (pagefrom == "videoType") {
        if (type == null) {
            url = "/video/videoGroupServer/selectAll";
            breadcrumb = "";
        } else {
            url = "/video/videoGroupServer/selectByType";
            data = {
                type: localStorage.getItem('video-type')
            };

            breadcrumb = $("#videoHead").find("li[data-type='" + localStorage.getItem('video-type') + "']").text();
        }
    } else if (pagefrom == "videoTip") {
        url = "/video/videoGroupServer/selectByVideoTip";
        data = {
            tipname: tipname
        };
        breadcrumb = tipname;
    }

    $.ajax({
        url: url,
        data: data,
        async: false,
        success: function (result) {
            totalPage = Math.ceil(result.length / pageSize) == 0 ? 1 : Math.ceil(result.length / pageSize);
            $('#breadcrumb').html(`当前位置 > 视频墙 > ` + breadcrumb);
            $('#total').html(`影片合计:${result.length}部`);
            $('#pageTotal').html(`${totalPage}`);

            datas = result;
            generateMoive();
        }
    });
}

function generateMoive() {
    $("#movieWall").empty();
    var pageData = datas.slice((pageNum - 1) * pageSize, pageNum * pageSize);
    for (var i = 0; i < pageData.length; i++) {
        var videoStr = `<figure>
                            <div class='videoDiv' data-vgid='${pageData[i]['id']}'>
                                <p class="videoText videoTitle">标题:${pageData[i]['name']}</p>
                                <div>
                                    <img class="cover" src='` + encodeURI(pageData[i]['coverSrc']) + `'>
                                </div>
                                <p class="videoText">番号:<span>${pageData[i]['numcode']}</span></p>
                                <p class="videoText">上传时间: ` + timeStamp2String(new Date(pageData[i]['updatetime']), "yyyy-MM-dd") + `</p>
                            </div>
	 	                </figure>`;
        $("#movieWall").append(videoStr);
    }
}

function initVideoWallSearch() {
    $("#videoSearch").on('keydown', function (e) {
        if ($("#videoSearch").val() && e.keyCode == 13) {
            $.ajax({
                url: "/video/videoGroupServer/selectByKey",
                data: {
                    key: $("#videoSearch").val()
                },
                async: false,
                success: function (result) {
                    totalPage = Math.ceil(result.length / pageSize);
                    $('#breadcrumb').html(`当前位置 > 视频墙   <span class="keyTip">$关键词$: ` + $("#videoSearch").val() + `</span>`);
                    $('#total').html(`影片合计:${result.length}部`);
                    $('#pageTotal').html(`${totalPage}`);

                    datas = result;
                    generateMoive();
                }
            });
        }
    })
}

//上一页与下一页
$(function () {
    $('#num').val(1);
    $("#previous").hide();
    if (1 == totalPage) {
        $("#next").hide();
    }
    $("#next").on('click', function () {
        var num = Number($('#num').val());
        $('#num').val(num + 1);
        pageNum += 1;

        generateMoive();

        pagePluginsController();
    })
    $("#previous").on('click', function () {
        var num = Number($('#num').val());
        $('#num').val(num - 1);
        pageNum -= 1;

        generateMoive();

        pagePluginsController();
    })

    $(document).on('keydown', function (e) {
        if ($("#num").get(0) == document.activeElement && e.keyCode == 13) {
            var num = pageRangeLimit($("#num").val());
            if (num.toString() == 'NaN') {
                $("#num").val(1);
                return;
            }
            pageNum = num;
            $("#num").val(pageNum);
            generateMoive();

            pagePluginsController();
        }
    })

    function pageRangeLimit(obj) {
        var num = Number(obj);
        if (num < 1)
            num = 1;
        if (num > totalPage)
            num = totalPage;
        return num;
    }

    function pagePluginsController() {
        if (pageNum == 1) {
            $('#previous').hide();
        }

        if (pageNum > 1) {
            $('#previous').show();
        }

        if (pageNum < totalPage) {
            $('#next').show();
        }

        if (pageNum == totalPage) {
            $('#next').hide();
        }
    }
})