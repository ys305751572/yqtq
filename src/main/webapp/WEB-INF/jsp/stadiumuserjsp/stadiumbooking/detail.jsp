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
        <h1 class="page-title">约场地详情</h1>
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>logo:</label>
                        <img id="avater" name="avater" src="${stadiumBooking.stadium.avater}" alt="">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>球场名称:</label>
                        <a href="${contextPath}/stadium/stadium/detail?id=${stadiumBooking.stadium.id}&&isEd=1" class="btn btn-block btn-alt" style="font-size: 12px;height: 30px;text-align:left;text-indent:0.5em;background-color:rgba(238, 238, 238, 0.09) !important;" >${stadiumBooking.stadium.name}</a>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>城市:</label>
                        <input type="text" id="cityId" value="${stadiumBooking.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>预约时长:</label>
                        <input type="text" id="slogan" name="slogan" value="${stadiumBooking.bookTime}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>金额:</label>
                        <input type="text" id="1" name="1" value="${stadiumBooking.price}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>预订人:</label>
                        <input type="text" id="leaderUserId" name="leaderUserId" value="${stadiumBooking.user.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>预定状态:</label>
                        <input type="text" id="status" name="status" value="<c:if test='${stadiumBooking.status eq 0}'>未使用</c:if><c:if test='${stadiumBooking.status eq 1}'>已使用</c:if><c:if test='${stadiumBooking.status eq 2}'>已退款</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>使用开始时间:</label>
                        <input type="text" id="startDate" name="startDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${stadiumBooking.startDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>预约时间:</label>
                        <input type="text" id="createDate" name="createDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${stadiumBooking.createDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </form>
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
            },
            "detail" : function(userId) {
                window.location.href = "${contextPath}/admin/user/detail?userId=" + userId;
            },
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

