var trailerData;
$(function () {
    initTable();
    randerData();
});

function randerData(){
    trailerData = [];
    $.ajax({
        url: "/video/videoTrailerServer/selectAll",
        async: false,
        success: function (data) {
            trailerData = data;
            $('#dg').datagrid('loadData',trailerData);
            initTablePage(data)
        }
    });
}

function initTable() {
    $('#dg').datagrid({
        title: "新片推荐",
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
                title: '推荐名称',
                width: 130,
                align: "center",
                editor: 'text'
            }, {
                field: 'vgid',
                title: '视频集ID',
                width: 130,
                align: "center",
                hidden: true
            }, {
                field: 'type',
                title: '视频类型',
                width: 130,
                align: "center",
                hidden: true
            }, {
                field: 'typeName',
                title: '视频类型名',
                width: 130,
                align: "center"
            }, {
                field: 'author',
                title: '作者',
                width: 130,
                align: "center"
            }, {
                field: 'numcode',
                title: '番号',
                width: 130,
                align: "center"
            }, {
                field: 'coverid',
                title: '封面图片ID',
                width: 130,
                align: "center",
                hidden: true
            }, {
                field: 'coverSrc',
                title: '封面图片',
                width: 130,
                align: "center"
            }, {
                field: 'uploadtime',
                title: '上传时间',
                width: 200,
                align: "center",
                formatter:function(value){
                    if(value){
                        return timeStamp2String(new Date(value),'yyyy-MM-dd HH:mm');
                    }
                }
            }, {
                field: 'uploadby',
                title: '上传人',
                width: 200,
                align: "left"
            }]
        ],
        pagination: true,
        iconCls: 'icon-edit',
        singleSelect: false,
        toolbar: '#tb',
        method: 'get',
        onClickRow: onClickRow
    });
};

function initTablePage(data){
    //设置分页控件
    var p = $('#dg').datagrid('getPager');
    $(p).pagination({
        showRefresh: false,
        total: data.length,
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [10,20,50],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        onSelectPage: function (pageNo, pageSize) {
            var start = (pageNo - 1) * pageSize;
            var end = start + pageSize;
            $("#dg").datagrid("loadData", data.slice(start, end));
            $(p).pagination('refresh', {
                total:data.length,
                pageNumber:pageNo
            });
        }
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

function append(num) {
    if (endEditing()) {
        $.ajax({
            url:'/video/videoTrailerServer/selectVideoGroupForTrailerByRange',
            data:{
                'end':num
            },
            success:function(result){
                if(result && result.length > 0){
                    var rows = result.concat(trailerData);
                    $('#dg').datagrid('loadData',rows);
                    initTablePage(rows)
                } else {
                    layer.msg('未取得可推荐视频！', {
                        icon: 3,
                        time: 2000
                    });
                }
            }
        })
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
    layer.confirm('是否确认删除?', {icon: 3, title:'提示'}, function(index){
        $('#dg').datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
        editIndex = undefined;
        layer.close(index);
    })
}

function removeAll(){
    if (endEditing()) {
        $.ajax({
            url:'/video/videoTrailerServer/deleteAll',
            success:function(result){
                layer.msg('清空完成！', {
                    icon: 3,
                    time: 2000
                });
                randerData();
            }
        })
    }
}

function accept() {
    if (endEditing()) {
        var rows = $('#dg').datagrid('getRows');
        $.ajax({
            url: "/video/videoTrailerServer/insertSelective",
            async: false,
            dataType: "json",
            data: {
                rows : JSON.stringify(rows)
            },
            success: function (data) {
                if(data.success){
                    layer.msg('新片速达发布完成！', {
                        icon: 3,
                        time: 2000
                    });
                    randerData();
                } else {
                    layer.msg(data.message);
                }
            }
        });
    }
}

function reject() {
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
}