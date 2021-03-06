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
    <input type="hidden" id="mian_module" value="散客管理">
    <input type="hidden" id="child_module" value="散客列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <input type="hidden" id="details" name="details" value="${details}">
            <div class="row">
                <div class="col-md-2 form-group">
                    <select id="province" name="province" class="select" >
                        <option value="">省份</option>
                        <c:forEach items="${province}" var="v" >
                            <option value="${v.provinceId}" >${v.province}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="cityId" name="cityId" class="select">
                        <option value="">城市</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="stadiumId" name="stadiumId" class="select">
                        <option value="">球场</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="matchType" name="matchType" class="select">
                        <option value="">赛制</option>
                        <option value="3">三人制</option>
                        <option value="5">五人制</option>
                        <option value="7">七人制</option>
                        <option value="11">十一人制</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="payment" name="payment" class="select">
                        <option value="">付款方式</option>
                        <option value="0">AA制</option>
                        <option value="1">全额</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="insuranceId" name="insuranceId" class="select">
                        <option value="">保险</option>
                        <option value="1">5万元经济型意外保险</option>
                        <option value="2">10万元基础型意外保险</option>
                        <option value="3">100万元高端型意外保险</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">组队状态</option>
                        <option value="0">正在组队</option>
                        <option value="1">组队成功</option>
                        <option value="2">组队失败</option>
                        <option value="3">比赛结束</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>比赛时间</th>
                    <th>城市</th>
                    <th>球场</th>
                    <th>创建者</th>
                    <th>赛制</th>
                    <th>付款方式</th>
                    <th>保险</th>
                    <th>已报人数</th>
                    <th>预定时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    $reserve = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $reserve.fn.dataTableInit();

                $("#c_search").click(function () {
                    $reserve.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $reserve.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/reserve/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
//                                var checkbox = "<div class=\"icheckbox_minimal\" aria-checked=\"false\" aria-disabled=\"false\" style=\"position: relative;\"><input type=\"checkbox\" value="+ data +" class='pull-left list-check' style=\"position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\"></div>";
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {"data": "startDate",
                            render: function (data) {
                                return new Date(data).format("yyyy年MM月dd日 hh:mm")
                            },"sDefaultContent" : ""
                        },
                        {"data": "stadium.city.city","sDefaultContent" : ""},
                        {"data": "stadium.name","sDefaultContent" : ""},
                        {"data": "user.nickName","sDefaultContent" : ""},
                        {"data": "matchType",
                            render:function(data){
                                if(data==3){
                                    return "三人制";
                                }
                                if(data==5){
                                    return "五人制";
                                }
                                if(data==7){
                                    return"七人制";
                                }
                                if(data==11){
                                    return"十一人制";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "payment",
                            render:function(data) {
                                if (data == 0) {
                                    return "AA制";
                                }
                                if (data == 1) {
                                    return "全额";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {"data":"systemInsurance.name","sDefaultContent" : ""},
                        {"data":"num","sDefaultContent" : ""},
                        {"data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy年MM月dd日 hh:mm")
                            },"sDefaultContent" : ""
                        },
                        {"data": "status",
                            render: function (data) {
                                if (data==0){
                                    return"正在组队";
                                }
                                if (data==1){
                                    return"组队成功";
                                }
                                if (data==2){
                                    return"组队失败";
                                }
                                if (data==3){
                                    return"比赛结束";
                                }
                            },"sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' ONCLICK='$reserve.fn.detail("+ data +")'> " +
                                        "<i class='fa fa-eye'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.provinceId = $("#province").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.stadiumId = $("#stadiumId").val();
                        aoData.matchType = $("#matchType").val();
                        aoData.payment = $("#payment").val();
                        aoData.id = $("#insuranceId").val();
                        aoData.status = $("#status").val();
                        aoData.details = $("#details").val();
                    }
                });
            },
            rowCallback: function (row, data) {
                var items = $reserve.v.list;
                $('td', row).last().find(".add").attr("href", 'admin/reserve/detail?id=' + data.id);
            },
            selectCity : function(data){
                if(data!=null && data!=""){
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
                                op += "<option value='"+cityId+"'>"+city+"</option>";
//                                if(i==0){
//                                    $reserve.fn.selectStadium(cityId);
//                                }
                            }
                        $("#cityId").append(op);
                        $("#cityId").selectpicker('refresh');
                    }
                    });
                }else{
                    $("#cityId").empty();
                    $("#cityId").append("<option value=''>"+"城市"+"</option>");
                    $("#cityId").selectpicker('refresh');
                    $reserve.fn.selectStadium();
                }
            },
            selectStadium : function(data){
                if(data!=null && data!=""){
                    $.ajax({
                        url:"${contextPath}/admin/reserve/selectStadium",
                        data:{
                            "cityId":data
                        },
                        success:function(data){
                            if(data.length>0){
                                $("#stadiumId").empty();
                                var op ="<option value=''>请选择球场</option>";
                                for(var i= 0;i<data.length;i++){
                                    var id = data[i].id;
                                    var name = data[i].name;
                                    op += "<option value='"+id+"'>"+name+"</option>";
                                }
                                $("#stadiumId").append(op);
                                $("#stadiumId").selectpicker('refresh');
                            }else{
                                $("#stadiumId").empty();
                                $("#stadiumId").append("<option value=''>"+"暂无球场"+"</option>");
                                $("#stadiumId").selectpicker('refresh');
                            }
                        }
                    });
                }else{
                    $("#stadiumId").empty();
                    $("#stadiumId").append("<option value=''>"+"球场"+"</option>");
                    $("#stadiumId").selectpicker('refresh');
                }
            },
            "detail" : function(id) {
                window.location.href = "${contextPath}/admin/reserve/detail?id=" + id;
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $reserve.v.dTable.ajax.reload(null, false);
                    } else {
                        $reserve.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }



    $(function () {
        $reserve.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
            $reserve.fn.selectCity(opt);
            $reserve.fn.selectStadium();
        })
        $("#cityId").change(function(){
            var opt=$("#cityId").val();
            $reserve.fn.selectStadium(opt);
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

