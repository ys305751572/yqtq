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
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>比赛名称</th>
                    <th>比赛地区</th>
                    <th>状态</th>
                    <th>赛制</th>
                    <th>参赛队数</th>
                    <th>比赛时间</th>
                    <th>球场地址</th>
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
    $hostRaceList = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $hostRaceList.fn.dataTableInit();

                $("#c_search").click(function () {
                    $hostRaceList.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $hostRaceList.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/hostRace/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "name"},
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
                            }
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
                            }
                        },
                        {"data": "hrNum"},
                        {
                            "data": "startDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            }
                        },
                        {"data": "stadium.address","sDefaultContent" : ""},
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var id = full.id;
                                var name = full.name;
                                var sure = "<button title='确定' class='btn btn-primary btn-circle add' onclick=\"$hostRaceList.fn.sure('"+id+ "','"+name+ "')\">" +
                                        "确定</button>";
                                return sure;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {

                    }
                });
            },
            sure : function(id,name){
                if(window.opener){
                    window.opener.document.all.toId.value = id;
                    window.opener.document.all.object.value = name;
                    window.close();
                }
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $hostRaceList.v.dTable.ajax.reload(null, false);
                    } else {
                        $hostRaceList.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $hostRaceList.fn.init();
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

