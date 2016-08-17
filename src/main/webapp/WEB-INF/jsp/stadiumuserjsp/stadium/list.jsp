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
    <input type="hidden" value="球场管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="alternative-buttons">
            <div>
                <a data-toggle="modal" href="${contextPath}/stadium/stadium/edit" title="新增" class="btn btn-alt m-r-5">新增</a>
            </div>
        </div>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>球场名称</th>
                    <th>被预定总数</th>
                    <th>当前预定数</th>
                    <th>场地数</th>
                    <th>总金额</th>
                    <th>状态</th>
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
    $stadium = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadium.fn.dataTableInit();
            },
            dataTableInit: function () {
                $stadium.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/stadium/stadium/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "stadiumNum","sDefaultContent" : ""},
                        {"data": "availableStadiumNum","sDefaultContent" : ""},
                        {"data": "siteNum","sDefaultContent" : ""},
                        {"data": "accumulatedAmount","sDefaultContent" : ""},
                        {
                            "data": "isStatus",
                            "render": function (data) {
                                if(data==0){
                                    return "无可预订场次";
                                }else {
                                    return "可预订";
                                }
                            },"sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$stadium.fn.sfStadiumInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$stadium.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                return detail + "&nbsp;" + edit ;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {

                    }
                });
            },
            edit: function (id){
                window.location.href = "${contextPath}/stadium/stadium/edit?id=" + id;
            },
            sfStadiumInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/stadium/stadium/sfStadiumInfo",
                    "data": {
                        "id": id
                    },
                    "dataType": "json",
                    "type": "POST",
                    "success": function (result) {
                        if (!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        window.location.href = "${contextPath}/stadium/stadium/detail?id=" + id;
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadium.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadium.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $stadium.fn.init();
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

