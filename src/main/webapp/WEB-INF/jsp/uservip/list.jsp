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
    <input type="hidden" value="用户管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <input type="hidden" id="details" name="details" value="${details}">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="手机号">
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="昵称" >
                </div>
                <div class="col-md-2 form-group">
                    <select id="level" class="select" >
                        <option value="">会员等级</option>
                        <c:forEach items="${systemVipLevels}" var="v">
                            <option value="${v.level}">Lv ${v.level}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>手机号</th>
                    <th>会员开始时间</th>
                    <th>会员经验</th>
                    <th>会员等级</th>
                    <th>会员结束时间</th>
                    <th>昵称</th>
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
    $userVip = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $userVip.fn.dataTableInit();

                $("#c_search").click(function () {
                    $userVip.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $userVip.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/userVip/list",
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
                        {"data": "user.mobile","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "user.experience","sDefaultContent" : ""},
                        {
                            "data": "user.vipLevel",
                            render:function(data){
                                return "Lv" + data;
                            }
                        },
                        {
                            "data": "vipEndDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "user.nickName","sDefaultContent" : ""}

                    ],
                    "fnServerParams": function (aoData) {
                        aoData.mobile = $("#mobile").val();
                        aoData.nickName = $("#nickName").val();
                        aoData.level = $("#level").val();
                        aoData.details = $("#details").val();
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $userVip.v.dTable.ajax.reload(null, false);
                    } else {
                        $userVip.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $userVip.fn.init();
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

