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
        <h1 class="page-title">用户详情</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-6 m-b-15">
                    <label>头像</label>
                    <img src="${contextPath}/${user.avater}" alt="">
                </div>
                <div class="col-md-6 m-b-15">
                    <label>账 号:</label>
                    <input type="text" id="userId" name="userId" value="${user.userId}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>电 话:</label>
                    <input type="text" id="mobile" name="mobile" value="${user.mobile}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>昵 称:</label>
                    <input type="text" id="nickName" name="nickName" value="${user.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>年 龄:</label>
                    <input type="text" id="age" name="age" value="${user.age}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>身 高:</label>
                    <input type="text" id="height" name="height" value="${user.height}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>体 重:</label>
                    <input type="text" id="weight" name="weight" value="${user.weight}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>位 置:</label>
                    <c:if test="${user.position eq 0}"><input type="text" id="position" name="position" value="前锋" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${user.position eq 1}"><input type="text" id="position" name="position" value="中锋" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${user.position eq 2}"><input type="text" id="position" name="position" value="后卫" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${user.position eq 3}"><input type="text" id="position" name="position" value="门卫" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>用户状态:</label>
                    <c:if test="${user.status eq 0}"><input type="text" id="status" name="status" value="正常" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${user.status eq 1}"><input type="text" id="status" name="status" value="冻结" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>注册时间:</label>
                    <input type="text" id="createDate" name="createDate" value="<date:date format="yyyy-MM-dd HH:mm:ss" value="${user.createDate}"></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>信誉积分:</label>
                    <input type="text" id="credibility" name="credibility" value="${user.credibility}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>会员等级:</label>
                    <c:if test="${user.vipLevel eq 0}"><input type="text" id="vipLevel" name="vipLevel" value="非会员" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${user.vipLevel ne 0}"><input type="text" id="vipLevel" name="vipLevel" value="Lv${user.vipLevel}" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>会员积分:</label>
                    <input type="text" id="integral" name="integral" value="${user.integral}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>会员结束时间:</label>
                    <input type="text" id="vipEndDate" name="vipEndDate" value="<date:date format="yyyy-MM-dd HH:mm:ss" value="${user.vipEndDate}"></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>地理位置:</label>
                    <input type="text" id="cityId" name="cityId" value="${user.cityId}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <hr class="whiter m-t-20"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
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

