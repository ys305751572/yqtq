<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>map坐标</title>
    <script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=2d5f22b503004b7c7a48e0cc6cc82cd5"></script>
    <style>
        #iMap{height:500px;width:600px;float:left;}
        .info{float:left;margin:0 0 0 10px;}
        label{width:80px;float:left;}
        .detail{padding:10px;border:1px solid #aaccaa}
    </style>
</head>
<body onLoad="mapInit()">

<div id="iMap"></div>
<div class="info">
    <h1>坐标拾取工具（GCJ-02坐标）</h1>
    <p>说明:</p>
    <p>1、鼠标滚轮可以缩放地图，拖动地图。</p>
    <p>2、点击地图，即可获得GCJ-02的经纬度坐标，即火星坐标。</p>
    </br>
    <div class="detail">
        <p><span id="lnglat">&nbsp;</span></p>
        <p><span id="iAddress">&nbsp;</span></p>
    </div>
</div>
<!-- tongji begin-->
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Faeff88f19045b513af7681b011cea3bd' type='text/javascript'%3E%3C/script%3E"));
</script>
<!-- tongji end -->
</body>
<script language="javascript">
    var mapObj;
    var lnglatXY;
    //初始化地图
    function mapInit(){
        var opt = {
            level: 10 //设置地图缩放级别
        }
        mapObj = new AMap.Map("iMap", opt);

        AMap.event.addListener(mapObj,'click',getLnglat); //点击事件
    }
    function geocoder() {
        var MGeocoder;
        //加载地理编码插件
        mapObj.plugin(["AMap.Geocoder"], function() {
            MGeocoder = new AMap.Geocoder({
                radius: 1000,
                extensions: "all"
            });
            //返回地理编码结果
            AMap.event.addListener(MGeocoder, "complete", geocoder_CallBack);
            //逆地理编码
            MGeocoder.getAddress(lnglatXY);
        });
        //加点
        var marker = new AMap.Marker({
            map:mapObj,
            icon: new AMap.Icon({
                image: "http://api.amap.com/Public/images/js/mark.png",
                size:new AMap.Size(58,30),
                imageOffset: new AMap.Pixel(-32, -0)
            }),
            position: lnglatXY,
            offset: new AMap.Pixel(-5,-30)
        });
        // mapObj.setFitView();
    }
    //回调函数
    function geocoder_CallBack(data) {
        var address;
        //返回地址描述
        address = data.regeocode.formattedAddress;
        //返回结果拼接输出
        document.getElementById("iAddress").innerHTML = address;
    }
    //鼠标点击，获取经纬度坐标
    function getLnglat(e){
        mapObj.clearMap();
        var x = e.lnglat.getLng();
        var y = e.lnglat.getLat();
        document.getElementById("lnglat").innerHTML = x + "," + y;

        lnglatXY = new AMap.LngLat(x,y);
        geocoder();
    }
</script>
</html>
