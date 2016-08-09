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
    <style>
        .asd{
        height: 60px;
        width: 100px;
        background-color: #3b97d7!important;
        }
    </style>
</head>
<body id="skin-cloth">
<%@ include file="../inc/new/header.jsp" %>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <input type="hidden" value="球场管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">场地列表</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-6 m-b-15">
                    <label>图片:</label>
                    <img src="${stadium.avaterAbsolutePath}" alt="">
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>球场名称:</label>
                    <input type="text" id="name" name="name" value="${stadium.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>

                </div>
                <div class="col-md-6 m-b-15">
                    <label>省:</label>
                    <input type="text" id="province" name="province" value="${stadium.province.province}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>城市:</label>
                    <input type="text" id="city" name="city" value="${stadium.city.city}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>详细地址:</label>
                    <input type="text" id="address" name="address" value="${stadium.address}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <c:if test="${stadium.type eq 0}">
                    <div class="col-md-6 m-b-15">
                        <label>球场主:</label>
                        <a class="btn btn-block btn-alt" style="font-size: 12px;height: 30px;text-align:left;text-indent:0.5em;background-color:rgba(238, 238, 238, 0.09) !important;" href="${contextPath}/admin/stadiumUser/detail?id=${stadium.stadiumUserId}">${stadiumUser.nickName}</a>
                    </div>
                </c:if>
                <hr class="whiter m-t-20"/>
                <div>
                    <div class="col-md-6 m-b-15">
                        <label>场地类型:</label>
                        <input type="text" id="siteType" name="siteType" value="<c:if test='${stadium.siteType eq 0}'>室内</c:if><c:if test='${stadium.siteType eq 1}'>室外</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>草皮类型:</label>
                        <input type="text" id="sodType" name="sodType" value="${stadium.sodType}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>灯光:</label>
                        <input type="text" id="light" name="light" value="${stadium.light}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>停车场:</label>
                        <input type="text" id="park" name="park" value="<c:if test='${stadium.park eq 0}'>无</c:if><c:if test='${stadium.park eq 1}'>免费</c:if><c:if test='${stadium.park eq 2}'>收费</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>赠送:</label>
                        <input type="text" id="giving" name="giving" value="${stadium.giving}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                </div>
                <hr class="whiter m-t-20"/>
                <c:if test="${stadium.type eq 0}">
                    <div class="col-md-6 m-b-15">
                        <label>球场场次:</label>
                        <p><button type="checkbox" name="time" style="height: 60px;width: 100px;background-color: #3b97d7">13:00-15:00</button></p>
                        <p><button type="checkbox" name="time" style="height: 60px;width: 100px;background-color: #3b97d7">15:00-17:00</button></p>
                        <p><button type="checkbox" name="time" style="height: 60px;width: 100px;background-color: #3b97d7">17:00-19:00</button></p>
                        <p><button type="checkbox" name="time" style="height: 60px;width: 100px;background-color: #3b97d7">19:00-21:00</button></p>
                        <p><button type="checkbox" name="time" style="height: 60px;width: 100px;background-color: #3b97d7">21:00-23:00</button></p>
                        <input type="checkbox" name="time" class="asd" >123</input>
                    </div>
                </c:if>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>球场简介:</label>
                    <div class="wysiwye-editor" id="description" name="description">${stadium.description}</div>
                </div>
                <hr class="whiter m-t-20"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <c:if test="${isEd eq null}">
                        <a class="btn btn-info btn-sm m-t-10" data-toggle="modal" href="${contextPath}/admin/stadium/edit?id=${stadium.id}" title="编辑" class="tooltips">
                            编辑
                        </a>
                    </c:if>
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

