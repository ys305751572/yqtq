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
        <h1 class="page-title">宝贝编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${girl.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>宝贝名称：</label>
                        <input type="text" id="name" name="name" value="${girl.name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>地区:</label>
                        <select id="cityId" name="cityId" class="select" >
                            <option value="${girl.city.cityId}">${girl.city.city eq null ? "请选择" : girl.city.city}</option>
                            <c:forEach items="${city}" var="c">
                                <option value="${c.cityId}">${c.city}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>年龄：</label>
                        <input type="text" id="age" name="age" value="${girl.age}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>身高：</label>
                        <input type="text" id="height" name="height" value="${girl.height}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>体重：</label>
                        <input type="text" id="weight" name="weight" value="${girl.weight}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>服务价格:</label>
                        <input type="text" id="price" name="price" value="${girl.price}" class="input-sm form-control validate[required]">
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>兴趣爱好:</label>
                        <input type="text" id="interest" name="interest" value="${girl.interest}" class="input-sm form-control validate[required]">
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>喜欢球队:</label>
                        <input type="text" id="favoriteTeam" name="favoriteTeam" value="${girl.favoriteTeam}" class="input-sm form-control validate[required]">
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>职业:</label>
                        <input type="text" id="profession" name="profession" value="${girl.profession}" class="input-sm form-control validate[required]">
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>签名:</label>
                        <input type="text" id="label" name="label" value="${girl.label}" class="input-sm form-control validate[required]">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>宝贝封面：</label>
                        <%--<div class="fileupload fileupload-new" data-provides="fileupload">--%>
                            <%--<div class="fileupload-preview thumbnail form-control">--%>
                                <%--<c:if test="${image.type eq 0}">--%>
                                    <%--<img src="${image.url}">--%>
                                <%--</c:if>--%>
                            <%--</div>--%>
                            <%--<div>--%>
                                <%--<span class="btn btn-file btn-alt btn-sm">--%>
                                    <%--<span class="fileupload-new">选择图片</span>--%>
                                    <%--<span class="fileupload-exists">更改</span>--%>
                                    <%--<input id="imageFile" name="imageFile" type="file"/>--%>
                                <%--</span>--%>
                                <%--<a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>宝贝相册：</label>
                        <%--<div class="fileupload fileupload-new" data-provides="fileupload">--%>
                            <%--<div class="fileupload-preview thumbnail form-control">--%>
                                <%--<c:if test="${image.type eq 1}">--%>
                                    <%--<img src="${image.url}">--%>
                                <%--</c:if>--%>
                            <%--</div>--%>
                            <%--<div>--%>
                                <%--<span class="btn btn-file btn-alt btn-sm">--%>
                                    <%--<span class="fileupload-new">选择图片</span>--%>
                                    <%--<span class="fileupload-exists">更改</span>--%>
                                    <%--<input id="imageFile1" name="imageFile" type="file"/>--%>
                                <%--</span>--%>
                                <%--<a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$user.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
            },
            save : function () {
                $("#fromId").ajaxSubmit({
                    url : "${contextPath}/admin/girl/save",
                    type : "POST",
                    success : function(result) {
                        if(!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        window.location.href = "${contextPath}/admin/girl/index";
                    }
                });
            }
        }
    }
    $(function () {
        $user.fn.init();
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

