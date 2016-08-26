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
    <input type="hidden" id="mian_module" value="APP相关设置">
    <input type="hidden" id="child_module" value="保险列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">新增保险</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${systemInsurance.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>保险名字：</label>
                        <input type="text" id="name" name="name" value="${systemInsurance.name}"
                               class="input-sm form-control validate[required]" placeholder="..." >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>保险单价：</label>
                        <input type="text" id="parce" name="price" value="${systemInsurance.price}"
                               class="input-sm form-control validate[required]" placeholder="..." maxlength="10" onkeyup="value=value.replace(/[^0-9.]/g,'')">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>事故保金：</label>
                        <input type="text" id="bj" name="bj" value="${systemInsurance.bj}"
                               class="input-sm form-control validate[required]" placeholder="..." maxlength="10" onkeyup="value=value.replace(/[^0-9.]/g,'')">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>投保公司：</label>
                        <input type="text" id="company" name="company" value="${systemInsurance.company}"
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>保险详情</label>
                        <div class="wysiwye-editor">${systemInsurance.content}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="systemInsurance.fn.save();" class="btn btn-info btn-sm m-t-10">提交
                        </button>
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
    systemInsurance = {
        v: {
            list: [],
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
                systemInsurance.fn.initImage();
            },
            save: function () {
                var isCheck = true;
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                var price =$("#parce").val();
                var bj = $("#bj").val();
                if($("#name").val()=="" || $("#name").val()==null) {
                    $leoman.notify('保险名称不能为空', "error");
                    isCheck = false;
                    return;
                }
                if(price=="" || price==null) {
                    $leoman.notify('保险单价不能为空', "error");
                    isCheck = false;
                    return;
                }
                if(price.length!=0){
                    var reg = /^([0-9]{1,8})([.]{0,1})([0-9]{0,2})$/;
                    if(!reg.test(price)){
                        $leoman.notify('错误的保险单价', "error");
                        $("#parce").val("");
                        isCheck=false;
                        return;
                    }
                }
                if(bj=="" || bj==null) {
                    $leoman.notify('事故保金不能为空', "error");
                    isCheck = false;
                    return;
                }
                if(bj.length!=0){
                    var reg = /^([0-9]{1,8})([.]{0,1})([0-9]{0,2})$/;
                    if(!reg.test(price)){
                        $leoman.notify('错误的事故保金', "error");
                        $("#bj").val("");
                        isCheck=false;
                        return;
                    }
                }
                if($("#company").val()=="" || $("#company").val()==null) {
                    $leoman.notify('投保公司不能为空', "error");
                    isCheck = false;
                    return;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('保险详情不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(isCheck){
                    var code = $('.wysiwye-editor').code();
                    $("#formId").ajaxSubmit({
                        url: "${contextPath}/admin/systemInsurance/save",
                        type: "POST",
                        data: {
                            "detail": code
                        },
                        success: function (result) {
                            if (result == 1) {
                                $common.fn.notify("操作成功", "success");
                                window.location.href = "${contextPath}/admin/systemInsurance/index";
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                }
            }
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

