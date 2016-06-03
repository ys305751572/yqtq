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
            <h2 class="tile-title">基本信息</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-6 m-b-15">
                    <label>比赛时间:</label>
                    <%--<date:date format="yyyy-MM-dd HH:mm" value="${reserve.startDate}"></date:date>--%>
                    <input type="text" id="startDate" value="" name="startDate" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>城市:</label>
                    <input type="text" id="cityId" value="${reserve.cityId}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>球场:</label>
                    <a href="">${stadiumName}</a>//跳转
                </div>
                <div class="col-md-6 m-b-15">
                    <label>创建者:</label>
                    <input type="text" id="nickName" value="${reserve.user.nickName}" name="nickName" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>赛制:</label>
                    <input type="text" id="matchType" value="" name="matchType" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>时常:</label>
                    <input type="text" id="time" value="${reserve.time}小时" name="time" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>价格:</label>
                    <input type="text" id="price" value="${reserve.price}￥" name="price" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>付款方式:</label>
                    <input type="text" id="payment" value="" name="payment" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>保险:</label>
                    <input type="text" id="systemInsuranceName" value="${reserve.systemInsurance.name}" name="systemInsuranceName" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>预定时间:</label>
                    <input type="text" id="createDate" value="" name="createDate" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>已报球友:</label>
                </div>
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $(function(){

        var matchType = ${reserve.matchType};
        if(matchType==3){
            $("#matchType").val("三人制");
        }else if(matchType==5){
            $("#matchType").val("五人制");
        }else if(matchType==7){
            $("#matchType").val("七人制");
        }else if(matchType==11){
            $("#matchType").val("十一人制");
        }

        var payment = ${reserve.payment};
        if(payment==0){
            $("#payment").val("AA");
        }else if(payment==1){
            $("#payment").val("全额");
        }

        var createDate = ${reserve.createDate};
        createDate = new Date().format("yyyy-MM-dd HH:mm");
        $("#createDate").val(createDate);

        var startDate = ${reserve.startDate};
        createDate = new Date().format("yyyy-MM-dd HH:mm");
        $("#startDate").val(startDate);

    });
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

