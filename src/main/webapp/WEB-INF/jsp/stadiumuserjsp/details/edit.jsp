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
    <input type="hidden" value="个人管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">新增球场主</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadiumUser.id}">
                <input type="hidden" id="cId" name="cId" value="${stadiumUser.city.cityId}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>名称：</label>
                        <input type="text" id="nickName" name="nickName" value="${stadiumUser.nickName}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>电话：</label>
                        <input type="text" id="mobile" name="mobile" value="${stadiumUser.mobile}" maxlength="11" class="form-control input-sm validate[required,custom[phone]]" placeholder="..." onkeyup="value=value.replace(/[^0-9]/g,'')" >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>银行：</label>
                        <input type="text" id="bank" name="bank" value="${stadiumUser.bank}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>银行卡号：</label>
                        <input type="text" id="bankCardNumber" name="bankCardNumber" value="${stadiumUser.bankCardNumber}" class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9]/g,'')" >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>省份：</label>
                        <select id="provinceId" name="provinceId" class="select" >
                            <option value="">省份</option>
                            <c:forEach items="${province}" var="v" >
                                <option value="${v.provinceId}" <c:if test="${stadiumUser.province.provinceId eq v.provinceId}">selected</c:if> >${v.province}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>城市：</label>
                        <select id="cityId" name="cityId" class="select">
                            <option value="">城市</option>
                        </select>
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
                $("#formId").validationEngine();
                var opt=$("#provinceId").val();
                $user.fn.selectCity(opt);
            },
            save : function () {
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                var isCheck = true;
                if($("#nickName").val()==""){
                    $leoman.notify('姓名不能为空', "error");
                    isCheck=false;
                    return;
                }
//                if($("#mobile").val()==""){
//                    $leoman.notify('电话不能为空', "error");
//                    isCheck=false;
//                }
                if($("#bank").val()==""){
                    $leoman.notify('银行不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#bankCardNumber").val()==""){
                    $leoman.notify('银行卡号不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#provinceId").val()==""){
                    $leoman.notify('省份不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#cityId").val()==""){
                    $leoman.notify('城市不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(isCheck){
                    var code =  $('.wysiwye-editor').code();
                    $("#formId").ajaxSubmit({
                        url : "${contextPath}/stadium/details/save",
                        type : "POST",
                        data : {
                            "detail" : code
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
            },
            selectCity : function(data){
                var cId = $("#cId").val();
                if(data!=""){
                    $.ajax({
                        url:"${contextPath}/stadium/details/selectCity",
                        data:{
                            "provinceId":data
                        },
                        success:function(data){
                            $("#cityId").empty();
                            var op = "<option value=''>请选择城市</option>";
                            for(var i= 0;i<data.length;i++){
                                var cityId = data[i].cityId;
                                var city = data[i].city;
                                if(cId==cityId){
                                    op += "<option value='"+cityId+"' selected>"+city+"</option>";
                                }else{
                                    op += "<option value='"+cityId+"'>"+city+"</option>";
                                }
                            }
                            $("#cityId").append(op);
                            $("#cityId").selectpicker('refresh');
                        }
                    });
                }else{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>"+"城市"+"</option>");
                    $("#cityId").selectpicker('refresh');
                }
            }
        }
    }
    $(function () {
        $user.fn.init();
        $("#provinceId").change(function(){
            var opt=$("#provinceId").val();
            $user.fn.selectCity(opt);
        })
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

