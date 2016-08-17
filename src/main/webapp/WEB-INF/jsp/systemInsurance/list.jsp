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
    <input type="hidden" id="mian_module" value="APP相关设置">
    <input type="hidden" id="child_module" value="保险列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area">
            <button id="publish" class="btn btn-alt m-r-5" onclick="systemInsurance.fn.add()">增加保险</button>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th style="width: 20px;"><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>保险名称</th>
                    <th>保险单价</th>
                    <th>购买次数</th>
                    <th>总金额</th>
                    <th>事故次数</th>
                    <th>状态</th>
                    <th>事故保金</th>
                    <th>投保公司</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="showText" >确定禁用该账号？</h4>
                    </div>
                    <div class="modal-body">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" id="confirm" class="btn btn-primary">确定
                        </button>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    systemInsurance = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                systemInsurance.fn.dataTableInit();
            },
            dataTableInit: function () {
                systemInsurance.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/systemInsurance/list",
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
                        {"data": "name"},
                        {"data": "price"},
                        {"data": "payNum"},
                        {"data": "sumPrice"},
                        {"data": "accidentNum"},
                        {
                            "data": "status",
                            render: function (data) {
                                if (data == 0) {
                                    return "正常";
                                }
                                if (data == 1) {
                                    return "失效";
                                }
                            },
                            "sDefaultContent": ""
                        },
                        {"data": "bj",},
                        {"data": "company",},
                        {
                            "data": null,
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='systemInsurance.fn.detail(" + data.id + ")'> " +
                                        "<i class='fa fa-eye'></i></button>&nbsp;&nbsp;";
                                detail += "<button title='失效' class='btn btn-primary btn-circle changeStatus' onclick='systemInsurance.fn.changeStatus(" + data.id + "," + data.status + ")'> " +
                                        "<i class='fa fa-check'></i></button>";
                                return detail;
                            }
                        }
                    ],
                });
            },
            "detail": function (id) {
                window.location.href = "${contextPath}/admin/systemInsurance/detail?systemInsuranceId=" + id;
            },
            "add": function () {
                window.location.href = "${contextPath}/admin/systemInsurance/add";
            },
            "changeStatus": function (id,status) {

                if (status == 0) {
                    $('#showText').html('确定禁用该账号？');
                } else {
                    $('#showText').html('确定解封该账号？');
                }


                $("#delete").modal("show");
                $("#confirm").unbind();
                $('#confirm').click(function () {

                    var tempStatus = 0;
                    if (status == 0) {
                        tempStatus = 1;
                    }
                    $leoman.ajax("${contextPath}/admin/systemInsurance/banned", {
                        "insuranceId": id,
                        "status": tempStatus
                    }, function (result) {
                        if (result == 1) {
                            $common.fn.notify("操作成功", "success");
                            $("#delete").modal("hide");
                            systemInsurance.v.dTable.ajax.reload(null, false);
                        } else {
                            $common.fn.notify("操作失败", "error");
                        }
                    });
                });
            },
        }
    }
    $(function () {
        systemInsurance.fn.init();
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

