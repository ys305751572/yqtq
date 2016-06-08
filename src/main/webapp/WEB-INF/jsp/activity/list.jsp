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
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <label>关键字搜索</label>
                    <input type="text" class="input-sm form-control" id="introduction" name="introduction" placeholder="...">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <div class="block-area" >
            <a data-toggle="modal" href="${contextPath}/admin/activity/add" title="发布活动" class="btn btn-alt m-r-5">
            </a>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>活动简介</th>
                    <th>发帖时间</th>
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
    activity = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                activity.fn.dataTableInit();

                $("#c_search").click(function () {
                    activity.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                activity.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/activity/list",
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
                        {"data": "introduction"},
                        {
                            "data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm")
                            }
                        },
                        {
                            "data": null,
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='activity.fn.detail(" + data.id + ")'> " +
                                        "<i class='fa fa-eye'></i></button>&nbsp;&nbsp;";
                                detail += "<button title='编辑' class='btn btn-primary btn-circle edit' onclick='activity.fn.edit(" + data.id + ")'> " +
                                        "<i class='fa fa-edit'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.introduction = $("#introduction").val();
                    }
                });
            },
            "detail": function (id) {
                window.location.href = "${contextPath}/admin/activity/detail?activityId=" + id;
            },
            "edit": function (id) {
                window.location.href = "${contextPath}/admin/activity/edit?activityId=" + id;
            },
        }
    }
    $(function () {
        activity.fn.init();
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

