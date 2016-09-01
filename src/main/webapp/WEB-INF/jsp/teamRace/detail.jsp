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
    <input type="hidden" id="mian_module" value="球队管理">
    <input type="hidden" id="child_module" value="赛事管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">赛事列表</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-12 m-b-15">
                    <div style="float: left">
                        <label>比赛队伍:</label>
                    </div>
                </div>
                <div class="col-md-12 m-b-15">
                    <div style="float: left">
                        <a onclick="$team.fn.sfTeamInfo(${teamRace.homeTeam.id})" class="pirobox_gall img-popup" title="${teamRace.homeTeam.name}">
                            <img src="${teamRace.homeTeam.avaterAbsolutePath}" alt="" style="height: 100px;width: 100px;">
                        </a>
                        <p style="text-align: center;">${teamRace.homeTeam.name}</p>
                    </div>
                    <div style="float: left; text-align: center;line-height: 110px;margin-right: 10px;">
                        VS
                    </div>
                    <div style="float: left">
                        <a onclick="$team.fn.sfTeamInfo(${teamRace.visitingTeam.id})" class="pirobox_gall img-popup" title="${teamRace.visitingTeam.name}">
                            <img src="${teamRace.visitingTeam.avaterAbsolutePath}" alt="" style="height: 100px;width: 100px;">
                        </a>
                        <p style="text-align: center;">${teamRace.visitingTeam.name}</p>
                    </div>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>预约城市:</label>
                    <input type="text" id="city" name="city" value="${teamRace.city.city}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>预约状态:</label>
                    <c:if test="${teamRace.status eq 0}"><input type="text" id="status" name="status" value="等待中" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${teamRace.status eq 1}"><input type="text" id="status" name="status" value="预约成功" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                    <c:if test="${teamRace.status eq 2}"><input type="text" id="status" name="status" value="预约失败" class="input-sm form-control validate[required]" placeholder="..." disabled></c:if>
                </div>
                <c:if test="${teamRace.status eq 1}">
                    <div class="col-md-6 m-b-15">
                        <label>比赛地点:</label>
                        <input type="text" id="sName" name="sName" value="${teamRace.address}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                </c:if>
                <div class="col-md-6 m-b-15">
                    <label>比赛时间:</label>
                    <input type="text" id="startDate" name="startDate" value="<date:date format='yyyy年MM月dd日 HH:mm' value='${teamRace.startDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>约定时间:</label>
                    <input type="text" id="createDate" name="createDate" value="<date:date format='yyyy年MM月dd日 HH:mm' value='${teamRace.createDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
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
    $team = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
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

