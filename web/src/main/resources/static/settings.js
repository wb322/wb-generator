layui.use(['table'], function () {
    var table = layui.table;


    //setting获取数据
    wb.get("/settings", null, function (d) {
        d.data.forEach(function (item) {
            $("#t-settings").append("<tr>");
            $.each(item,function (k,v) {
                $("#t-settings").append("<td>"+k+"</td>");
                $("#t-settings").append("<td>"+v+"</td>");
                $("#t-settings").append("<td><button class='layui-btn layui-btn-sm' onclick='editSettings(\""+k+"\")'>修改</button></td>");
            })
            $("#t-settings").append("</tr>");
        });
        table.reload('#t-settings');
    });

});