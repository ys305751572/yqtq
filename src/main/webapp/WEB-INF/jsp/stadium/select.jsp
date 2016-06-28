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
    <style type="text/css">
        section#content.container{
            margin-left: 0px;
        }
    </style>
</head>
<body id="skin-cloth">
<%--<%@ include file="../inc/new/header.jsp" %>--%>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <%--<%@ include file="../inc/new/menu.jsp" %>--%>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="球场名称">
                </div>
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
                    <select id="type" name="type" class="select">
                        <option value="">球场类型</option>
                        <option value="0">私有</option>
                        <option value="1">公有</option>
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
                    <th>城市</th>
                    <th>球场名称</th>
                    <th>球场类型</th>
                    <th>确认</th>
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
    $stadium = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadium.fn.dataTableInit();
                $("#c_search").click(function () {
                    $stadium.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $stadium.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/stadium/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "city.city","sDefaultContent" : ""},
                        {"data": "name"},
                        {
                            "data": "type",
                            "render":function(data){
                                if(data==0){
                                    return "私人球场";
                                }else {
                                    return "公共球场";
                                }
                            }
                        },
                        {
                            "data": "",
                            "render": function (data,type,full) {
                                var id = full.id;
                                var name = full.name;
                                var sure = "<button title='确定' class='btn btn-primary btn-circle add' onclick=\"$stadium.fn.sure("+id+",'"+name+ "')\">" +
                                        "确定</button>";
                                return sure;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.type = $("#type").val();
                    },
                });
            },
            sure : function(id,name){
                if(window.opener){
                    window.opener.document.all.stadiumName.value = name;
                    window.opener.document.all.stadiumId.value = id;
                    window.close();
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
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadium.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadium.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }

        }
    }
    $(function () {
        $stadium.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
            $stadium.fn.selectCity(opt);
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