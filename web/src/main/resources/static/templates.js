layui.use([ 'layer', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;

    //添加按钮点击事件
    $("#tpl-add-btn").click(function () {
        if ($("#tpl-input-add").val() == '') {
            layer.msg("请填写模板名称");
            return;
        }
        var name = $("#tpl-input-add").val();
        wb.post("/templates/addOrCopy", {url: name}, function (d) {
            if (d.code == 0) {
                $.fn.zTree.destroy("t-tree-add");
                $.fn.zTree.init($("#t-tree-add"), setting, d.data);
            } else {
                layer.msg(d.msg);
            }
        });
    });

    //查询所有模板
    wb.get("/templates", null, function (d) {
        d.data.forEach(function (k) {
            $("#tpl-sel").append("<option value='" + k.name + "'>" + k.name + "</option>");
            form.render();
        });
    });
    //监听select切换模板
    form.on('select(tpl-sel)', function (data) {
        $.fn.zTree.destroy("t-tree");
        wb.get("/templates/detail", {name: data.value}, function (d) {
            $.fn.zTree.init($("#t-tree"), setting, d.data);
        });
    });

    //ztree设置
    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: true,
            dblClickExpand: false,
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            },
            keep: {
                parent: true
            },
        },
        edit: {
            drag: {},
            enable: true,
            removeTitle: "删除",
            renameTitle: "重命名"
        },
        callback: {
            onDblClick: zTreeOnDblClick,
            beforeRename: zTreeBeforeRename,
            beforeRemove: zTreeBeforeRemove,
            beforeDrag: zTreeBeforeDrag,
            beforeDrop: zTreeBeforeDrop
        }
    };
    //ztree双击事件
    function zTreeOnDblClick(event, treeId, treeNode) {
        var url = getParentsPath(treeNode);
        $.get("/templates/content", {url: url});
        return;
    };

    //ztree修改名称
    function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
        if (isCancel) {
            return;
        }
        var r = false;
        wb.put("/templates", {url: getParentsPath(treeNode), newName: newName}, function (d) {
            if (d.code == 0) {
                r = true;
            } else {
                layer.msg(d.msg);
            }
        }, {async: false});
        return true;
    }

    //ztree删除节点
    function zTreeBeforeRemove(treeId, treeNode) {
        var b = false;
        var url = getParentsPath(treeNode);
        wb.delete("/templates", {url: url}, function (d) {
            if (d.code == 0) {
                b = true;
            } else {
                layer.msg(d.msg);
            }
        }, {async: false});
        return b;
    }

    //ztree拖拽前事件
    function zTreeBeforeDrag(treeId, treeNodes) {
        source_treeId = treeId;
        return true;
    };

    //ztree拖拽事件
    var source_treeId;
    function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
        var b = false;
        if (source_treeId != treeId && !isCopy) {
            layer.msg("不同模板间的文件只能复制");
            return b;
        }
        var target = getParentsPath(targetNode);
        var source = [];
        treeNodes.forEach(function (v) {
            source.push(getParentsPath(v));
        });
        wb.post("/templates/drop", {source: source, target: target, type: moveType, isCopy: isCopy}, function (d) {
            if (d.code == 0) {
                b = true;
            } else {
                layer.msg(d.msg);
            }
        },{traditional:true,async:false})
        return b;
    };
});

//修改配置
function editSettings(k) {
    layer.prompt()
}
//获取任意文件的相对路径
function getParentsPath(treeNode) {
    var parent = treeNode.getParentNode();
    if (parent == null) {
        return treeNode.name;
    } else {
        return getParentsPath(parent) + "\\" + treeNode.name;
    }
}

//展开树/收缩树
function expandTree() {
    var treeObj = $.fn.zTree.getZTreeObj("t-tree");
    if ($("#tree-text").text() == '展开树') {
        $("#tree-text").text('收缩树');
        treeObj.expandAll(true);
    } else {
        $("#tree-text").text('展开树');
        treeObj.expandAll(false);
    }
}

var newCount = 1;

//ztree节点添加文件和文件夹按钮
function addHoverDom(treeId, treeNode) {
    //只有父节点才有添加功能
    if (!treeNode.isParent) {
        return;
    }
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    if (treeNode.editNameFlag || $("#addBtn2_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='layui-icon layui-icon-templeate-1' id='addBtn_" + treeNode.tId
        + "' title='添加文件夹' onfocus='this.blur();'></span>";
    var addStr2 = "<span class='layui-icon layui-icon-file' id='addBtn2_" + treeNode.tId
        + "' title='添加文件' onfocus='this.blur();'></span>";
    sObj.after(addStr2);
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    var btn2 = $("#addBtn2_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        var name = "new folder" + (newCount++);
        var url = getParentsPath(treeNode) + "\\" + name;
        wb.post("/templates", {type: 0, url: url}, function (d) {
            if (d.code == 0) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.addNodes(treeNode, 0, {id: (-1 - newCount), isParent: true, pId: treeNode.id, name: name});
            } else {
                layer.msg(d.msg);
            }
        }, {async: false});
        return false;
    });
    if (btn2) btn2.bind("click", function () {
        var name = "new file" + (newCount++);
        var url = getParentsPath(treeNode) + "\\" + name;
        wb.post("/templates", {type: 1, url: url}, function (d) {
            if (d.code == 0) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.addNodes(treeNode, 0, {id: (-1 - newCount), pId: treeNode.id, name: name});
            } else {
                layer.msg(d.msg);
            }
        }, {async: false});
        return false;
    });
};

//移除节点按钮
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
    $("#addBtn2_" + treeNode.tId).unbind().remove();
};
