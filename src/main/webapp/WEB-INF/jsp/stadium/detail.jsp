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
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">场地列表</h1>
        <div class="block-area">
            <div class="col-md-2">
                <div class="tile">
                    <h2 class="tile-title">场地详情</h2>
                    <div class="p-10" style="height:520px">
                        <div class="form-group">
                            <label>图片:</label>
                            <img src="${contextPath}/${stadium.avater}" alt="">
                        </div>
                        <div class="form-group">
                            <label>球场名称:</label>
                            ${stadium.name}
                            <a data-toggle="modal" href="${contextPath}/admin/stadium/edit?id=${stadium.id}" title="编辑" class="tooltips">
                                <i>编辑</i>
                            </a>
                        </div>
                        <div class="form-group">
                            <label>城市:</label>
                            ${stadium.city.city}
                        </div>
                        <c:if test="${stadium.type eq 0}">
                            <div class="form-group">
                                <label>球场主:</label>
                                <a href="${contextPath}/admin/stadiumUser/detail?id=${stadium.stadiumUserId}">${stadiumUser.nickName}</a>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label>球场基本信息:</label>
                            <p>场地类型：<c:if test="${stadium.siteType eq 0}">室内</c:if><c:if test="${stadium.siteType eq 1}">室外</c:if></p>
                            <p>草皮类型：${stadium.sodType}</p>
                            <p>灯光：${stadium.light}</p>
                            <p>停车场：<c:if test="${stadium.park eq 0}">无</c:if><c:if test="${stadium.park eq 1}">免费</c:if><c:if test="${stadium.park eq 2}">收费</c:if></p>
                            <p>赠送：${stadium.giving}</p>
                        </div>
                        <c:if test="${stadium.type eq 0}">
                            <div class="form-group">
                                <label>球场场次:</label>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label>球场简介:</label>
                            ${stadium.description}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $team = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $team.fn.initImage();
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/1/sfInfo",
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
                        window.location.href = "${contextPath}/admin/1/detail?id=" + id;
                    }
                });
            }
        }
    }
    $(function () {
        $team.fn.init();
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

