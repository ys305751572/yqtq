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
        <h1 class="page-title">管理人员</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <c:if test="${isEdit ne null }"><input type="hidden" id="id" name="id" value="${module.id}"></c:if>
                <input type="hidden" id="isEditModule" name="isEdit" value="${isEdit}">
                <input type="hidden" id="isChild" name="isChild" value="${isChild}">
                <input type="hidden" id="isParent" name="isParent" value="${isParent}">
                <div class="row">
                    <c:if test="${isChild ne null or (isEdit ne null and isParent eq null)}">
                        <div class="col-md-6 m-b-15">
                            <label>父模块名称：</label>
                            <c:if test="${isChild ne null}">
                                <input type="text" id="parentName" name="parentName" value="${module.name}" class="input-sm form-control validate[required]" <c:if test="${isChild ne null}">disabled="disabled"</c:if> >
                                <input type="hidden" id="moduleParentId" name="parentId" value="${module.id}" >
                            </c:if>
                            <c:if test="${isEdit ne null and isParent eq null}">
                                <input type="text" id="parentName" name="parentName" value="${module.parent.name}" class="input-sm form-control validate[required]" <c:if test="${isEdit ne null}">disabled="disabled"</c:if> >
                                <input type="hidden" id="moduleParentId" name="parentId" value="${module.parent.id}" >
                            </c:if>
                        </div>
                    </c:if>
                    <div class="col-md-6 m-b-15">
                        <label>模块名称：</label>
                        <input type="text" id="name" name="name" value="<c:if test="${isEdit ne null}">${module.name}</c:if>" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <c:if test="${isChild ne null or (isEdit ne null and isParent eq null)}">
                        <div class="col-md-6 m-b-15">
                            <label>URL：</label>
                            <input type="text" id="url" name="url" value="${module.url}" class="input-sm form-control validate[required]" placeholder="...">
                        </div>
                    </c:if>
                <div class="col-md-6 m-b-15">
                    <label>模块描述：</label>
                    <input type="text" id="description" name="description" value="<c:if test="${isEdit ne null}">${module.description}</c:if>" class="input-sm form-control validate[required]" placeholder="...">
                </div>
                <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="module_add.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    module_add = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
            },
            save : function () {
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                var moduleParentId = $("#moduleParentId").val();
                var parentName = $("#parentName").val();
                var name = $("#name").val();
                var isChild = $("#isChild").val();
                var isEdit = $("#isEditModule").val();
                var isCheck = true;
                if($("#parentId").val()==""){
                    $leoman.notify('父模块不能为空', "error");
                    isCheck=false;
                }
                if($("#name").val()==""){
                    $leoman.notify('模块名称不能为空', "error");
                    isCheck=false;
                }
                if($("#url").val()==""){
                    $leoman.notify('URL不能为空', "error");
                    isCheck=false;
                }
                console.log(isCheck);
                if(isCheck){
                    $("#formId").ajaxSubmit({
                        url : "${contextPath}/admin/module/save",
                        type : "POST",
                        data : {
                            "moduleParentId" : moduleParentId,
                            "parentName" : parentName
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/module/index";
                        }
                    });
                }

            },
        }
    }
    $(function () {
        module_add.fn.init();

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

