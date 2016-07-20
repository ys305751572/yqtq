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
        <h1 class="page-title">新增球场主</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadiumUser.id}">
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <label>密码：</label>
                        <input onblur="$changePwd.fn.checkPwd()" type="password" id="originalPwd" name="originalPwd" value="" maxlength="12" class="input-sm form-control validate" placeholder="请输入登录密码" style="width: 50%">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>新密码：</label>
                        <input type="password" id="newPwd" name="newPwd" value="" maxlength="12" class="input-sm form-control validate" placeholder="输入新密码" style="width: 50%" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" >
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>确认新密码：</label>
                        <input type="password" id="confirmPwd" name="confirmPwd" value="" maxlength="12" class="input-sm form-control validate" placeholder="再次输入新密码" style="width: 50%" onkeyup="value=value.replace(/[^0-9]/g,'')">
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$changePwd.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    $changePwd = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $("#fromId").validationEngine();
            },
            checkPwd : function(){
                var originalPwd = $("#originalPwd").val();
                $.ajax({
                    url : "${contextPath}/stadium/details/checkPwd",
                    type : "POST",
                    data : {
                        "password" : originalPwd
                    },
                    success : function(result) {
                        if(!result.status) {
                            $leoman.notify(result.msg,"error");
                            return;
                        }
                    }
                })
            },
            save : function () {
                var isCheck = true;
                var originalPwd = $("#originalPwd").val();
                var newPwd = $("#newPwd").val();
                var confirmPwd = $("#confirmPwd").val();
                if(originalPwd==""){
                    $leoman.notify('原密码不能为空', "error");
                    isCheck=false;
                }
                if(originalPwd.length<6){
                    $leoman.notify('原密码长度不能小于六位', "error");
                    isCheck=false;
                }
                if(originalPwd==(newPwd)){
                    $leoman.notify('新密码不能和原密码相同', "error");
                    isCheck=false;
                }
                if(newPwd==""){
                    $leoman.notify('新密码不能为空', "error");
                    isCheck=false;
                }
                if(newPwd.length<6){
                    $leoman.notify('新密码长度不能小于六位', "error");
                    isCheck=false;
                }
                if(confirmPwd==""){
                    $leoman.notify('确认密码不能为空', "error");
                    isCheck=false;
                }
                if(newPwd!=(confirmPwd)){
                    $leoman.notify('两次密码不相同', "error");
                    isCheck=false;
                }

                if(isCheck){
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/stadium/details/savePwd",
                        type : "POST",
                        data : {
                            "password" : confirmPwd
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/stadium/details/index";
                        }
                    });
                }
            }
        }
    }
    $(function () {
        $changePwd.fn.init();
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

