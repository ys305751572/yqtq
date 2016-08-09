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
    <input type="hidden" value="APP相关设置">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>反馈人</th>
                    <th>反馈时间</th>
                    <th>联系方式</th>
                    <th>反馈内容</th>
                    <th>反馈详情</th>
                </tr>
                </thead>
            </table>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<%@ include file="detail.jsp" %>
<script>
    $userfeedback = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $userfeedback.fn.dataTableInit();

                $("#c_search").click(function () {
                    $userfeedback.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $userfeedback.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/userFeedback/list",
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
                        {"data": "user.nickName","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            render : function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "user.mobile","sDefaultContent" : ""},
                        {
                            "data": "content",
                            render: function (data) {
                                if (null != data && data != '') {
                                    return data.length > 30 ? (data.substring(0, 30) + '...') : data;
                                } else {
                                    return "";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "content",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看详情' class='btn btn-primary btn-circle detail' onclick=\"$userfeedback.fn.detail(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                return detail
                            }
                        }

                    ],
                    "fnServerParams": function (aoData) {

                    }
                });
            },
            detail : function(data){
                $('#detail').html(data);
                $("#form_detail").modal("show");
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $userfeedback.v.dTable.ajax.reload(null, false);
                    } else {
                        $userfeedback.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $userfeedback.fn.init();
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

