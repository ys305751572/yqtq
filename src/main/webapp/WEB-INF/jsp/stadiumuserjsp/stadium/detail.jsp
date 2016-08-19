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
    <style>
        .label{
            font-size: 15px;
            line-height: 30px;
            padding-left: 20px;
        }
        .time{
            line-height: 60px;
            height: 40px;
            font-size: 12px;
        }
        .base{
            border: 2px solid #b3b8b8;
            width: 65px;
            height: 40px;
            background-color: #1ab65e;
        }
        .asd{
            background-color: #ff4d4f;!important;
        }
    </style>
</head>
<body id="skin-cloth">
<%@ include file="../inc/new/header.jsp" %>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <input type="hidden" value="球场管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">场地列表</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-6 m-b-15">
                    <label>图片:</label>
                    <img src="${stadium.avater}" alt="">
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>球场名称:</label>
                    <input type="text" id="name" name="name" value="${stadium.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>

                </div>
                <div class="col-md-6 m-b-15">
                    <label>省:</label>
                    <input type="text" id="province" name="province" value="${stadium.province.province}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>市:</label>
                    <input type="text" id="city" name="city" value="${stadium.city.city}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>区:</label>
                    <input type="text" id="area" name="area" value="${stadium.area.area}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>详细地址:</label>
                    <input type="text" id="address" name="address" value="${stadium.address}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <c:if test="${stadium.type eq 0}">
                    <div class="col-md-6 m-b-15">
                        <label>球场主:</label>
                        <%--<a class="btn btn-block btn-alt" style="font-size: 12px;height: 30px;text-align:left;text-indent:0.5em;background-color:rgba(238, 238, 238, 0.09) !important;" href="${contextPath}/admin/stadiumUser/detail?id=${stadium.stadiumUserId}">${stadiumUser.nickName}</a>--%>
                        <input type="text" id="nickName" name="nickName" value="${stadiumUser.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                </c:if>
                <hr class="whiter m-t-20"/>
                <div>
                    <div class="col-md-6 m-b-15">
                        <label>场地类型:</label>
                        <input type="text" id="siteType" name="siteType" value="<c:if test='${stadium.siteType eq 0}'>室内</c:if><c:if test='${stadium.siteType eq 1}'>室外</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>草皮类型:</label>
                        <input type="text" id="sodType" name="sodType" value="${stadium.sodType}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>灯光:</label>
                        <input type="text" id="light" name="light" value="${stadium.light}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>停车场:</label>
                        <input type="text" id="park" name="park" value="<c:if test='${stadium.park eq 0}'>无</c:if><c:if test='${stadium.park eq 1}'>免费</c:if><c:if test='${stadium.park eq 2}'>收费</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>赠送:</label>
                        <input type="text" id="giving" name="giving" value="${stadium.giving}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15">
                    <label>球场场次:</label>
                    <div class="col-md-12 m-b-15">
                        <c:forEach items="${timeList}" var="v">
                            <a data-toggle="modal" title="" onclick="$team.fn.siteList(${v.year},${v.month},${v.day},${stadium.id})" class="btn btn-alt m-r-5">${v.month}月${v.day}号</a>
                        </c:forEach>
                    </div>
                    <div id="table" class="col-md-12 m-b-15">

                    </div>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-6 m-b-15">
                    <label>球场简介:</label>
                    <div class="wysiwye-editor" id="description" name="description">${stadium.description}</div>
                </div>
                <hr class="whiter m-t-20"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                </div>
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $team = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {

            },
            siteList: function(year,month,day,stadiumId){
                $team.fn.table();
                var time = year+"-"+month+"-"+day;
//                console.log(time);
//                console.log(stadiumId);
                $.ajax({
                    url : "${contextPath}/stadium/stadium/siteManageList",
                    type : "POST",
                    data : {
                        "time" : time,
                        "stadiumId" : stadiumId
                    },
                    success : function(seDate) {
                        $("#sad label").each(function() {
                            for(var i=0;i<seDate.length;i++){
                                if($(this).html()==seDate[i].code){
                                    var index = 0;
                                    $(this).parent().find("div").each(function(){
                                        var start = $(this).find("input[name=start]").val();
                                        if(start==seDate[i].start){
                                            index = 1;
                                        }

                                        var end = $(this).find("input[name=end]").val();
                                        if(end==seDate[i].end){
                                            $(this).css("background-color","#faffff");
                                            index = 0;
                                        }

                                        if(index!=0){
                                            $(this).css("background-color","#faffff");
                                        }
                                    })
                                }
                            }
                        })
                    }
                });

                $.ajax({
                    url : "${contextPath}/stadium/stadium/siteTimeList",
                    type : "POST",
                    data : {
                        "time" : time,
                        "stadiumId" : stadiumId
                    },
                    success : function(seDate) {
                        $("#sad label").each(function() {
                            for(var i=0;i<seDate.length;i++){
                                if($(this).html()==seDate[i].code){
                                    var index = 0;
                                    $(this).parent().find("div").each(function(){
                                        var start = $(this).find("input[name=start]").val();
                                        if(start==seDate[i].start){
                                            index = 1;
                                        }
                                        var end = $(this).find("input[name=end]").val();
                                        if(end==seDate[i].end){
                                            console.log(start+" ~~ "+end);
                                            $(this).css("background-color","#ff4d4f");
                                            index = 0;
                                        }

                                        if(index!=0){
                                            $(this).css("background-color","#ff4d4f");
                                        }
                                    })
                                }
                            }
                        });
                    }
                });
            },
            table : function(){
                $("#table").empty();
                var html = "";
                html += " <div style='float:left'>						";
                html += "    <label class='label'>时间</label>          ";
                html += "    <p class='time'>08:00~10:00</p>            ";
                html += "    <p class='time'>10:00~12:00</p>            ";
                html += "    <p class='time'>12:00~14:00</p>            ";
                html += "    <p class='time'>14:00~16:00</p>            ";
                html += "    <p class='time'>16:00~18:00</p>            ";
                html += "    <p class='time'>18:00~20:00</p>            ";
                html += "    <p class='time'>20:00~22:00</p>            ";
                html += "    <p class='time'>22:00~00:00</p>            ";
                html += "</div>                                         ";
                html += "<c:forEach items='${stadiumSubList}' var='v'>  ";
                html += "<div style='float:left;padding-left:10px' id='sad'>     ";
                html += "    <label class='label'>${v.code}</label>";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='08:00'><input type='hidden' name='end' value='10:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='10:00'><input type='hidden' name='end' value='12:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='12:00'><input type='hidden' name='end' value='14:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='14:00'><input type='hidden' name='end' value='16:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='16:00'><input type='hidden' name='end' value='18:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='18:00'><input type='hidden' name='end' value='20:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='20:00'><input type='hidden' name='end' value='22:00'></div></p>       ";
                html += "    <p><div class='base' disabled><input type='hidden' name='start' value='22:00'><input type='hidden' name='end' value='00:00'></div></p>       ";
                html += "</div>                                         ";
                html += "</c:forEach>                                   ";
                $("#table").append(html);
            }
        }
    }
    $(function () {
        $team.fn.init();
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

