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
    <input type="hidden" id="mian_module" value="平台赛事管理">
    <input type="hidden" id="child_module" value="平台赛事列表">
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
                    <label>赛事封面:</label>
                </div>
                <div class="col-md-12 m-b-15">
                    <img src="${hostRace.avaterAbsolutePath}" alt="" style="height: 200px;width: 300px;">
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>赛事名称:</label>
                    <input type="text" id="name" value="${hostRace.name}" name="name" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>赛事地点:</label>
                    <input type="text" id="address" value="${hostRace.stadium.address}" name="address" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15">
                    <label>赛事详情:</label>
                    <div class="wysiwye-editor" id="detail" name="detail">${hostRace.description}</div>
                </div>
                <hr class="whiter m-t-20"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <button type="button" class="btn btn-info btn-sm m-t-10" onclick="$team.fn.information(${hostRace.id})">增加资讯</button>
                    <button type="button" class="btn btn-info btn-sm m-t-10" onclick="$team.fn.sign(${hostRace.id})">报名队伍 ( ${matchTeam} )</button>
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
            information : function (id) {
                window.location.href = "${contextPath}/admin/hostRace/information?id="+id;
            },
            sign : function (id) {
                window.location.href = "${contextPath}/admin/hostRaceJoin/index?id="+id;
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

