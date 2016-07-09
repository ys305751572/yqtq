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
        .from1{
            float: left;
            margin-right: 1px;
            margin-top: 10px;
        }
    </style>
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
        <h1 class="page-title">会员设置</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <div class="row">
                    <div class="col-md-6 m-b-15" style="width: auto">
                        <label>VIP会员价格:</label>
                        <input type="hidden" id="systemVipsId" name="systemVipsId" value="${systemVips.id}">
                        <input type="text" id="price" name="price" value="${systemVips.price}" maxlength="10" class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9.]/g,'')">
                        <a onclick="$user.fn.systemVipsSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>设置VIP级别:</label>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${systemVipLevels}" var="v">
                    <div class="col-md-12 m-b-15">
                        <div style="float: left">
                            <input style="width: auto;border-right:none;" type="text" id="1" name="1" value="Lv ${v.level}" class="input-sm form-control validate[required]" disabled>
                        </div>
                        <div style="float: left">
                            <input style="width: auto;border-right:none;border-left:none;" type="text" id="2" name="2" value="${v.experience} 经验" class="input-sm form-control validate[required]" disabled>
                        </div>
                        <div style="float: left">
                            <input style="width: 156px;border-left:none;" type="text" id="3" name="3" value="${v.preferente} 折" class="input-sm form-control validate[required]" disabled>
                        </div>
                    </div>
                    <br/>
                    </c:forEach>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <div class="from1">
                            <label>等级:</label>
                            <input type="text" id="level1" name="level" value="" maxlength="2" class="input-sm form-control " style="width: auto" onkeyup="value=value.replace(/[^0-9]/g,'')">
                            <a onclick="$user.fn.systemVipsLevelSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                        </div>
                        <div class="from1">
                            <label>经验:</label>
                            <input type="text" id="experience" name="experience" value="" maxlength="8" class="input-sm form-control  " style="width: auto" onkeyup="value=value.replace(/[^0-9]/g,'')">
                        </div>
                        <div class="from1">
                            <label>折扣:</label>
                            <input type="text" id="preferente" name="preferente" maxlength="3" value="" class="input-sm form-control  " style="width: auto" onkeyup="value=value.replace(/[^0-9.]/g,'')">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15" style="width: 164px;">
                        <label>设置获得经验:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15" style="width: 164px;">
                        <label>选择对应等级:</label>
                        <select id="systemVipId" class="select" >
                            <c:forEach items="${systemVipLevels}" var="v">
                                <option value="${v.systemVipId}">Lv ${v.level}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <div class='from1'>
                            <label>散客组队成功:</label>
                            <input style='width: auto' type='text' id="a" value='' class='input-sm form-control validate[required]' disabled>
                        </div>
                        <div class='from1'>
                            <label>场地预订:</label>
                            <input style='width: auto' type='text' id="b" value='' class='input-sm form-control validate[required]' disabled>
                        </div>
                        <div class='from1'>
                            <label>约看宝贝:</label>
                            <input style='width: auto' type='text' id="c" value='' class='input-sm form-control validate[required]' disabled>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15" style="width: 164px;">
                        <select id="action" class="select">
                            <option value="1">散客组队成功</option>
                            <option value="2">场地预订</option>
                            <option value="3">约看宝贝</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <input type="text" id="vipExperience" name="vipExperience" value="" class="input-sm form-control " maxlength="6" style="width: auto" onkeyup="value=value.replace(/[^0-9]/g,'')">
                        <a onclick="$user.fn.systemVipExperienceSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group" >
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
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
            },
            systemVipsSave : function () {
                var isCheck = true;
                var price = $("#price").val();
                if(price==""){
                    $leoman.notify('VIP会员价格不能为空', "error");
                    isCheck=false;
                }
                if(price.length!=0){
                    var reg = /^([0-9]{1,8})([.]{0,1})([0-9]{0,2})$/;
                    if(!reg.test(price)){
                        $leoman.notify('错误的VIP会员价格', "error");
                        $("#price").val(${systemVips.price});
                        isCheck=false;
                    }
                }
                if(isCheck){
                    var code =  $('#systemVipsId').val();
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/systemVipSetting/systemVipSave",
                        type : "POST",
                        data : {
                            "id" : code
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.reload();
                        }
                    });
                }
            },
            systemVipsLevelSave : function () {
                var isCheck = true;
                var preferente = $("#preferente").val();
                if($("#level1").val()=="" ){
                    $leoman.notify('等级不能为空', "error");
                    isCheck=false;
                }
                if($("#experience").val()=="" ){
                    $leoman.notify('经验不能为空', "error");
                    isCheck=false;
                }
                if(preferente=="" ){
                    $leoman.notify('折扣不能为空', "error");
                    isCheck=false;
                }
                if(preferente.length!=0){
                    var reg = /^([0-9])([.])([0-9])$/;
                    var reg1 = /^([0-9])$/;
                    if(reg1.test(preferente)){
                        isCheck=isCheck;
                    }else if(!reg.test(preferente)){
                        $leoman.notify('错误的折扣', "error");
                        $("#preferente").val("");
                        isCheck=false;
                    }
                }
                if(isCheck){
                    var level = $("#level1").val();
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/systemVipSetting/systemVipLevelSave",
                        type : "POST",
                        data : {
                            "level1" : level
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.reload();
                        }
                    });
                }
            },
            systemVipExperienceSave : function(){
                var isCheck = true;
                if($("#vipExperience").val()==""){
                    $leoman.notify('经验设定不能为空', "error");
                    isCheck=false;
                }

                if(isCheck){
                    var experience = $("#vipExperience").val();
                    var systemVipId =  $('#systemVipId').val();
                    var action =  $('#action').val();
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/systemVipSetting/systemVipExperienceSave",
                        type : "POST",
                        data : {
                            "experience" : experience,
                            "systemVipId" : systemVipId,
                            "action" : action
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            $user.fn.vipExperienceFrom(systemVipId);
                        }
                    });
                }
            },
            vipExperienceFrom : function(data){
                $.ajax({
                    url:"${contextPath}/admin/systemVipSetting/vipExperienceFrom",
                    data:{
                        "systemVipId":data
                    },
                    success:function(data){
                        $("#a").val("");
                        $("#b").val("");
                        $("#c").val("");
                        for(var i= 0;i<data.length;i++){
                            var action = data[i].action;
                            var experience = data[i].experience;
                            if(action==1){
                                $("#a").val(experience+"点经验");
                            }
                            if(action==2){
                                $("#b").val(experience+"点经验");
                            }
                            if(action==3){
                                $("#c").val(experience+"点经验");
                            }
                        }
                    }
                });
            }
        }
    };
    $(function () {
        $user.fn.init();
        $user.fn.vipExperienceFrom($("#systemVipId").val());
        $("#systemVipId").change(function(){
            $("#vipExperience").val("");
            var opt=$("#systemVipId").val();
            $user.fn.vipExperienceFrom(opt);
        });
        $("#action").change(function(){
            $("#vipExperience").val("");
        });
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

