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
    <input type="hidden" id="mian_module" value="互动管理">
    <input type="hidden" id="child_module" value="帖子列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <input type="hidden" id="details" name="details" value="${details}">
            <div class="row">
                <div class="col-md-2 form-group">
                    <label>发布人</label>
                    <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="...">
                </div>
                <div class="col-md-2 form-group">
                    <label>关键字搜索</label>
                    <input type="text" class="input-sm form-control" id="contentId" name="contentId" placeholder="...">
                </div>
                <div class="col-md-2 form-group">
                    <label>状态</label>
                    <select id="status" name="status" class="select">
                        <option value="">全部</option>
                        <option value="0">正常</option>
                        <option value="1">封禁</option>
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
                    <th>帖子内容</th>
                    <th>发帖人</th>
                    <th>回复人数</th>
                    <th>分享次数</th>
                    <th>举报次数</th>
                    <th>状态</th>
                    <th>发帖时间</th>
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
    $post = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $post.fn.dataTableInit();

                $("#c_search").click(function () {
                    $post.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $post.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/post/list",
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
                        {"data": "content"},
                        {"data": "user.nickName"},
                        {"data": "commentNum"},
                        {"data": "shareNum"},
                        {"data": "reportNum"},
                        {
                            "data": "status",
                            render: function (data) {
                                if (data == 0) {
                                    return "正常";
                                }
                                if (data == 1) {
                                    return "封禁";
                                }
                            },
                            "sDefaultContent": ""
                        },
                        {
                            "data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm")
                            }
                        },
                        {
                            "data": null,
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='$post.fn.detail(" + data.id + ")'> " +
                                        "<i class='fa fa-eye'></i></button>&nbsp;&nbsp;";
                                detail += "<button title='封禁' class='btn btn-primary btn-circle changeStatus' onclick='$post.fn.changeStatus(" + data.id + "," + data.status + ")'> " +
                                        "<i class='fa fa-check'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.nickName = $("#nickName").val();
                        aoData.content = $("#contentId").val();
                        aoData.status = $("#status").val();
                        aoData.details = $("#details").val();
                    }
                });
            },
            /*rowCallback: function (row, data) {
             var items = $post.v.list;
             $('td', row).last().find(".add").attr("href", 'admin/post/detail?id=' + data.id);
             },*/
            "detail": function (id) {
                window.location.href = "${contextPath}/admin/post/detail?postId=" + id;
            },
            "changeStatus": function (id,status) {

                if (status == 0) {
                    $('#showText').html('确定禁用该账号？');
                } else {
                    $('#showText').html('确定解封该账号？');
                }

                $("#delete").modal("show");
                $('#confirm').click(function () {

                    var tempStatus = 0;
                    if (status == 0) {
                        tempStatus = 1;
                    }

                    $leoman.ajax("${contextPath}/admin/post/banned", {
                        "postId": id,
                        "status": tempStatus
                    }, function (result) {
                        if (result == 1) {
                            $common.fn.notify("操作成功", "success");
                            $("#delete").modal("hide");
                            $post.v.dTable.ajax.reload(null, false);
                        } else {
                            $common.fn.notify("操作失败", "error");
                        }
                    });
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $reserve.v.dTable.ajax.reload(null, false);
                    } else {
                        $reserve.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $post.fn.init();
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

