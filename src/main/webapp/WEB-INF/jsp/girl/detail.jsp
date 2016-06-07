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
        <h1 class="page-title">宝贝信息</h1>
        <form action="${contextPath}/admin/girl/save" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
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
                        <label>服务价格:</label>
                        <input type="text" id="price" name="price" value="￥${girl.price}/h" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>服务次数:</label>
                        <input type="text" id="1" name="leaderUserId" value="" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>状态:</label>
                        <input type="text" id="2" name="createDate" value="" class="input-sm form-control validate[required]" placeholder="..." disabled>
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
                        <div id="demo" class="demo">
                            <span class="ratyli"></span>
                        </div>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>宝贝封面:</label>
                        <c:forEach items="${image}" var="v" begin="0" end="2">
                            <c:if test="${v.type eq 0 }"><img src="${contextPath}/${v.url}" alt=""></c:if>
                        </c:forEach>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>宝贝相册:</label>
                        <c:forEach items="${image}" var="v">
                            <c:if test="${v.type eq 1}"><img src="${contextPath}/${v.url}" alt=""></c:if>
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
<script type="text/javascript" src="/js/jquery.raty.js"></script>
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
            initImage : function() {
            },
            save : function () {
            },
            "detail" : function(userId) {
                window.location.href = "${contextPath}/admin/user/detail?userId=" + userId;
            },
        }
    }
    $(function () {
        $team.fn.init();
        $("#demo .ratyli").ratyli();
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

