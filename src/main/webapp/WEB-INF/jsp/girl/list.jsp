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
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="宝贝姓名">
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">全部</option>
                        <option value="0">可预约</option>
                        <option value="1">不可预约</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="appointment" name="appointment" class="select">
                        <option value="">全部</option>
                        <option value="1">今天有预约</option>
                        <option value="2">今天无预约</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
            <div style="float:right;margin-right:25px;">
                <a data-toggle="modal" href="${contextPath}/admin/girl/edit" title="新增" class="btn btn-alt m-r-5" >新增</a>
            </div>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>地区</th>
                    <th>宝贝昵称</th>
                    <th>宝贝年龄</th>
                    <th>身高</th>
                    <th>体重</th>
                    <th>价格/场</th>
                    <th>状态</th>
                    <th>预约次数</th>
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
                        "url": "${contextPath}/admin/girl/list",
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
                        {"data": "city.city","sDefaultContent" : ""},
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "age","sDefaultContent" : ""},
                        {"data": "height","sDefaultContent" : ""},
                        {"data": "weight","sDefaultContent" : ""},
                        {"data": "price", "sDefaultContent" : ""},
                        {
                            "data": "status",
                            "render":function(data){
                                if(data==0){
                                    return "可预约"
                                }else if(data==1){
                                    return "不可预约"
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "guSize","sDefaultContent" : ""},//预约次数
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$girl.fn.sfInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$girl.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var st = full.status;
                                if(st==0){
                                    var status = "<button title='不可预约' class='btn btn-primary btn-circle detail' onclick='$girl.fn.close("+ data +")'> " +
                                            "不可预约</button>";
                                }else if(st==1){
                                    var status = "<button title='可预约' class='btn btn-primary btn-circle detail' onclick='$girl.fn.open("+ data +")'> " +
                                            "可预约</button>";
                                }
                                return detail + "&nbsp;" +edit + "&nbsp;" + status;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.status = $("#status").val();
                        aoData.appointment = $("#appointment").val();
                    }
                });
            },
            close:function (data){
                if(confirm('您确定设置宝贝为不可预约吗？')){
                    $girl.fn.status(data);
                }
            },
            open:function (data){
                if(confirm('您确定设置宝贝为可预约吗？')){
                    $girl.fn.status(data);
                }
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

