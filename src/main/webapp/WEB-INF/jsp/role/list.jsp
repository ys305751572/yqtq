<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">
    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">
    <title>Super Admin Responsive Template</title>
    <!-- CSS -->
    <%@ include file="../inc/new/css.jsp" %>
</head>
<body id="skin-cloth">
<%@ include file="../inc/new/header.jsp" %>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <div class="block-area" id="alternative-buttons">
            <div>
                <a data-toggle="modal" href="${contextPath}/admin/role/add" title="新增" class="btn btn-alt m-r-5">新增</a>
            </div>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>名称</th>
                    <th>人员数量</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </section>
    <br/><br/>
</section>
    <!-- JS -->
    <%@ include file="../inc/new/foot.jsp" %>

    <script>
    $role = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $role.fn.dataTableInit();

                $("#c_search").click(function () {
                    $role.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $role.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/role/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
//                                var checkbox = "<div class=\"icheckbox_minimal\" aria-checked=\"false\" aria-disabled=\"false\" style=\"position: relative;\"><input type=\"checkbox\" value="+ data +" class='pull-left list-check' style=\"position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\"></div>";
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "num","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            render : function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": null,
                            "render": function (data) {
                                var num = data.num;
                                var id = data.id;
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$role.fn.edit(\'" + id + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                if(num==0){
                                    var del = "<button title='删除' class='btn btn-primary btn-circle edit' onclick=\"$role.fn.del(\'" + id + "\')\">" +
                                            "删除</button>";
                                }else{
                                    var del = "<button title='删除' class='btn btn-primary btn-circle edit' disabled ><a>删除<a></button>";
                                }
                                return edit + "&nbsp;" + del;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                    }
                });
            },
            edit: function (id) {
                window.location.href = "${contextPath}/admin/role/add?id=" + id ;
            },
            del: function (id) {
                $.ajax({
                    url: "${contextPath}/admin/role/del",
                    type: "POST",
                    data: {
                        "id": id
                    },
                    success: function (result) {
                        if (result.status) {
                            $role.v.dTable.ajax.reload();
                        } else {
                            $common.fn.notify("操作失败", "error");
                        }
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $role.v.dTable.ajax.reload(null, false);
                    } else {
                        $role.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $role.fn.init();
    })
</script>
<script>
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: "2",
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd'
    });
</script>
</body>
</html>

