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
                    <label>球场名称</label>
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="...">
                </div>
                <div class="col-md-2 form-group">
                    <label>城市</label>
                    <select id="cityId" name="cityId" class="select">
                        <option value="">全部</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <label>球场类型</label>
                    <select id="type" name="type" class="select">
                        <option value="">全部</option>
                        <option value="0">私有</option>
                        <option value="1">公有</option>
                    </select>
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
                        <a data-toggle="modal" href="${contextPath}/admin/stadium/add" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
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
                    <th>城市</th>
                    <th>球场名称</th>
                    <th>球场类型</th>
                    <th>被预定总数</th>
                    <th>当前预定数</th>
                    <th>场地数</th>
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
    $stadium = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $stadium.fn.dataTableInit();
                $("#c_search").click(function () {
                    $stadium.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $stadium.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/stadium/list",
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
                        {"data": "cityId"},
                        {"data": "name"},
                        {
                            "data": "type",
                            "render":function(data){
                                if(data==0){
                                    return "私人球场";
                                }else {
                                    return "公共球场";
                                }
                            }
                        },
                        {"data": "stadiumNum","sDefaultContent" : ""},
                        {"data": "availableStadiumNum","sDefaultContent" : ""},
                        {"data": "siteNum","sDefaultContent" : ""},
                        {
                            "data": "isStatus",
                            "render": function (data) {
                                if(data==0){
                                    return "无可预订场次";
                                }else {
                                    return "可预订";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle add' onclick=\"$stadium.fn.sfStadiumInfo(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                return detail;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.cityId = $("#cityId").val();
                        aoData.type = $("#type").val();
                    }
                });
            },
            sfStadiumInfo: function (id) {
                $.ajax({
                    "url": "${contextPath}/admin/stadium/sfStadiumInfo",
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
                        window.location.href = "${contextPath}/admin/stadium/detail?id=" + id;
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $stadium.v.dTable.ajax.reload(null, false);
                    } else {
                        $stadium.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $stadium.fn.init();
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

