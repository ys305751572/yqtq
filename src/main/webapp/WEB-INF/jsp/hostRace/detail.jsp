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
            <h2 class="tile-title">赛事详情</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-6 m-b-15">
                    <label>赛事名称:</label>
                    <input type="text" id="name" value="${hostRace.name}" name="name" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>赛事地点:</label>
                    <input type="text" id="address" value="${hostRace.stadium.address}" name="address" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>赛事封面:</label>
                    <img src="${contextPath}/${hostRace.avater}" alt="">
                </div>
                <div class="col-md-6 m-b-15">
                    <label>赛事详情:</label>
                    <input type="text" id="description" value="${hostRace.description}" name="description" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="block-area" style="margin-left: auto;margin-right: auto;">
                    <div class="row">
                        <ul class="list-inline list-mass-actions">
                            <li>
                                <a data-toggle="modal" href="${contextPath}/admin/hostRace/information?id=${hostRace.id}" title="增加咨询" class="tooltips">
                                    <i>增加咨询</i>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/admin/hostRaceJoin/index?id=${hostRace.id}" title="报名队伍" class="tooltips">
                                    <i>报名队伍</i>
                                </a>
                            </li>
                        </ul>
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
            }
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

