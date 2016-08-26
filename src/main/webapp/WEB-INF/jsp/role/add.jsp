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
    <input type="hidden" id="mian_module" value="权限管理">
    <input type="hidden" id="child_module" value="角色列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">管理权限</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${role.id}">
                <input type="hidden" id="roleModule" name="roleModule" value="${roleModule}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>角色名称：</label>
                        <input type="text" id="name" name="name" value="${role.name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="block-area" id="tableBordered">
                        <div class="col-md-12 m-b-15">
                            <h5>权限名称:</h5>
                            <div class="table-responsive overflow">
                                <table class="tile table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th width="12%">序号</th>
                                        <th width="12%">功能名称</th>
                                        <th>子模块名称</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="n" items="${mainModuleList}" varStatus="index">
                                        <tr>
                                            <td>${index.index + 1}</td>
                                            <td>${n.name}</td>
                                            <td>
                                                <c:forEach var="m" items="${subModuleList}">
                                                    <c:if test="${m.parent.id eq n.id}">
                                                    <div style="float: left;margin-right: 10px;">
                                                        <input type="checkbox" name="modules" value="${m.id}" />${m.name}
                                                    </div>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="role_add.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    role_add = {
        v: {
            list: [],
            chart : null,
            dTable: null,
            ids: []
        },
        fn: {
            init: function () {
                var id = $('#id').val();
                if (null != id && id != '') {
                    var roleModule = $('#roleModule').val();
                    console.log(roleModule);
                    var array = roleModule.split(',');
                    console.log(array);
                    for (var i = 0; i < array.length; i++) {
                        $('input:checkbox[name="modules"]').each(function () {
                            if ($(this).val() == array[i]) {
                                $(this).iCheck("check");
                            }
                        });
                    }
                }
            },
            save : function () {
                var cb = $('input:checkbox[name="modules"]:checked');
                var isCheck = true;
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                if($("#name").val()==""){
                    $leoman.notify('角色名称不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(cb.length==0){
                    $leoman.notify('子模块不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(isCheck){
                    role_add.v.ids = [];
                    cb.each(function(){
                        role_add.v.ids.push($(this).val());
                    });
                    console.log(role_add.v.ids);
                    $("#formId").ajaxSubmit({
                        url : "${contextPath}/admin/role/save",
                        type : "POST",
                        data : {
                            "ids" : JSON.stringify(role_add.v.ids)
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/role/index";
                        }
                    });
                }

            }
        }
    };
    $(function () {
        role_add.fn.init();
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

