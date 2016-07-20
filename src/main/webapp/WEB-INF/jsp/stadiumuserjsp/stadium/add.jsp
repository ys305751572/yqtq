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
        <h1 class="page-title">场地编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${stadium.id}">
                <input type="hidden" id="cId" name="cId" value="${stadium.city.cityId}">
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
                        <label>详细地址：</label>
                        <input type="text" id="address" name="address" value="${stadium.address}"  class="input-sm form-control " placeholder="..."  disabled>
                        <input type="hidden" id="lng" name="longitude" value="${stadium.longitude}" >
                        <input type="hidden" id="lat" name="latitude" value="${stadium.latitude}" >
                        <a onclick="$user.fn.map()" class="btn btn-alt m-r-5" style="margin-top: 10px">选择</a>
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
                    <div class="col-md-12 m-b-15">
                        <div><label>场地信息:</label></div>
                        <div id = "stadiumSubs"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 m-b-15">
                            <div class="from1">
                                <label>场地编号:</label>
                                <input type="text" id="code" name="code" value="" maxlength="2" class="input-sm form-control " style="width: auto" onkeyup="value=value.replace(/[^0-9]/g,'')">
                                <a onclick="$user.fn.addStadiumSub();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">添加</a>
                                <c:if test="${stadium.id ne null}">
                                    <a onclick="$user.fn.editStadiumSub();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">编辑</a>
                                </c:if>
                            </div>
                            <div class="from1" style="width: 164px;">
                                <label>赛制:</label>
                                <select id="type" class="select" >
                                    <option value="3">3人场</option>
                                    <option value="5">5人场</option>
                                    <option value="7">7人场</option>
                                    <option value="11">11人场</option>
                                </select>
                            </div>
                            <div class="from1">
                                <label>价格:</label>
                                <input type="text" id="price" name="price" maxlength="6" value="" class="input-sm form-control  " style="width: auto" onkeyup="value=value.replace(/[^0-9.]/g,'')">
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
            code: [],
            type: [],
            price: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $user.fn.stadiumSubFrom();
                $(".iCheck-helper").click(function() {
                    $user.fn.hid();
                });

                var opt=$("#provinceId").val();
                $user.fn.selectCity(opt);
            },
            hid:function(){
                if($(".icheckbox_minimal").attr("aria-checked")=="true"){
                    $("#div_hidden").css('display','block');
                }else {
                    $("#div_hidden").css('display','none');
                }
            },
            addStadiumSub : function(){
                var code = $("#code").val();
                var type = $("#type").val();
                var price = $("#price").val();
                var check = true;
                var html = "";
                html += "<div style='float: left;margin-right: 10px;' class='btn btn-alt m-r-5' name='addSub'>";
                html += "    <p>"+code+"号场地</p>                                                            ";
                html += "	<input type='hidden' value='"+code+"' name='code'>                                ";
                html += "    <p>("+type+"人场)</p>                                                            ";
                html += "	<input type='hidden' value='"+type+"' name='type'>					              ";
                html += "    <p>￥"+price+"/小时</p>                                                          ";
                html += "	<input type='hidden' value='"+price+"' name='price'>					          ";
                html += "</div>                                                                               ";
                $("#stadiumSubs input[name='code']").each(function(){
                    if($(this).val()==code){
                        $leoman.notify('已有相同号码的场地', "error");
                        check = false;
                    }
                });
                if(code==""){
                    $leoman.notify('场地编号不能为空', "error");
                    check=false;
                }
                if(price==""){
                    $leoman.notify('场地价格不能为空', "error");
                    check=false;
                }
                if(check){
                    $("#stadiumSubs").append(html);
                }


            },
            <%--stadiumSubSave : function(){--%>
                <%--var code = $("#code").val();--%>
                <%--var type = $("#type").val();--%>
                <%--var price = $("#price").val();--%>
                <%--var isCheck = true;--%>
                <%--if(code=="" ){--%>
                    <%--$leoman.notify('场地编号不能为空', "error");--%>
                    <%--isCheck=false;--%>
                <%--}--%>
                <%--if(price=="" ){--%>
                    <%--$leoman.notify('价格不能为空', "error");--%>
                    <%--isCheck=false;--%>
                <%--}--%>
                <%--if(isCheck){--%>
                    <%--$("#fromId").ajaxSubmit({--%>
                        <%--url : "${contextPath}/stadium/stadium/stadiumSubSave",--%>
                        <%--type : "POST",--%>
                        <%--data : {--%>
                            <%--"type" : type--%>
                        <%--},--%>
                        <%--success : function(result) {--%>
                            <%--if(!result.status) {--%>
                                <%--$common.fn.notify(result.msg);--%>
                                <%--return;--%>
                            <%--}--%>
<%--//                            window.location.reload();--%>
                            <%--$user.fn.stadiumSubFrom();--%>
                        <%--}--%>
                    <%--});--%>
                <%--}--%>
            <%--},--%>
            stadiumSubFrom :function(){
                var id = $("#id").val();
                $.ajax({
                    url:"${contextPath}/stadium/stadium/stadiumSubFrom",
                    data:{
                        "id":id
                    },
                    success:function(data){
                        $("#stadiumSubs").html("");
                        if(data!=null){
                            for(var i= 0;i<data.length;i++){
                                var code = data[i].code;
                                var type = data[i].type;
                                var price = data[i].price;
                                var html = "";
                                html += "<div style='float: left;margin-right: 10px;' class='btn btn-alt m-r-5' name='a'>";
                                html += "    <p>"+code+"号场地</p>                                             ";
                                html += "    <p>("+type+"人场)</p>                                             ";
                                html += "    <p>￥"+price+"/小时</p>                                           ";
                                html += "</div>                                                                 ";
                                $("#stadiumSubs").append(html);
                            }
                        }
                    }
                })
            },
            save : function () {
                var isCheck = true;

                if($("#name").val()==""){
                    $leoman.notify('球场名称不能为空', "error");
                    isCheck=false;
                }
                if($("#provinceId").val()==""){
                    $leoman.notify('省份不能为空', "error");
                    isCheck=false;
                }
                if($("#cityId").val()==""){
                    $leoman.notify('城市不能为空', "error");
                    isCheck=false;
                }
                if($("#address").val()==""){
                    $leoman.notify('详细地址不能为空', "error");
                    isCheck=false;
                }
                if($("#siteType").val()=="" || $("#sodType").val()=="" || $("#light").val()=="" || $("#park").val()=="" || $("#giving").val()==""){
                    $leoman.notify('球场基本信息不完整', "error");
                    isCheck=false;
                }
                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    $leoman.notify('球场封面不能为空', "error");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('球场简介不能为空', "error");
                    isCheck=false;
                }

                if(isCheck){
                    $user.v.code = [];
                    $("#stadiumSubs input[name='code']").each(function(){
                        $user.v.code.push($(this).val());
                    });
                    console.log("code:"+$user.v.code);
                    $user.v.type = [];
                    $("#stadiumSubs input[name='type']").each(function(){
                        $user.v.type.push($(this).val());
                    });
                    console.log("type:"+$user.v.type);
                    $user.v.price = [];
                    $("#stadiumSubs input[name='price']").each(function(){
                        $user.v.price.push($(this).val());
                    });
                    console.log("price:"+$user.v.code);
                    var code =  $('.wysiwye-editor').code();
                    var address = $("#address").val();
                    var lng = $("#lng").val();
                    var lat = $("#lat").val();
                    var lnglat = {
                        longitude : $("#lng").val(),
                        latitude : $("#lat").val()
                    };
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/stadium/stadium/save",
                        type : "POST",
                        data : {
                            "detail" : code,
                            "code" : JSON.stringify($user.v.code),
                            "type" : JSON.stringify($user.v.type),
                            "price" : JSON.stringify($user.v.price),
                            "address" : address,
                            "lnglat" : lnglat
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/stadium/stadium/index";
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
            },
            map : function(){
                var iWidth=1000; //弹出窗口的宽度;
                var iHeight=600; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                window.open("${contextPath}/stadium/stadium/map","地图","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
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

