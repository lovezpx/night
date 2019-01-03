var zTree
$(function () {
    initTree();
});

function initTree() {
    $.ajax({
        url: "/manager/menuManage/selectByParentId",
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        },
        async: false,
        dataType: "json",
        type: "POST",
        data: {
            parentId: 'root'
        },
        success: function (data) {
            $.fn.zTree.init($("#treeMenu"), setting, data);
            zTree = $.fn.zTree.getZTreeObj("treeMenu");
            fuzzySearch('treeMenu', '#key', null, true);
        }
    });
}

var setting = {
    view: {
        showLine: true,
        addHoverDom: addHoverDom,
        selectedMulti: false
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onDblClick: zTreeonDblClick,
        beforeEditName: beforeEditName,
        beforeRemove: beforeRemove
    }
};

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 1,
                title: "请输入新节点内容",
                content: $("#model"),
                btn: ['确定', '取消'],
                success: function () {
                    $(".layui-layer-title").append('<i class="fa fa-times" aria-hidden="true"></i> ')
                },
                yes: function (ind, layero) {
                    var data = {
                        name: $("#model input[name=name]").val(),
                        type: $("#model input[name=type]").val(),
                        url: $("#model input[name=url]").val(),
                        logo: $("#model input[name=logo]").val(),
                        target: $("#model select[name=target]").val(),
                        sort: $("#model input[name=sort]").val(),
                        parentId: treeNode.id
                    };
                    $.ajax({
                        url: "/manager/menuManage/insertSelective",
                        async: false,
                        dataType: "json",
                        type: "get",
                        data: data,
                        success: function (data) {

                        }
                    });
                    initTree();
                    layer.close(ind);
                },
                end: function () {
                    $("#model").css('display', 'none')
                }

            })
        });
        return false;
    });
};

function beforeRemove(treeId, treeNode) {
    zTree.selectNode(treeNode);
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            type: 0,
            title: "确认删除 节点[" + treeNode.name + "]吗？",
            btn: ["确定", "取消"],
            yes: function (ind) {
                $.ajax({
                    url: "/manager/menuManage/deleteByPrimaryKey",
                    async: false,
                    dataType: "json",
                    type: "get",
                    data: {
                        id: treeNode.id
                    },
                    success: function (data) {

                    }
                });
                initTree();
                layer.close(ind);
            },
            offset: ['30%', '40%'],
            area: 'auto',
            maxWidth: 250
        })
    });
    return false;
}

function beforeEditName(treeId, treeNode) {
    zTree.selectNode(treeNode);
    setTimeout(function () {
        layui.use('layer', function () {
            var name = treeNode.name || '';
            var type = treeNode.type || "";
            var url = treeNode.url || "";
            var logo = treeNode.logo || "";
            var sort = treeNode.sort || "";
            $("#model input[name=name]").val(name);
            $("#model input[name=type]").val(type);
            $("#model input[name=url]").val(url);
            $("#model input[name=logo]").val(logo);
            $("#model input[name=sort]").val(sort);
            var layer = layui.layer;
            layer.open({
                type: 1,
                title: "编辑节点",
                content: $("#model"),
                btn: ["确定", "取消"],
                success: function () {
                    $(".layui-layer-title").append('<i class="fa fa-times" aria-hidden="true"></i> ')
                },
                yes: function (ind) {
                    var data = {
                        id: treeNode.id,
                        name: $("#model input[name=name]").val(),
                        type: $("#model input[name=type]").val(),
                        url: $("#model input[name=url]").val(),
                        logo: $("#model input[name=logo]").val(),
                        target: $("#model select[name=target]").val(),
                        sort: $("#model input[name=sort]").val()
                    };

                    $.ajax({
                        url: "/manager/menuManage/updateByPrimaryKeySelective",
                        async: false,
                        dataType: "json",
                        type: "get",
                        data: data,
                        success: function (data) {

                        }
                    });
                    initTree();
                    layer.close(ind)
                },
                end: function () {
                    $("#model").css("display", "none")
                }

            })
        })
    }, 0);
    return false;
}

function zTreeonDblClick(event, treeId, treeNode) {
    if (treeNode.url != "" && treeNode.target == "_self") {
        $("#previewDiv").load(treeNode.url);
    }
}