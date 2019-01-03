var element;
var coverPic = {};
var videoList = [];
$(function () {
    layui.use('element', function () {
        element = layui.element;
        element.init();
    });

    layui.use('upload', function () {
        var upload = layui.upload;

        var uploadPicture = upload.render({
            elem: '#coverPicture'
            , url: '/picture/pictureUploadServer/uploadSingleFile'
            , accept: 'images'
            , acceptMime: 'image/*'
            , exts: 'jpg|png|gif'
            , auto: true
            , progress: function (e, percent) {
                element.progress('progressBar', percent + '%');
            }
            , done: function (res, index, upload) {
                $("#coverPreview").attr("src", res.data);
                $("#picSrc").val(res.data);
                $("#mask").hide();
                $(".layui-progress-bar").width("0%");
            }
        });


        var uploadVideo = upload.render({
            elem: '#film'
            , url: '/video/videoUploadServer/uploadSingleFile'
            , accept: 'video'
            , acceptMime: 'video/*'
            , exts: 'mp4|avi|mkv|wmv|rmvb'
            , auto: true
            , progress: function (e, percent) {
                element.progress('progressBar', percent + '%');
            }
            , done: function (res, index, upload) {
                videoList.push(res.data);
                $('#dg').datagrid('loadData', videoList)
                $("#mask").hide();
                $(".layui-progress-bar").width("0%");
            }
        });
    })

    initVideoTable();
    initVideoTypeSelect();
});

$("#uploadSubmit").on('click', function (e) {
    var videoGroup = {};
    if ($("[name='vgName']").val()) {
        videoGroup.name = $("[name='vgName']").val();
    } else {
        layer.msg('視頻集名稱不可為空！', {
            icon: 3,
            time: 2000
        });
        return false;
    }

    if ($("[name='vgType']").val()) {
        videoGroup.type = $("[name='vgType']").val();
    } else {
        layer.msg('視頻類型未選擇！', {
            icon: 3,
            time: 2000
        });
        return false;
    }

    videoGroup.author = $("[name='author']").val();

    videoGroup.numcode = $("[name='numCode']").val();

    coverPic.src = $("#picSrc").val();
    if (coverPic.src) {
        videoGroup.picture = coverPic;
    } else {
        layer.msg('封面圖片未上傳！', {
            icon: 3,
            time: 2000
        });
        return false;
    }

    if (!$.isEmptyObject(videoList)) {
        videoGroup.videoList = videoList;
    } else {
        layer.msg('視頻未上傳！', {
            icon: 3,
            time: 2000
        });
        return false;
    }

    videoGroup.videoTipList = getTips();

    $.ajax({
        url: '/video/videoUploadServer/upload',
        type: 'post',
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        },
        async: false,
        dataType: "json",
        data: {
            videoGroupStr: JSON.stringify(videoGroup)
        },
        success: function (result) {
            debugger;
            if (result.success) {
                layer.msg('視頻上传成功！', {
                    icon: 6,
                    time: 5000,
                    shade: 0.4
                }, function () {
                    $("#head").find("li.active").click();
                });
            } else {
                layer.alert('視頻上传失败！<br>' + result.message);
            }
        }
    });
})

function initVideoTypeSelect() {
    $.ajax({
        url: '/manager/dicManage/dicVideoType/selectAll',
        success: function (result) {
            $.each(result, function (idx, val) {
                $("#videoType").append(`<option value='${val.type}'>${val.name}</option>`);
            })
        }
    });
}

function initVideoTable() {
    $('#dg').datagrid({
        title: "上传视频表",
        rownumbers: true, //行数
        columns: [
            [{
                field: 'id',
                title: '主键',
                width: 130,
                align: "center",
                hidden: true
            }, {
                field: 'name',
                title: '视频名称',
                width: 200,
                align: "center",
                editor: 'text'
            }, {
                field: 'videoStatus',
                title: '视频状态',
                width: 100,
                align: "center",
                editor: 'text',
                formatter: function (value, rowData, rowIndex) {
                    rowData.videoStatus = null;
                },
                hidden: true
            }, {
                field: 'url',
                title: '视频路径',
                width: 700,
                align: "center",
                editor: 'text'
            }, {
                field: 'sort',
                title: '展示顺序',
                width: 100,
                align: "center",
                editor: {
                    type: 'numberbox'
                }
            }]
        ],
        iconCls: 'icon-edit',
        singleSelect: false,
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: append
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: removeit
        }, '-', {
            text: '保存',
            iconCls: 'icon-save',
            handler: accept
        }, '-', {
            text: '撤销',
            iconCls: 'icon-undo',
            handler: reject
        }],
        method: 'get',
        onClickRow: onClickRow
    });
}

var editIndex = undefined;

function endEditing() {
    if (editIndex == undefined) {
        return true
    }
    if ($('#dg').datagrid('validateRow', editIndex)) {
        var ed = $('#dg').datagrid('getEditor', {
            index: editIndex,
            field: 'productid'
        });

        $('#dg').datagrid('getRows')[editIndex]['productname']
        $('#dg').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function onClickRow(index) {
    if (editIndex != index) {
        if (endEditing()) {
            $('#dg').datagrid('selectRow', index)
                .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#dg').datagrid('selectRow', editIndex);
        }
    }

}

function append() {
    if (endEditing()) {
        $('#dg').datagrid('appendRow', {
            status: 'P'
        });
        editIndex = $('#dg').datagrid('getRows').length - 1;
        $('#dg').datagrid('selectRow', editIndex)
            .datagrid('beginEdit', editIndex);
    }
}

function removeit() {
    if (editIndex == undefined) {
        layer.msg('未选中行！', {
            icon: 3,
            time: 2000
        });
        return false;
    }
    layer.confirm('是否确认删除?', {icon: 3, title: '提示'}, function (index) {
        $('#dg').datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
        editIndex = undefined;
        layer.close(index);
    })
}

function accept() {
    if (endEditing()) {
        var insertedRows = $('#dg').datagrid('getChanges', 'inserted');
        var updatedRows = $('#dg').datagrid('getChanges', 'updated');
        videoList = insertedRows.concat(updatedRows)
    }
}

function reject() {
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
}