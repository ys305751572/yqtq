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
    <input type="hidden" id="mian_module" value="球场管理">
    <input type="hidden" id="child_module" value="球场列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">场地编辑</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadium.id}">
                <input type="hidden" id="cId" name="cId" value="${stadium.city.cityId}">
                <input type="hidden" id="aId" name="aId" value="${stadium.area.areaId}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>球场名称：</label>
                        <input type="text" id="name" name="name" value="${stadium.name}" class="input-sm form-control validate[required]" placeholder="..." >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>省份：</label>
                        <select id="provinceId" name="provinceId" class="select" >
                            <option value="">省份</option>
                            <c:forEach items="${province}" var="v" >
                                <option value="${v.provinceId}" <c:if test="${stadium.province.provinceId eq v.provinceId}">selected</c:if>>${v.province}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>城市：</label>
                        <select id="cityId" name="cityId" class="select">
                            <option value="">城市</option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>区：</label>
                        <select id="areaId" name="areaId" class="select">
                            <option value="">区</option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>详细地址：</label>
                        <input type="text" id="address" name="address" value="${stadium.address}"  class="input-sm form-control validate[required]" placeholder="..."  disabled>
                        <input type="hidden" id="lng" name="longitude" value="${stadium.longitude}" >
                        <input type="hidden" id="lat" name="latitude" value="${stadium.latitude}" >
                        <a onclick="$stadium.fn.map()" class="btn btn-alt m-r-5" style="margin-top: 10px">选择</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>球场基本信息:</label>
                        <input type="checkbox" id="chk" />
                        <div class="div_hidden" id="div_hidden" style="display: none">
                            <label>场地类型：</label>
                                <select id="siteType" name="siteType" class="select">
                                    <option value='1'  <c:if test="${stadium.siteType eq 1}">selected</c:if>>室外</option>
                                    <option value='0' <c:if test="${stadium.siteType eq 0}">selected</c:if>>室内</option>
                                </select>
                            <label>草皮类型：</label>
                            <input type="text" id="sodType" name="sodType" value="${stadium.sodType}" class="input-sm form-control validate[required]" placeholder="...">
                            <label>灯光：</label>
                            <input type="text" id="light" name="light" value="${stadium.light}" class="input-sm form-control validate[required]" placeholder="...">
                            <label>停车场：</label>
                                <select id="park" name="park" class="select">
                                    <option value="0" <c:if test="${stadium.park eq 0}">selected</c:if>>无</option>
                                    <option value="1" <c:if test="${stadium.park eq 1}">selected</c:if>>免费</option>
                                    <option value="2" <c:if test="${stadium.park eq 2}">selected</c:if>>收费</option>
                                </select>
                            <label>赠送：</label>
                            <input type="text" id="giving" name="giving" value="${stadium.giving}" class="input-sm form-control validate[required]" placeholder="...">
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>球场封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${stadium.avaterAbsolutePath}">
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile" name="imageFile" type="file"/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>球场简介: </label>
                        <div class="wysiwye-editor" id="detail" name="detail">${stadium.description}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$stadium.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    $stadium = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $(".iCheck-helper").click(function() {
                    $stadium.fn.hid();
                });

                var opt=$("#provinceId").val();
                $stadium.fn.selectCity(opt);
            },
            hid:function(){
                if($(".icheckbox_minimal").attr("aria-checked")=="true"){
                    $("#div_hidden").css('display','block');
                }else {
                    $("#div_hidden").css('display','none');
                }
            },
            save : function () {
                var isCheck = true;
                if(!$("#formId").validationEngine("validate")) {
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
                if($("#address").val()==""){
                    $leoman.notify('详细地址不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#siteType").val()=="" || $("#sodType").val()=="" || $("#light").val()=="" || $("#park").val()=="" || $("#giving").val()==""){
                    $leoman.notify('球场基本信息不完整', "error");
                    isCheck=false;
                    return;
                }
                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    $leoman.notify('球场封面不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('球场简介不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(isCheck){
                    var code =  $('.wysiwye-editor').code();
                    var address = $("#address").val();
                    var lng = $("#lng").val();
                    var lat = $("#lat").val();
                    var lnglat = {
                        longitude : $("#lng").val(),
                        latitude : $("#lat").val()
                    }
                    $("#formId").ajaxSubmit({
                        url : "${contextPath}/admin/stadium/save",
                        type : "POST",
                        data : {
                            "detail" : code,
                            "address" : address,
                            "lnglat" : lnglat
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/stadium/index";
                        }
                    });
                }
            },
            selectCity : function(data){
                console.log("selectCity (provinceId):");
                console.log(data);
                var cId = $("#cId").val();
                if(data!=""){
                    console.log("selectCitydata!=''");
                    $.ajax({
                        url:"${contextPath}/admin/reserve/selectCity",
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
                            $stadium.fn.selectArea($("#cityId").val());
                        }
                    });
                }else{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=\"\">"+"城市"+"</option>");
                    $("#cityId").selectpicker('refresh');
                    $stadium.fn.selectArea($("#cityId").val());
                    console.log("进到city")
                }
            },
            selectArea : function(data){
                var aId = $("#aId").val();
                console.log("selectArea (cityId):");
                console.log(data);
                if(data!=""){
                    console.log("dataselectArea!=''");
                    $.ajax({
                        url:"${contextPath}/admin/reserve/selectArea",
                        data:{
                            "cityId":data
                        },
                        success:function(data){
                            $("#areaId").empty();
                            var op = "<option value=''>请选择区</option>";
                            for(var i= 0;i<data.length;i++){
                                var areaId = data[i].areaId;
                                var area = data[i].area;
                                if(aId==areaId){
                                    op += "<option value='"+areaId+"' selected>"+area+"</option>";
                                }else{
                                    op += "<option value='"+areaId+"'>"+area+"</option>";
                                }
                            }
                            $("#areaId").append(op);
                            $("#areaId").selectpicker('refresh');
                        }
                    });
                }else{
                    $("#areaId").empty();
                    $("#areaId").append("<option value=''>"+"区"+"</option>");
                    $("#areaId").selectpicker('refresh');
                }
            },
            map : function(){
                var iWidth=1000; //弹出窗口的宽度;
                var iHeight=600; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                window.open("${contextPath}/admin/stadium/map","地图","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
            }
        }
    }
    $(function () {
        $stadium.fn.init();
        $("#provinceId").change(function(){
            var opt=$("#provinceId").val();
            $stadium.fn.selectCity(opt);
        });
        $("#cityId").change(function(){
            console.log("进来cityChange了")
            var opt=$("#cityId").val();
            $stadium.fn.selectArea(opt);
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

