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
                    <select id="cityId" name="cityId" class="select">
                        <option value="">城市</option>
                        <c:forEach items="${city}" var="c">
                            <option value="${c.cityId}">${c.city}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="stadiumId" name="stadiumId" placeholder="场地名">
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="bookTime" name="bookTime" placeholder="预定时长">
                </div>
                <div class="col-md-2 form-group">
                    <select id="" name="" class="select">
                        <option value="">类型</option>
                        <option value="0">散客</option>
                        <option value="1">其他</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="userId" name="userId" placeholder="订购者">
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
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>地区</th>
                    <th>球场名称</th>
                    <th>场地编号</th>
                    <th>订购者</th>
                    <th>预定时长</th>
                    <th>开始使用时间</th>
                    <th>预定时间</th>
                    <th>预定类型</th>
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
    $stadiumBooking = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadiumBooking.fn.dataTableInit();

                $("#c_search").click(function () {
                    $stadiumBooking.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $stadiumBooking.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/stadiumBooking/list",
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
                        {"data": "city.city"},
                        {"data": "stadium.name"},
                        {"data": "stadiumSub.code"},
                        {"data": "user.nickName"},
                        {"data": "bookTime"},
                        {"data": "startDate",
                            render: function (data) {
                                return new Date(data).format("yyyy年MM月dd日 hh:mm")
                            }
                        },
                        {"data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy年MM月dd日 hh:mm")
                            }
                        },
                        {"data": "type",
                            render: function (data) {
                                if(data==0){
                                    return "散客";
                                }
                                if(data==1){
                                    return "其他";
                                }
                            }
                        },
                        {"data": "status",
                            render: function (data) {
                                if(data==0){
                                    return "未使用";
                                }
                                if(data==1){
                                    return "已使用";
                                }
                                if(data==2){
                                    return "已退款";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$stadiumBooking.fn.sfInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.cityId = $("#cityId").val();
                    }
                });
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/stadiumBooking/sfTeamInfo",
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
                        window.location.href = "${contextPath}/admin/stadiumBooking/detail?id=" + id;
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadiumBooking.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadiumBooking.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $stadiumBooking.fn.init();
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

