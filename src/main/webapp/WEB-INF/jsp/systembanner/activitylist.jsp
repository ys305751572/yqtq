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
    <style type="text/css">
        section#content.container{
            margin-left: 0px;
        }
    </style>
</head>
<body id="skin-cloth">
<%--<%@ include file="../inc/new/header.jsp" %>--%>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <%--<%@ include file="../inc/new/menu.jsp" %>--%>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="introduction" name="introduction" placeholder="关键字搜索">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>活动简介</th>
                    <th>发帖时间</th>
                    <th>确定</th>
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
    $activityList = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $activityList.fn.dataTableInit();

                $("#c_search").click(function () {
                    $activityList.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $activityList.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/activity/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "introduction",
                            render: function (data) {
                                if (null != data && data != '') {
                                    return data.length > 30 ? (data.substring(0, 30) + '...') : data;
                                } else {
                                    return "";
                                }
                            }
                        },
                        {
                            "data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm")
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var sure = "<button title='确定' class='btn btn-primary btn-circle add' onclick=\"$activityList.fn.sure('"+data+ "')\">" +
                                        "确定</button>";
                                return sure;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.introduction = $("#introduction").val();
                    }
                });
            },
            sure : function(data){
                if(window.opener){
                    window.opener.document.all.toId.value = data;
                    window.close();
                }
            }
        }
    }
    $(function () {
        $activityList.fn.init();
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

