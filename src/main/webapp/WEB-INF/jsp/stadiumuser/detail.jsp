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
        <h1 class="page-title">看球详情</h1>
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadiumUser.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>场主名称:</label>
                        <input type="text" id="nickName" name="nickName" value="${stadiumUser.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>地区:</label>
                        <input type="text" id="cityId" value="${stadiumUser.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>球场:</label>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>电话:</label>
                        <input type="text" id="invitation" name="invitation" value="${stadiumUser.mobile}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>总预定金额:</label>
                        <input type="text" id="description" name="description" value="${stadiumUser.reserveMoney}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>总提现金额:</label>
                        <input type="text" id="withdrawMoney" name="description" value="${stadiumUser.withdrawMoney}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>帐户余额:</label>
                        <input type="text" id="balance" name="balance" value="${stadiumUser.balance}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>今日预定金额:</label>
                        <input type="text" id="1" name=" " value=" " class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>散客:</label>
                        <input type="text" id="2" name=" " value=" " class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>状态:</label>
                        <c:if test="${stadiumUser.status eq 0}">
                            <input type="text" id="status" name="status" value="正常" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </c:if>
                        <c:if test="${stadiumUser.status eq 1}">
                            <input type="text" id="status" name="status" value="封禁" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </c:if>
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

