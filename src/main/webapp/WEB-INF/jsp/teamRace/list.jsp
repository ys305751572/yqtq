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
    <input type="hidden" id="mian_module" value="球队管理">
    <input type="hidden" id="child_module" value="赛事管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="teamName" name="teamName" placeholder="比赛">
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
                <%--<div class="col-md-2 form-group">--%>
                    <%--<select id="stadiumId" name="stadiumId" class="select">--%>
                        <%--<option value="">球场</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
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
                    <th>比赛队伍</th>
                    <th>比赛城市</th>
                    <th>比赛地址</th>
                    <th>比赛时间</th>
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
    $teamRace = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $teamRace.fn.dataTableInit();
                $("#c_search").click(function () {
                    $teamRace.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $teamRace.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/teamRace/list",
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
                        {
                            "data": "",
                            "render": function (data,type,full) {
                                return full.homeTeam.name +"&nbsp; VS &nbsp;"+full.visitingTeam.name;
                            },"sDefaultContent" : ""
                        },
                        {"data": "city.city","sDefaultContent" : ""},
                        {"data": "address","sDefaultContent" : ""},
                        {
                            "data": "startDate",
                            "render":function(data){
                                return new Date(data).format("yyyy年MM月dd日 hh:mm:ss");
                            },"sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$teamRace.fn.sfTeamRaceInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.teamName = $("#teamName").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.provinceId = $("#province").val();
//                        aoData.id = $("#stadiumId").val();
                    }
                });
            },
            sfTeamRaceInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/teamRace/sfTeamRaceInfo",
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
                        window.location.href = "${contextPath}/admin/teamRace/detail?id=" + id;
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
                            var op = "<option value=''>请选择城市</option>";
                            for(var i= 0;i<data.length;i++){
                                var cityId = data[i].cityId;
                                var city = data[i].city;
                                op += "<option value='"+cityId+"'>"+city+"</option>";
                                if(i==0){
                                    $teamRace.fn.selectStadium(cityId);
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
                    $teamRace.fn.selectStadium();
                }
            },
            selectStadium : function(data){
                if(data!=null){
                    $.ajax({
                        url:"${contextPath}/admin/reserve/selectStadium",
                        data:{
                            "cityId":data
                        },
                        success:function(data){
                            if(data.length>0){
                                $("#stadiumId").empty();
                                $("#stadiumId").append("<option value=''>"+"请选择球场"+"</option>");
                                for(var i= 0;i<data.length;i++){
                                    var id = data[i].id;
                                    var name = data[i].name;
                                    var op = "<option value='"+id+"'>"+name+"</option>";
                                    $("#stadiumId").append(op);
                                }
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
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $teamRace.v.dTable.ajax.reload(null, false);
                    } else {
                        $teamRace.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $teamRace.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
            $teamRace.fn.selectCity(opt);
        })
//        $("#cityId").change(function(){
//            var opt=$("#cityId").val();
//            $teamRace.fn.selectStadium(opt);
//        })

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

