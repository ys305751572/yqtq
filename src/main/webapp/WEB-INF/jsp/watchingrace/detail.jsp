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
    <input type="hidden" value="看球管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">看球详情</h1>
        <form action="${contextPath}/admin/watchingRace/save" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${watchingRace.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <img src="${watchingRace.avaterAbsolutePath}" alt="">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>看球名称:</label>
                        <input type="text" id="name" name="name" value="${watchingRace.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>地区:</label>
                        <input type="text" id="cityId" value="${watchingRace.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>创建时间:</label>
                        <input type="text" id="createDate" name="createDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${watchingRace.createDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>邀请次数:</label>
                        <input type="text" id="invitation" name="invitation" value="${watchingRace.invitation}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>看球详情</label>
                        <div class="wysiwye-editor" id="detail" name="detail">${watchingRace.description}</div>
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

