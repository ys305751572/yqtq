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
        <div class="block-area" id="alternative-buttons">
            <div>
                <a data-toggle="modal" onclick="$module.fn.add()" title="新增" class="btn btn-alt m-r-5">新增</a>
            </div>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>父模块</th>
                    <th>模块名称</th>
                    <th>URL</th>
                    <th>模块描述</th>
                    <th>操作员账号</th>
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
    $module = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $module.fn.dataTableInit();

                $("#c_search").click(function () {
                    $module.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $module.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/module/list",
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
                        {"data": "parent.name","sDefaultContent" : ""},
                        {"data": "name","sDefaultContent" : ""},
                        {"data": "url","sDefaultContent" : ""},
                        {"data": "description","sDefaultContent" : ""},
                        {"data": "admin.username","sDefaultContent" : ""},
                        {
                            "data": "id",
                            "render": function (data) {
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle edit' onclick=\"$module.fn.edit(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                return edit;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                    }
                });
            },
            edit: function (id) {
                window.location.href = "${contextPath}/admin/module/add?id=" + id ;
            },
            add: function () {
                var st = $('input[type="checkbox"]:checked');
                if(st.length==1){
                    st.each(function(){
                        var id = $(this).val();
                        var isChild = "1";
                        $.ajax({
                            url : "${contextPath}/admin/module/addChild",
                            type : "POST",
                            data : {
                                "id" : id
                            },
                            success : function(result) {
                                if(result.status) {
                                    window.location.href = "${contextPath}/admin/module/add?id="+ id +"&isChild=" + isChild;
                                }else {
                                    $leoman.notify('请选择一个主模块', "error");
                                }
                            }
                        })
                    })
                }else if(st.length==0){
                    window.location.href = "${contextPath}/admin/module/add";
                }else{
                    st.each(function(){
                        var a = $(this).val()
                        console.log(a);
                    })
                    $leoman.notify('只能选择一个模块', "error");
                }
            },

            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $module.v.dTable.ajax.reload(null, false);
                    } else {
                        $module.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $module.fn.init();
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

