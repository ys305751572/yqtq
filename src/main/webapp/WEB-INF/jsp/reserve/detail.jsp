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
                    <input type="text" id="startDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${reserve.startDate}'></date:date>" name="startDate" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>城市:</label>
                    <input type="text" id="cityId" value="${reserve.stadium.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>球场:</label>
                    <a onclick="$reserve.fn.sfInfo(${reserve.stadium.id})">${reserve.stadium.name}</a>//到约场地详情还没写
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
                    <input type="text" id="time" value="${reserve.time}" name="time" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>价格:</label>
                    <input type="text" id="price" value="${reserve.price}" name="price" class="input-sm form-control validate[required]" placeholder="..." disabled>
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
                    <input type="text" id="createDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${reserve.createDate}'></date:date>" name="createDate" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15" >
                    <label>已报球友:</label>
                    <input type="text" id="num" name="num" value="${num}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>头像:</label>
                    <c:forEach items="${list}" var="v">
                        <a onclick="$reserve.fn.detail(${v.userId})" data-rel="gallery" class="pirobox_gall img-popup" title="Lovely evening in Noreway">
                            <img src="${contextPath}/${v.avater}" alt="">
                        </a>
                    </c:forEach>
                </div>
                <hr class="whiter m-t-20"/>

            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $reserve = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
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

           },
            "detail" : function(userId) {
                window.location.href = "${contextPath}/admin/user/detail?userId=" + userId;
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
    },
    $(function(){
        $reserve.fn.init();
    });
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

