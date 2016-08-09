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
    <input type="hidden" value="说明页面管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">vip等级说明</h1>
        <div class="block-area">
            <h2 class="tile-title">基本信息</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <div class="wysiwye-editor"  id="detail" name="detail" disabled>${vipLevelMessage.content}</div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button  class='btn btn-primary btn-circle edit' onclick='vipLevelMessage.fn.edit()'>编辑
                        </button>
                    </div>
                </div>
                <hr class="whiter m-t-20"/>

            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    var vipLevelMessage = {
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
            "edit": function () {
                window.location.href = "${contextPath}/admin/vipLevelMessage/edit"
            },

        }
    }
    $(function () {
        vipLevelMessage.fn.init();
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

