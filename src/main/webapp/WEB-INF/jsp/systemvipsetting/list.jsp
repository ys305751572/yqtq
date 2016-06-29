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
            float: left;
            margin-right: 10px;
            margin-top: 10px;
        }
        .from1{
            float: left;
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
                    <div class="col-md-3 m-b-15">
                        <label>VIP会员价格:</label>
                        <input type="hidden" id="systemVipsId" name="systemVipsId" value="${systemVips.id}">
                        <input type="text" id="price" name="price" value="${systemVips.price}" class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9.]/g,'')">
                        <a onclick="$user.fn.systemVipsSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 m-b-15">
                        <label>设置VIP级别:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 m-b-15">
                        <c:forEach items="${systemVipLevels}" var="v">
                            <%--<input type="text" id="systemVipLevel" name="systemVipLevel" value="Lv ${v.level}               ${v.experience} 经验               ${v.preferente} 折" class="input-sm form-control validate[required]" disabled>--%>
                        <div class="from1">
                            <input style="width: 134px" type="text" id="1" name="1" value="Lv ${v.level}" class="input-sm form-control validate[required]" disabled>
                        </div>
                        <div class="from1">
                            <input style="width: 134px" type="text" id="2" name="2" value="${v.experience} 经验" class="input-sm form-control validate[required]" disabled>
                        </div>
                        <div class="from1">
                            <input style="width: 134px" type="text" id="3" name="3" value="${v.preferente} 折" class="input-sm form-control validate[required]" disabled>
                        </div>
                        </c:forEach>
                        <div class="asd">
                            <label>等级:</label>
                            <input type="text" id="level1" name="level" value="" maxlength="2" class="input-sm form-control validate[required]" style="width: 123px" onkeyup="value=value.replace(/[^0-9]/g,'')">
                        </div>
                        <div class="asd">
                            <label>经验:</label>
                            <input type="text" id="experience" name="experience" value="" class="input-sm form-control validate[required]" style="width: 123px" onkeyup="value=value.replace(/[^0-9]/g,'')">
                        </div>
                        <div class="asd">
                            <label>折扣:</label>
                            <input type="text" id="preferente" name="preferente" maxlength="3" value="" class="input-sm form-control validate[required]" style="width: 123px" onkeyup="value=value.replace(/[^0-9.]/g,'')">
                        </div>
                        <a onclick="$user.fn.systemVipsLevelSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-3 m-b-15">
                        <label>设置获得经验:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1 m-b-15">
                        <select id="systemVipId" class="select" >
                            <c:forEach items="${systemVipLevels}" var="v">
                                <option value="${v.systemVipId}">Lv ${v.level}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 m-b-15">
                        <div id="systemVipExperience"></div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
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
                if($("#price").val()==""){
                    alert("VIP会员价格不能为空!");
                    isCheck=false;
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
                if($("#level").val()==""){
                    alert("等级不能为空!");
                    isCheck=false;
                }
                if($("#experience").val()==""){
                    alert("经验不能为空!");
                    isCheck=false;
                }
                if($("#preferente").val()==""){
                    alert("折扣不能为空!");
                    isCheck=false;
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
            vipExperienceFrom : function(data){
                $.ajax({
                    url:"${contextPath}/admin/systemVipSetting/vipExperienceFrom",
                    data:{
                        "systemVipId":data
                    },
                    success:function(data){
                        $("#cityId").empty();
                        for(var i= 0;i<data.length;i++){
                            var cityId = data[i].cityId;
                            var city = data[i].city;
                            var op = "<option value='"+cityId+"'>"+city+"</option>";
                            $("#cityId").append(op);
                            if(i==0){
                                $reserve.fn.selectStadium(cityId);
                            }
                        }
                        $("#cityId").selectpicker('refresh');
                    }
                });
            }
        }
    }
    $(function () {
        $user.fn.init();
        $("#systemVipId").change(function(){
            var opt=$("#systemVipId").val();
            $user.fn.vipExperienceFrom(opt);
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

