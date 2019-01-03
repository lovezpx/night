$(function () {
    initTable();
});

function initTable() {
    $.ajax({
        url: "/manager/dicManage/dicPictureType/selectAll",
        async: false,
        success: function (data) {
            $('#dg').datagrid({
                title: "图片类型字典表",
                data: data,
                rownumbers: true, //行数
                columns: [
                    [{
                        field: 'id',
                        title: '主键',
                        width: 130,
                        align: "center",
                        hidden: true
                    }, {
                        field: 'type',
                        title: '图片类型代码',
                        width: 130,
                        align: "center",
                        editor: 'text'
                    }, {
                        field: 'name',
                        title: '图片类型名称',
                        width: 130,
                        align: "center",
                        editor: 'text'
                    }, {
                        field: 'isusing',
                        title: '是否启用',
                        width: 130,
                        align: "center",
                        formatter: function (value) {
                            if (value == 0) {
                                return "不启用";
                            } else {
                                return "启用";
                            }
                        },
                        editor: {
                            type: 'combobox',
                            options: {
                                data: [{
                                    value: 1,
                                    text: "启用"
                                }, {
                                    value: 0,
                                    text: "不启用"
                                }],
                                valueField: "value",
                                textField: "text",
                                editable: false
                            }
                        }
                    }, {
                        field: 'sort',
                        title: '排序',
                        width: 90,
                        align: "center",
                        editor: {
                            type: 'numberbox'
                        }
                    }, {
                        field: 'remark',
                        title: '备注',
                        width: 300,
                        align: "left",
                        editor: {
                            type: 'textarea',
                            options: {
                                validType: 'maxLength[300]'
                            }
                        }
                    }]
                ],
                pagination: true,
                iconCls: 'icon-edit',
                singleSelect: false,
                toolbar: '#tb',
                method: 'get',
                onClickRow: onClickRow
            });

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
    });
};

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
    layer.confirm('是否确认删除?', {icon: 3, title:'提示'}, function(index){
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
        var deletedRows = $('#dg').datagrid('getChanges', 'deleted');
        $.ajax({
            url: "/manager/dicManage/dicPictureType/saveData",
            async: false,
            dataType: "json",
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            data: {
                insertedRows : JSON.stringify(insertedRows),
                updatedRows : JSON.stringify(updatedRows),
                deletedRows : JSON.stringify(deletedRows)
            },
            success: function (data) {
                layer.msg(data.message);
                initTable();
            }
        });
    }
}

function reject() {
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
}