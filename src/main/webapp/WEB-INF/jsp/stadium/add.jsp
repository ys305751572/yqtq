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
        <h1 class="page-title">场地编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadium.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>球场名称：</label>
                        <input type="text" id="name" name="name" value="${stadium.name}" class="input-sm form-control validate[required]" placeholder="..." >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>省份：</label>
                        <select id="province" name="province" class="select" >
                            <option value="">省份</option>
                            <c:forEach items="${province}" var="v" >
                                <option value="${v.provinceId}" >${v.province}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>城市：</label>
                        <select id="cityId" name="cityId" class="select">
                            <option value="">城市</option>
                        </select>
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
                                <c:if test="${stadium.avater ne null}">
                                    <img src="${stadium.avater}">
                                </c:if>
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
                    <div class="col-md-6 m-b-15">
                        <label>球场简介: </label>
                        <div class="wysiwye-editor" id="detail" name="detail">${stadium.description}</div>
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
                $(".iCheck-helper").click(function() {
                    $user.fn.hid();
                });
            },
            hid:function(){
                if($(".icheckbox_minimal").attr("aria-checked")=="true"){
                    $("#div_hidden").css('display','block');
                }else {
                    $("#div_hidden").css('display','none');
                }
            },
            save : function () {
                var code =  $('.wysiwye-editor').code();
                var sid= "${stadium.id}";
                var avater= "${stadium.avater}";
                var description = "${stadium.description}";
                console.log(sid);
                console.log(avater);
                var isCheck = true;
                if($("#name").val()==""){
                    alert("球场名称不能为空!");
                    isCheck=false;
                }
                if($("#cityId").val()==""){
                    alert("城市不能为空!");
                    isCheck=false;
                }
                if($("#siteType").val()=="" || $("#sodType").val()=="" || $("#light").val()=="" || $("#park").val()=="" || $("#giving").val()==""){
                    alert("球场基本信息不完整!");
                    isCheck=false;
                }
                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    alert("球场封面不能为空!");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    alert("球场简介不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/stadium/save",
                        type : "POST",
                        data : {
                            "detail" : code
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
                if(data!=""){
                    $.ajax({
                        url:"${contextPath}/admin/reserve/selectCity",
                        data:{
                            "provinceId":data
                        },
                        success:function(data){
                            $("#cityId").empty();
                            for(var i= 0;i<data.length;i++){
                                var cityId = data[i].cityId;
                                var city = data[i].city;
                                var op = "<option value='"+cityId+"'>"+city+"</option>";
                                $("#cityId").append(op);
                                if(i==0){
                                }
                            }
                            $("#cityId").selectpicker('refresh');
                        }
                    });
                }else{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>"+"城市"+"</option>");
                    $("#cityId").selectpicker('refresh');
                }
            }
//            check : function(){
//                if(escape($("#name").val()).indexOf("%u")<0 || $("#name").val()==""){
//                    alert( "球场名称格式不正确,请输入中文!" );
//                    $("#name").val("");
//                    return false;
//                }
//                return true;
//            }
        }
    }
    $(function () {
        $user.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
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

