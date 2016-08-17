<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
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
    <input type="hidden" id="mian_module" value="互动管理">
    <input type="hidden" id="child_module" value="活动列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">活动详情</h1>
        <div class="block-area">
            <h2 class="tile-title">基本信息</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-6 m-b-15">
                    <label>活动标题:</label>
                    <%--<date:date format="yyyy-MM-dd HH:mm" value="${reserve.startDate}"></date:date>--%>
                    <input type="text" id="title" value="${activity.title}" name="nickName" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>发布时间:</label>
                    <input type="text" id="createDate" name="createDate" class="input-sm form-control validate[required]" placeholder="..." disabled value="<date:date value="${activity.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date>" />
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>封面：</label>
                    <img style="height: 200px;width: 300px;" src="${activity.aveter}" alt="">
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>活动内容：</label>
                    <div class="wysiwye-editor"  id="detail" name="detail" disabled>${activity.description}</div>
                </div>
                <%--<div class="col-md-6 m-b-15">
                    <label>操作:</label>
                    <button title='封禁' class='btn btn-primary btn-circle changeStatus' onclick='$post.fn.changeStatus()'></button>
                </div>--%>
                <hr class="whiter m-t-20"/>

            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    var post = {
        v: {
            id: "post",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $.ajaxSetup({
                    async: false
                });

            },

        }
    }
    $(function () {
        post.fn.init();
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

