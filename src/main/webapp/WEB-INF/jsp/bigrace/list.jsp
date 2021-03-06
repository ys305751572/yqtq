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
    <input type="hidden" id="mian_module" value="看球管理">
    <input type="hidden" id="child_module" value="大型赛事列表">
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
                    <input type="text" class="input-sm form-control" id="teamName" name="teamName" placeholder="比赛队伍">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
            <div style="float:right;margin-right:25px;">
                <a data-toggle="modal" href="${contextPath}/admin/bigRace/edit" title="新增" class="btn btn-alt m-r-5" >新增</a>
            </div>
        </div>
        <%--<div class="block-area">--%>
            <%--<div class="row">--%>
                <%--<ul class="list-inline list-mass-actions">--%>
                    <%--<li>--%>
                        <%--<a data-toggle="modal" href="${contextPath}/admin/bigRace/edit" title="新增" class="tooltips">--%>
                            <%--<i class="sa-list-add"></i>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>比赛队伍</th>
                    <th>比赛名称</th>
                    <th>地区</th>
                    <th>比赛场地</th>
                    <th>比赛时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </section>
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <div align=center>
                        <h4 class="modal-title" id="showText" ></h4>
                    </div>
                </div>
                <div class="modal-body" align="center">
                    <button type="button" id="confirm" class="btn btn-primary">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    $bigRace = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $bigRace.fn.dataTableInit();

                $("#c_search").click(function () {
                    $bigRace.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $bigRace.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/bigRace/list",
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
                            "render":function(data,type,full){
                                return full.team1name + "&nbsp;" + "vs" + "&nbsp;" + full.team2name;
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "stadium.city.city","sDefaultContent" : ""},
                        {"data": "stadium.name","sDefaultContent" : ""},
                        {
                            "data": "startDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "status",
                            render:function(data){
                                if(data==0){
                                    return "—";
                                }else{
                                    return "禁用";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$bigRace.fn.sfInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$bigRace.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var id = data;
                                var st = full.status;
                                if(st==0){
                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' onclick=\"$bigRace.fn.changeStatus(\'" + id + "\',\'" + st + "\')\"> " +
                                            "禁用</button>";
                                }else if(st==1){
                                    var status = "<button title='启用' class='btn btn-primary btn-circle detail' onclick=\"$bigRace.fn.changeStatus(\'" + id + "\',\'" + st + "\')\"> " +
                                            "启用</button>";
                                }
                                return detail + "&nbsp;" + status + "&nbsp;" +edit;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.teamName = $("#teamName").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.provinceId = $("#province").val();
                        aoData.status = $("#status").val();
                    }
                });
            },
            "changeStatus": function (id,st) {
                var tempStatus = 0;
                if(st==0){
                    $('#showText').html('您确定要禁用该比赛吗？');
                    tempStatus = 1;
                }else if(st==1){
                    $('#showText').html('您确定要解禁该比赛吗？');
                }
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/bigRace/status",
                        "data": {
                            "id": id,
                            "status":tempStatus
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $bigRace.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/bigRace/sfInfo",
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
                        window.location.href = "${contextPath}/admin/bigRace/detail?id=" + id;
                    }
                });
            },
            edit: function (id){
                window.location.href = "${contextPath}/admin/bigRace/edit?id=" + id;
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
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $bigRace.v.dTable.ajax.reload(null, false);
                    } else {
                        $bigRace.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $bigRace.fn.init();
        $("#province").change(function(){
            var opt=$("#province").val();
            $bigRace.fn.selectCity(opt);
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

