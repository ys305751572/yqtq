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
                    <input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="手机号" onblur="$userList.fn.check()"/>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="昵称" >
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">用户状态</option>
                        <option value="0">正常</option>
                        <option value="1">冻结</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="vipLevel" name="vipLevel" class="select">
                        <option value="">会员状态</option>
                        <option value="0">非会员</option>
                        <option value="1">会员</option>
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
                    <th>手机号</th>
                    <th>注册时间</th>
                    <th>信誉度</th>
                    <th>会员状态</th>
                    <th>用户状态</th>
                    <th>昵称</th>
                    <th>总消费</th>
                    <th>确定</th>
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
    $userList = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $userList.fn.dataTableInit();
                $("#c_search").click(function () {
                    $userList.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $userList.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/user/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "mobile"},
                        {"data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                            }
                        },
                        {"data": "credibility"},
                        {"data": "vipLevel",
                            render:function(data){
                                if(data==0){
                                    return "非会员";
                                }else{
                                    return "Lv" + data;
                                }
                            }
                        },
                        {"data": "status",
                            render:function(data){
                                if(data==0){
                                    return "—";
                                }else{
                                    return "禁用";
                                }
                            }
                        },
                        {"data": "nickName"},
                        {"data": "userId"},
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var id = full.id;
                                var nickName = full.nickName;
                                var sure = "<button title='确定' class='btn btn-primary btn-circle add' onclick=\"$userList.fn.sure('"+id+ "','"+nickName+ "')\">" +
                                        "确定</button>";
                                return sure;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.mobile = $("#mobile").val();
                        aoData.nickName = $("#nickName").val();
                        aoData.status = $("#status").val();
                        aoData.vipLevel = $("#vipLevel").val();
                    }
                });
            },
            rowCallback: function (row, data) {
                var items = $userList.v.list;
                $('td', row).last().find(".add").attr("href", 'admin/user/detail?id=' + data.id);
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $userList.v.dTable.ajax.reload(null, false);
                    } else {
                        $userList.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            },
            sure : function(id,nickName){
                if(window.opener){
                    window.opener.document.all.toUserId.value = id;
                    window.opener.document.all.nickName.value = nickName;
                    window.close();
                }
            }
        }
    }
    $(function () {
        $userList.fn.init();
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

