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
        <!-- 查询条件 -->
        <div class="block-area" id="search">
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
                    <input type="text" class="input-sm form-control" id="girlName" name="girlName" placeholder="宝贝名">
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="userName" name="userName" placeholder="约看人">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>地区</th>
                    <th>宝贝昵称</th>
                    <th>约看人</th>
                    <th>下单时间</th>
                    <th>预约时间</th>
                    <th>预定时长</th>
                    <th>预约球场</th>
                    <th>比赛</th>
                    <th>价格</th>
                    <th>打赏</th>
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
    $girl = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $girl.fn.dataTableInit();

                $("#c_search").click(function () {
                    $girl.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $girl.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/girlUser/list",
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
                        {"data": "girl.city.city","sDefaultContent" : ""},
                        {"data": "girl.name","sDefaultContent" : ""},
                        {"data": "user.nickName","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            "render": function(data){
                                return new Date(data).format("yyyy年MM月dd日 hh:mm");
                            },
                            "sDefaultContent" : ""},
                        {
                            "data": "startDate",
                            "render": function(data){
                                return new Date(data).format("yyyy年MM月dd日 hh:mm");
                            },
                            "sDefaultContent" : ""},
                        {"data": "duration","sDefaultContent" : ""},
                        {"data": "stadium.name","sDefaultContent" : ""},
                        {
                            "data": "teamRace",
                            "render":function(data,type,full){
                                return full.teamRace.homeTeam.name+ "vs"+full.teamRace.visitingTeam.name;
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "price", "sDefaultContent" : ""},
                        {"data": "tip","sDefaultContent" : ""}
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#girlName").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.nickName = $("#userName").val();
                    }
                });
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/girl/sfInfo",
                    "data": {
                        "id": id
                    },
                    "dataType": "json",
                    "type": "POST",
                    "success": function (result) {
                        if (!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        window.location.href = "${contextPath}/admin/girl/detail?id=" + id;
                    }
                });
            },
            edit: function (id){
                window.location.href = "${contextPath}/admin/girl/edit?id=" + id;
            },
            status : function(id) {
                $.ajax({
                    "url": "${contextPath}/admin/girl/status",
                    "data": {
                        "id": id
                    },
                    "dataType": "json",
                    "type": "POST",
                    success: function (result) {
                        if (!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        $girl.v.dTable.ajax.reload();
                    }
                });
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
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $girl.v.dTable.ajax.reload(null, false);
                    } else {
                        $girl.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $girl.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
            $girl.fn.selectCity(opt);
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

