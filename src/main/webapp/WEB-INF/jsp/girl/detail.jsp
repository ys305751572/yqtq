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
    <link rel="stylesheet" type="text/css" href="${contextPath}/html/js/jQueryRatyli/css/demo.css" />
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
        <h1 class="page-title">宝贝信息</h1>
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${girl.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>宝贝名称:</label>
                        <input type="text" id="name" name="name" value="${girl.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>地区:</label>
                        <input type="text" id="cityId" value="${girl.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>服务价格/场:</label>
                        <input type="text" id="price" name="price" value="${girl.price}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>服务次数:</label>
                        <input type="text" id="guSize" name="guSize" value="${girl.guSize}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>状态:</label>
                        <input type="text" id="status" name="status" value="<c:if test='${girl.status == 0}'>可预约</c:if><c:if test='${girl.status == 1}'>不可预约</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>兴趣爱好:</label>
                        <input type="text" id="interest" name="interest" value="${girl.interest}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>喜欢球队:</label>
                        <input type="text" id="favoriteTeam" name="favoriteTeam" value="${girl.favoriteTeam}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>职业:</label>
                        <input type="text" id="profession" name="profession" value="${girl.profession}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>签名:</label>
                        <input type="text" id="label" name="label" value="${girl.label}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>评价:</label>
                        <%--<input type="text" id="avgStar" name="avgStar" value="${avgStar ne null ? avgStar : '还没有人评价'}" class="input-sm form-control validate[required]" placeholder="..." disabled>--%>
                        <div id="demo9" class="demo">
                            <span class="ratyli" data-rate="${avgStar ne null ? avgStar : 0}"></span>
                            <a data-toggle="modal" href="${contextPath}/admin/girlComment/index?id=${girl.id}" title="详情" class="btn btn-alt m-r-5">详情</a>
                        </div>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15" >
                        <label>宝贝封面:</label>
                        <c:forEach items="${image}" var="v">
                            <c:if test="${v.type eq 0 }"><img src="${v.url}" alt=""></c:if>
                        </c:forEach>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15" >
                        <label>宝贝相册:</label>
                        <c:forEach items="${image}" var="v">
                            <c:if test="${v.type eq 1}"><img src="${v.url}" alt=""></c:if>
                        </c:forEach>
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
<script src="${contextPath}/html/js/jQueryRatyli/js/demo.js"></script>
<script src="${contextPath}/html/js/jQueryRatyli/js/jquery.ratyli.js"></script>
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

