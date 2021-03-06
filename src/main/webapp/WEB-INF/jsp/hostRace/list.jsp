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
    <input type="hidden" id="mian_module" value="平台赛事管理">
    <input type="hidden" id="child_module" value="平台赛事列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <%--<div class="block-area">--%>
            <%--<div class="row">--%>
                <%--<ul class="list-inline list-mass-actions">--%>
                    <%--<li>--%>
                        <%--<a data-toggle="modal" href="${contextPath}/admin/hostRace/edit" title="新增" class="tooltips">--%>
                            <%--<i class="sa-list-add"></i>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="block-area" id="alternative-buttons">
            <div>
                <a data-toggle="modal" href="${contextPath}/admin/hostRace/edit" title="新增" class="btn btn-alt m-r-5">新增</a>
            </div>
        </div>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>比赛名称</th>
                    <th>比赛地区</th>
                    <th>状态</th>
                    <th>赛制</th>
                    <th>参赛队数</th>
                    <th>比赛时间</th>
                    <th>球场地址</th>
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
    $hostRace = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $hostRace.fn.dataTableInit();

                $("#c_search").click(function () {
                    $hostRace.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $hostRace.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/hostRace/list",
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
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "stadium.city.city","sDefaultContent" : ""},//地区
                        {
                            "data": "status",
                            "render":function (data){
                                if(data==0){
                                    return "未开始";
                                }else if(data==1){
                                    return "进行中";
                                }else if(data==2){
                                    return "已结束";
                                }else {
                                    return "";
                                }
                            },"sDefaultContent" : ""
                        },
                        {"data": "matchType",
                            render:function(data){
                                if(data==3){
                                    return "三人制";
                                }else if(data==5){
                                    return "五人制";
                                }else if(data==7){
                                    return"七人制";
                                }else if(data==11){
                                    return"十一人制";
                                }else{
                                    return "";
                                }
                            },"sDefaultContent" : ""
                        },
                        {"data": "hrNum","sDefaultContent" : ""},
                        {
                            "data": "startDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },"sDefaultContent" : ""
                        },
                        {"data": "stadium.address","sDefaultContent" : ""},
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' ONCLICK='$hostRace.fn.sfInfo("+ data +")'> " +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$hostRace.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var id = data;
                                var st = full.status;
                                if(st!=2){
                                    var del = "<button title='关闭' class='btn btn-primary btn-circle ' onclick=\"$hostRace.fn.close(\'" + id + "\')\"> " +
                                            "关闭</button>";

                                }else{
                                    var del = "<button title='关闭' class='btn btn-primary btn-circle ' disabled " +
                                            "<i>关闭</i></button>";
                                }
                                return detail+ "&nbsp;" +edit+ "&nbsp;" +del;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {

                    }
                });
            },
            close:function (id){
                $('#showText').html('您确定要关闭该比赛吗？');
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/hostRace/close",
                        "data": {
                            "id": id
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $hostRace.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/hostRace/sfInfo",
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
                        window.location.href = "${contextPath}/admin/hostRace/detail?id=" + id;
                    }
                });
            },
            "edit" : function(id) {
                window.location.href = "${contextPath}/admin/hostRace/edit?id=" + id;
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $hostRace.v.dTable.ajax.reload(null, false);
                    } else {
                        $hostRace.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $hostRace.fn.init();
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

