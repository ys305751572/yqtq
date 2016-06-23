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
        <h1 class="page-title">场地列表</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-6 m-b-15">
                    <label>图片:</label>
                    <img src="${contextPath}/${stadium.avater}" alt="">
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>球场名称:</label>
                    <input type="text" id="name" name="name" value="${stadium.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    <a data-toggle="modal" href="${contextPath}/admin/stadium/edit?id=${stadium.id}" title="编辑" class="tooltips">
                        <i>编辑</i>
                    </a>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>城市:</label>
                    <input type="text" id="city" name="city" value="${stadium.city.city}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <c:if test="${stadium.type eq 0}">
                    <div class="col-md-6 m-b-15">
                        <label>球场主:</label>
                        <a href="${contextPath}/admin/stadiumUser/detail?id=${stadium.stadiumUserId}"><input type="text" id="nickName" name="nickName" value="${stadiumUser.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled></a>
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
                <c:if test="${stadium.type eq 0}">
                    <div class="col-md-6 m-b-15">
                        <label>球场场次:</label>
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
                $team.fn.initImage();
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/1/sfInfo",
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
                        window.location.href = "${contextPath}/admin/1/detail?id=" + id;
                    }
                });
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

