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
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">用户信息</h1>
        <div class="block-area">
            <div class="col-md-2">
                <div class="tile">
                    <h2 class="tile-title">基本信息</h2>
                    <div class="p-10" style="height:520px">
                        <div class="form-group">
                            <label>姓 名:</label>
                            ${aoluser.name}
                        </div>
                        <div class="form-group">
                            <label>性 别:</label>
                            ${aoluser.sex}
                        </div>
                        <div class="form-group">
                            <label>手 机:</label>
                            ${aoluser.mobile}
                        </div>
                        <div class="form-group">
                            <label>身 高:</label>
                            ${aoluser.height}
                        </div>
                        <div class="form-group">
                            <label>体 重:</label>
                            ${aoluser.weight}
                        </div>
                        <div class="form-group">
                            <label>邮箱:</label>
                            ${aoluser.email}
                        </div>
                        <div class="form-group">
                            <label>图标类型</label>
                            <select id="reportype" name="reportype" id="sex" name="sex" class="select" onchange="$user.fn.selectdevice()">
                                <c:forEach var="device" items="${devicelist}" varStatus="status">
                                <option value="${device.device_id}"  <c:if test="${device.device_id==selectdeviceid}">selected="selected"</c:if>>
                                    <c:if test="${device.deviceType=='1'}">血压 </c:if>
                                    <c:if test="${device.deviceType=='2'}">血糖 </c:if>
                                    <c:if test="${device.deviceType=='3'}">体温 </c:if>
                                    ---${device.deviceSerial}
                                    </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-10">
                <div class="tile">
                    <h2 class="tile-title">血压折线图</h2>
                    <div class="p-10">
                        <div id="chartdiv" class="main-chart" style="height:500px"></div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $user.fn.initCharts();
                $user.fn.selectdevice();
            },
            initCharts: function () {
                $user.v.chart =   new Highcharts.Chart({
                    chart: {
                        type: 'line',
                        renderTo:'chartdiv' ,
                        backgroundColor: 'rgba(0,0,0,0)'
                    },
                    colors: ['#E066FF', '#87CEFF', '#20B2AA', '#1C86EE'],
                    title: {
                        text: '',
                        x: -20 //center
                    },
                    subtitle: {
                        text: '',
                        x: -20
                    },
                    xAxis: {
                        categories: [
                        ],
                        labels: {
                            enabled:false,
                            style: {
                                color: '#999',
                                font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        max: 300,
                        title: {
                            text: ''
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    series:[
                        {
                            name: '高血压',
                            data: [0]},
                        {
                            name: '低血压',
                            data: [0]},
                        {
                            name: '心率',
                            data: [0]}
                    ]
                });  // Highcharts.Chart end
            },
            selectdevice: function () {
                var formObj = "deviceid=" + $("#reportype").val() + "&userid=${aoluser.userId}";
                var type = $("#reportype").find("option:selected").text();
                var title;
                if (type.indexOf('血压') > 0) {
                    title = {
                        text: "血压折线图",
                        x: -20 // center
                    };
                    formObj = formObj + "&measureType=1";
                }
                if (type.indexOf('血糖') > 0) {
                    title = {
                        text: "血糖折线图",
                        x: -20 // center
                    };
                    formObj = formObj + "&measureType=2";
                }
                if (type.indexOf('体温') > 0) {
                    title = {
                        text: "体温折线图",
                        x: -20 // center
                    };
                    formObj = formObj + "&measureType=3";
                }

                $user.v.chart.setTitle(title);
                $.ajax({
                    url: '${contextPath}/admin/measure/getReport',
                    type: 'post',
                    cache: false,
                    data: formObj,
                    success: function (response) {
                        if (response == "未找到记录") {
                            $common.fn.notify(result.msg);
                            document.getElementById('strxml').value = "";
                        } else {
                            //清除数据
                            $user.fn.daleteSeries();
                            var highvaluelist = response;
                            console.log("highvaluelist.length:" + highvaluelist.length);
                            // 获取json数据，转换为需要的数据格式
                            for (var i = 0; i < highvaluelist.length; i++) {
                                // 抽取json中的Y元素变成数组
                                var datavalue = new Array();
                                var time = new Array();
                                for (var j = 0; j < highvaluelist[i].highvalue.length; j++) {
                                    datavalue[j] = Number(highvaluelist[i].highvalue[j].y);
                                    time.push(highvaluelist[i].highvalue[j].x);
                                }
                                $user.v.chart.xAxis[0].setCategories(time);
                                $user.v.chart.addSeries({
                                    // 设置污染项
                                    name: highvaluelist[i].typename,
                                    // 设置监测的数组
                                    data: datavalue,
                                    visible: true
                                }, true);
                                $user.v.chart.redraw();

                            }
                            //document.getElementById('gdiframe').contentWindow.location.reload(true);
                        }
                    }
                });
            },
            daleteSeries: function () {
                var index = $user.v.chart.series.length;
                for (var i = 0; i < index; i++) {
                    $user.v.chart.series[0].remove();
                }
            }
        }
    }
    $(function () {
        $user.fn.init();
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

