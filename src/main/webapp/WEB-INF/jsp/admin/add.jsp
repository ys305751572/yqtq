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
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${admin.id}">
                <input type="hidden" id="userRole" name="userRole" value="${userRole}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>账号：</label>
                        <input type="text" id="username" name="username" value="${admin.username}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>密码：</label>
                        <input type="password" id="password" name="password" value="${admin.password}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <div style="float: left;margin-right: 10px;">
                            <label>权限:</label>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <c:forEach var="v" items="${role}">
                                <div style="float: left;margin-right: 10px;">
                                    <input type="checkbox" name="roles" value="${v.id}" />${v.name}
                                </div>
                        </c:forEach>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="admin_add.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    admin_add = {
        v: {
            list: [],
            chart : null,
            dTable: null,
            ids:[]
        },
        fn: {
            init: function () {
                var id = $('#id').val();
                if (null != id && id != '') {
                    var userRole = $('#userRole').val();
                    console.log(userRole);
                    var array = userRole.split(',');
                    console.log(array);
                    for (var i = 0; i < array.length; i++) {
                        $('input:checkbox[name="roles"]').each(function () {
                            if ($(this).val() == array[i]) {
                                $(this).iCheck("check");
                            }
                        });
                    }
                }
            },
            save : function () {
                var cb = $('input:checkbox[name="roles"]:checked');
                var username = $("#username").val();
                var password = $("#password").val();
                var id = $("#id").val();
                var isCheck = true;
                if($("#username").val()==""){
                    $leoman.notify('用户名不能为空', "error");
                    isCheck=false;
                }
                if($("#password").val()==""){
                    $leoman.notify('密码不能为空', "error");
                    isCheck=false;
                }
                if(cb.length==0){
                    $leoman.notify('权限不能为空', "error");
                    isCheck=false;
                }
                if(isCheck){
                    admin_add.v.ids = [];
                    cb.each(function(){
                        admin_add.v.ids.push($(this).val());
                    });
                    console.log(admin_add.v.ids);
                    var ids = JSON.stringify(admin_add.v.ids);
                    console.log(ids);
                    $.ajax({
                        url : "${contextPath}/admin/admin/save",
                        type : "POST",
                        data : {
                            "ids" : ids,
                            "username" : username,
                            "password" : password,
                            "id" : id
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/admin/index";
                        }
                    });
                }

            },
        }
    }
    $(function () {
        admin_add.fn.init();
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

