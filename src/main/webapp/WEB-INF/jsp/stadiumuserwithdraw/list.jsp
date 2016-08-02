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
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">提现管理</h1>
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <input type="hidden" id="details" name="details" value="${details}">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="username" name="username" placeholder="账号">
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">状态</option>
                        <option value="0">未处理</option>
                        <option value="1">已处理</option>
                        <option value="2">驳回</option>
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
                    <th>提现金额</th>
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
                                    return "未处理";
                                }else if(data==1){
                                    return "已处理";
                                }else if(data==2){
                                    return "已驳回";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var id = data;
                                var st = full.status;
                                var handle = 1;
                                if(st!=0){
                                    var detail = "<button title='处理' class='btn btn-primary btn-circle add' disabled " +
                                        "<a>处理</a></button>";

                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' disabled " +
                                            "<a>驳回</a></button>";
                                    return detail + "&nbsp;" + status;
                                }else{
                                    var detail = "<button title='处理' class='btn btn-primary btn-circle add' onclick=\"$stadiumUserWithdraw.fn.changeStatus(\'" + id + "\',\'" + handle + "\')\"> " +
                                            "处理</button>";

                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' onclick=\"$stadiumUserWithdraw.fn.changeStatus(\'" + id + "\')\"> " +
                                            "驳回</button>";
                                    return detail + "&nbsp;" + status;
                                }
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.username = $("#username").val();
                        aoData.status = $("#status").val();
                        aoData.details = $("#details").val();
                    }
                });
            },
            "changeStatus": function (id,handle) {
                var tempStatus;
                if(handle==1){
                    $('#showText').html('您确定要处理这笔金额吗？');
                    tempStatus = 1;
                }else{
                    $('#showText').html('您确定要驳回这笔金额吗？');
                    tempStatus = 2;
                }
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/stadiumUserWithdraw/status",
                        "data": {
                            "id": id,
                            "status":tempStatus
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $stadiumUserWithdraw.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
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

