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
                        <img id="avater" name="avater" src="${contextPath}/${team.avater}" alt="">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>球场名称:</label>
                        <a href="${contextPath}/admin/stadium/add"><input type="text" id="name" name="name" value="${team.name}" class="input-sm form-control validate[required]" placeholder="..." disabled></a>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>城市:</label>
                        <input type="text" id="cityId" value="${team.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>队语:</label>
                        <input type="text" id="slogan" name="slogan" value="${team.slogan}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>队长:</label>
                        <input type="text" id="leaderUserId" name="leaderUserId" value="${team.user.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>创建时间:</label>
                        <input type="text" id="createDate" name="createDate" value="" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>赛事:</label>
                        <input type="text" id="tmSize" name="tmSize" value="${team.tmSize}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>队员:</label>
                        <input type="text" id="tmSetNum" name="tmSetNum" value="${team.tmSetNum}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                            <label>头像</label>
                            <p></p>
                            <p></p>
                            <c:forEach items="${teamMember}" var="item">
                                <c:forEach items="${userList}" var="u">
                                    <c:if test="${item.userId eq u.userId}">
                                        <a onclick="$team.fn.detail(${item.userId})" data-rel="gallery" class="pirobox_gall img-popup" title="Lovely evening in Noreway">
                                            <img src="${contextPath}/${u.avater}" alt="">
                                        </a>
                                    </c:if>
                                </c:forEach>
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
        var createDate = ${team.createDate};
        createDate = new Date().format("yyyy-MM-dd hh:mm");
        $("#createDate").val(createDate);
        <%--<date:date format="yyyy-MM-dd HH:mm" value="${team.createDate}"></date:date>--%>
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

