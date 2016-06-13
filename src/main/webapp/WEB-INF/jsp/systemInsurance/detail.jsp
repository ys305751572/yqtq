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
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">保险详情</h1>
        <div class="block-area">
            <h2 class="tile-title">基本信息</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-6 m-b-15">
                    <label>保险名字:</label>
                    <%--<date:date format="yyyy-MM-dd HH:mm" value="${reserve.startDate}"></date:date>--%>
                    <input type="text" id="name" value="${systemInsurance.name}" name="name" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>单价:</label>
                    <input type="text" id="price" name="price" class="input-sm form-control validate[required]" placeholder="..." disabled value="${systemInsurance.price}/份" />
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>状态：</label>
                    <input type="text" id="status" value="${systemInsurance.status}" name="status" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>保险供应商：</label>
                    <input type="text" id="company" value="${systemInsurance.company}" name="company" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>保险说明：</label>
                    <div class="wysiwye-editor"  id="detail" name="detail" disabled>${systemInsurance.content}</div>
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
    var systemInsurance = {
        v: {
            id: "post",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {

                var status = $("#status").val();
                if (status == 0) {
                    $("#status").val("正常");
                }else{
                    $("#status").val("失效");
                }
                $.ajaxSetup({
                    async: false
                });

            },

        }
    }
    $(function () {
        systemInsurance.fn.init();
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

