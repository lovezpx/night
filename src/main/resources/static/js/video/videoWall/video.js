var curClickElementAVID, curClickElementURL;
$(function () {
    initPage();
});

function initPage() {
    $("#breadcrumb").html(`当前位置 > 资料库 > 视频详情`);

    $.ajax({
        url: "/video/videoGroupServer/selectByPrimaryKey",
        data: {
            "id": localStorage.getItem('video-vgid')
        },
        async: false,
        success: function (result) {
            randerPage(result);
        }
    });
}

function randerPage(result) {
    doVideoDetail(result);
    doVideoPlayer();

    $(".tab").on('click', function (e) {
        $(this).addClass('tabActive').siblings().removeClass('tabActive')
        var type = $(this).data("type");
        tabClick(type, result);
    });

    $(".tab:eq(0)").click();

    function tabClick(type, result) {
        if (type == 'detail') {
            $("#videoDetail").show();
            $("#videoPlayContent").hide();
        }
        if (type == 'player') {
            $("#videoDetail").hide();
            $("#videoPlayContent").show();

            $("[name='videoTab']").eq(0).click();
        }
    }
}

function doVideoDetail(result){
    var imgStr = `<img class="videoImg" src='` + encodeURI(result.coverSrc) + `' alt=''/>`;
    $("#videoDetail").append(imgStr);
}

function doVideoPlayer(){
    $("#videoPlayer").empty().append("<video class='videoWindow' src='' controls='controls'/>");
    $.ajax({
        url: "/video/videoServer/selectByVgid",
        data: {
            "vgid": localStorage.getItem('video-vgid')
        },
        async: false,
        success: function (result) {
            createDramaList(result);
        }
    });
}

function createDramaList(result) {
    var playerStr = "";
    $.each(result, function (idx, val) {
        playerStr +=
            `<span name="videoTab" class="dramalist" title='${val.name}' 
                   data-videostatus='${val.videoStatus}' data-avid='${val.id}' data-url='${val.url}'>${val.name}</span>`;
    });
    $("#playerTab").empty().append(playerStr);

    $("[name='videoTab']").off('click').on('click', function (e) {
        curClickElementAVID = $(this).data("avid");
        curClickElementURL= $(this).data("url");
        $(this).addClass("active_span").siblings().removeClass('active_span');

        if ($(this).data("videostatus") == null || Video_VideoStatus_Able.indexOf($(this).data("videostatus")) != -1) {
            var videoStr = `<video id="videoWindow" class="videoWindow" src='` + encodeURI(curClickElementURL) + `' controls='controls'>
                            您的浏览器版本过低，暂不支持HTML5，请升级或更换浏览器尝试！
                        </video>`;
            $("#videoPlayer").empty().append(videoStr);

            document.getElementById("videoWindow").onerror = function (e) {
                var errorTip = layer.alert('视频播放遇到问题，请稍后重试！', {icon: 3, title: "提示"}, function (e) {
                    $.ajax({
                        url: "/video/videoUploadServer/videoTransCoding",
                        data: {
                            "avid": curClickElementAVID,
                            "videoPath": curClickElementURL
                        },
                        async: false,
                        success: function (result) {
                            doVideoPlayer();
                            $("[data-avid='"+curClickElementAVID+"']").addClass("active_span").siblings().removeClass('active_span');
                        }
                    });
                    layer.close(errorTip);
                });
            }

            return true;
        } else if ($(this).data("videostatus") == VIDEO_DELETED_STATUS) {
            layer.msg('因某些原因，视频已删除！', {
                icon: 3,
                time: 2000
            });
        } else if ($(this).data("videostatus") == VIDEO_EXAMING_STATUS || $(this).data("videostatus") == VIDEO_CODECS_STATUS) {
            layer.msg('视频正在接受审核，请稍后再试！', {
                icon: 3,
                time: 2000
            });
        } else if ($(this).data("videostatus") == VIDEO_CODECS_FAILURE_STATUS) {
            layer.msg('哎呀，视频源异常，请稍后重试！', {
                icon: 3,
                time: 2000
            });
        }

        showVideoErrorPic();
    });
}


function showVideoErrorPic() {
    var videoStr = `<img src="/img/video/video_load_failure.jpg" style="margin: 0 auto;height: 545px;padding-top: 50px;padding-bottom: 50px;">`;
    $("#videoDetail").empty().append(videoStr);
}