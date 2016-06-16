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
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">状态</option>
                        <option value="0">未转账</option>
                        <option value="1">转账中</option>
                        <option value="2">已转账</option>
                        <option value="3">驳回</option>
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
                    <th>账号</th>
                    <th>城市</th>
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
    $stadiumUserWithdraw = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadiumUserWithdraw.fn.dataTableInit();
                $("#c_search").click(function () {
                    $stadiumUserWithdraw.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $stadiumUserWithdraw.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/stadiumUserWithdraw/list",
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
                        {"data": "stadiumUser.username","sDefaultContent" : ""},
                        {"data": "stadiumUser.city.city","sDefaultContent" : ""},
                        {"data": "stadiumUser.reserveMoney","sDefaultContent" : ""},
                        {"data": "withdrawMoney","sDefaultContent" : ""},
                        {"data": "status",
                            render:function(data){
                                if(data==0){
                                    return "未转账";
                                }else if(data==1){
                                    return "转账中";
                                }else if(data==2){
                                    return "已转账";
                                }else if(data==3){
                                    return "驳回";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='处理' class='btn btn-primary btn-circle add' onclick=\"$stadiumUserWithdraw.fn.handle(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var sta = full.status;
                                if(sta==1){
                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' onclick='$stadiumUserWithdraw.fn.status("+ data +")'> " +
                                            "<i>驳回</i></button>";
                                    return detail + "&nbsp;" + status;
                                }
                                return detail

                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.username = $("#username").val();
                    }
                });
            },
            handle: function (id){
                window.location.href = "${contextPath}/admin/1/handle?id=" + id;
            },
            status : function(id) {
                $.ajax({
                    "url": "${contextPath}/admin/stadiumUserWithdraw/status",
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
                        $stadiumUserWithdraw.v.dTable.ajax.reload();
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadiumUserWithdraw.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadiumUserWithdraw.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $stadiumUserWithdraw.fn.init();
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

