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
    <input type="hidden" id="mian_module" value="看球管理">
    <input type="hidden" id="child_module" value="大型赛事列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">看球详情</h1>
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${bigRace.id}">
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <label>比赛名称:</label>
                        <h3>${bigRace.name}</h3>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <div style="float: left">
                            <label>比赛队伍:</label>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <div style="float: left">
                            <img src="${bigRace.avater1AbsolutePath}" alt="" style="height: 100px;width: 100px;">
                        </div>
                        <div style="float: left; text-align: center;line-height: 110px;margin-right: 10px;margin-left: 10px;">
                            VS
                        </div>
                        <div style="float: left">
                            <img src="${bigRace.avater2AbsolutePath}" alt="" style="height: 100px;width: 100px;">
                        </div>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>地区:</label>
                        <input type="text" id="cityId" value="${bigRace.stadium.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>比赛地点:</label>
                        <input type="text" id="name" name="name" value="${bigRace.stadium.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>比赛时间:</label>
                        <input type="text" id="startDate" name="startDate" value="<date:date format='yyyy年MM月dd日 HH:mm' value='${bigRace.startDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
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

