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
        <h1 class="page-title">散客详情</h1>
        <div class="block-area">
            <div class="col-md-2">
                <div class="tile">
                    <h2 class="tile-title">基本信息</h2>
                    <div class="p-10" style="height:520px">
                        <div class="form-group">
                            <label>比赛时间:</label>
                            <date:date format="yyyy-MM-dd HH:mm" value="${reserve.startDate}"></date:date>
                        </div>
                        <div class="form-group">
                            <label>城市:</label>
                            ${reserve.cityId}
                        </div>
                        <div class="form-group">
                            <label>球场:</label>
                            <a href="">${stadiumName}</a>
                        </div>
                        <div class="form-group">
                            <label>创建者:</label>
                            ${reserve.user.nickName}
                        </div>
                        <div class="form-group">
                            <label>赛制:</label>
                            <c:if test="${reserve.matchType eq 0}">三人制</c:if>
                            <c:if test="${reserve.matchType eq 1}">五人制</c:if>
                            <c:if test="${reserve.matchType eq 2}">七人制</c:if>
                            <c:if test="${reserve.matchType eq 3}">十一人制</c:if>
                        </div>
                        <div class="form-group">
                            <label>时常:</label>
                            ${reserve.time}
                        </div>
                        <div class="form-group">
                            <label>价格:</label>
                            ${reserve.price}
                        </div>
                        <div class="form-group">
                            <label>付款方式:</label>
                            <c:if test="${reserve.payment eq 0}">AA</c:if>
                            <c:if test="${reserve.payment eq 1}">全额</c:if>
                        </div>
                        <div class="form-group">
                            <label>保险:</label>
                            ${reserve.systemInsurance.name}
                        </div>
                        <div class="form-group">
                            <label>预定时间:</label>
                            <date:date format="yyyy-MM-dd HH:mm" value="${reserve.createDate}"></date:date>
                        </div>
                        <div class="form-group">
                            <label>已报球友:</label>
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

