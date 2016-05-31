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
        <h1 class="page-title">赛事列表</h1>
        <div class="block-area">
            <div class="col-md-2">
                <div class="tile">
                    <h2 class="tile-title">赛事详情</h2>
                    <div class="p-10" style="height:520px">
                        <div class="form-group">
                            <label>比赛队伍:</label>
                            ${teamRace.homeTeamId}VS${teamRace.visitingTeamId}
                            <c:forEach items="${team}" var="t">
                                <c:if test="${t.id eq teamRace.homeTeamId}">
                                    <a onclick="$team.fn.sfTeamInfo(${t.id})" class="pirobox_gall img-popup" title="Lovely evening in Noreway">
                                        <img src="${contextPath}/${t.avater}" alt="">
                                    </a>
                                </c:if>
                            </c:forEach>
                            VS
                            <c:forEach items="${team}" var="t">
                                <c:if test="${t.id eq teamRace.visitingTeamId}">
                                    <a onclick="$team.fn.sfTeamInfo(${t.id})" class="pirobox_gall img-popup" title="Lovely evening in Noreway">
                                        <img src="${contextPath}/${t.avater}" alt="">
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="form-group">
                            <label>预约城市:</label>
                            ${teamRace.cityId}
                        </div>
                        <div class="form-group">
                            <label>预约状态:</label>
                            <c:if test="${teamRace.status eq 0}">预约成功</c:if>
                            <c:if test="${teamRace.status eq 1}">预约失败</c:if>
                        </div>
                        <c:if test="${teamRace.status eq 0}">
                            <div class="form-group">
                                <label>比赛地点:</label>
                                ${teamRace.stadiumId}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label>比赛时间:</label>
                            <date:date format="yyyy-MM-dd HH:mm:ss" value="${teamRace.startDate}"></date:date>
                        </div>
                        <div class="form-group">
                            <label>约定时间:</label>
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
            sfTeamInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/team/sfTeamInfo",
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
                        window.location.href = "${contextPath}/admin/team/detail?id=" + id;
                    }
                });
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

