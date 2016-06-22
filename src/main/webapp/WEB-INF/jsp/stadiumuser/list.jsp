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
                    <input type="text" class="input-sm form-control" id="username" name="username" placeholder="账号">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <div class="block-area">
            <div class="row">
                <ul class="list-inline list-mass-actions">
                    <li>
                        <a data-toggle="modal" href="${contextPath}/admin/stadiumUser/add" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li>
                        <a data-toggle="modal" href="${contextPath}/admin/stadiumUserWithdraw/index" title="提现管理" class="tooltips">
                            <i class="sa-list-forwad"></i>
                        </a>
                    </li>
                    <li>
                        <a data-toggle="modal" href="${contextPath}/admin/1/1" title="权限列表" class="tooltips">
                            <i class="sa-list-forwad"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>账号</th>
                    <th>城市</th>
                    <th>场地数目</th>
                    <th>总金额</th>
                    <th>今日金额</th>
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
    $stadiumUser = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadiumUser.fn.dataTableInit();
                $("#c_search").click(function () {
                    $stadiumUser.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $stadiumUser.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/stadiumUser/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {"data": "username","sDefaultContent" : ""},
                        {"data": "city.city","sDefaultContent" : ""},
                        {"data": "stadiumNum","sDefaultContent" : ""},//球场数目
                        {"data": "reserveMoney","sDefaultContent" : ""},
                        {"data": "toDaySumPrice","sDefaultContent" : ""},
                        {"data": "status",
                            render:function(data){
                                if(data==0){
                                    return "—";
                                }else{
                                    return "封禁";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$stadiumUser.fn.sfInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$stadiumUser.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var st = full.status;
                                if(st==0){
                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' onclick='$stadiumUser.fn.close("+ data +")'> " +
                                            "<i>禁用</i></button>";
                                }else if(st==1){
                                    var status = "<button title='解禁' class='btn btn-primary btn-circle detail' onclick='$stadiumUser.fn.open("+ data +")'> " +
                                            "<i>解禁</i></button>";
                                }
                                return detail + "&nbsp;" + edit + "&nbsp;" +status;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.username = $("#username").val();
                    }
                });
            },
            close:function (data){
                if(confirm('您确定要禁用该用户吗？')){
                    $stadiumUser.fn.status(data);
                }
            },
            open:function (data){
                if(confirm('您确定要解禁该用户吗？')){
                    $stadiumUser.fn.status(data);
                }
            },
            sfInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/stadiumUser/sfInfo",
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
                        window.location.href = "${contextPath}/admin/stadiumUser/detail?id=" + id;
                    }
                });
            },
            edit: function (id){
                window.location.href = "${contextPath}/admin/stadiumUser/add?id=" + id;
            },
            status : function(id) {
                $.ajax({
                    "url": "${contextPath}/admin/stadiumUser/status",
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
                        $stadiumUser.v.dTable.ajax.reload();
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadiumUser.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadiumUser.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $stadiumUser.fn.init();
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

