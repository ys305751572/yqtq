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
    <input type="hidden" id="mian_module" value="约场地管理">
    <input type="hidden" id="child_module" value="约场地列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">约场地详情</h1>
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="">
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <label>logo:</label>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <img id="avater" name="avater" src="${reserve.stadium.avaterAbsolutePath}" alt="" style="height: 100px;width: 100px;">
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-6 m-b-15">
                        <label>球场名称:</label>
                        <a href="${contextPath}/admin/stadium/detail?id=${reserve.stadium.id}&&isEd=1" class="btn btn-block btn-alt" style="font-size: 12px;height: 30px;text-align:left;text-indent:0.5em;background-color:rgba(238, 238, 238, 0.09) !important;" >${reserve.stadium.name}</a>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>城市:</label>
                        <input type="text" id="cityId" value="${reserve.stadium.city.city}" name="cityId" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>预约时长:</label>
                        <input type="text" id="slogan" name="slogan" value="${reserve.time}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>预订人:</label>
                        <input type="text" id="leaderUserId" name="leaderUserId" value="${reserve.user.nickName}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>预订类型:</label>
                        <input type="text" id="type" name="type" value="<c:if test='${reserve.reserveType eq 0}'>散客</c:if><c:if test='${reserve.reserveType eq 1}'>其他</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>预定状态:</label>
                        <input type="text" id="status" name="status" value="<c:if test='${reserve.status eq 0}'>正在组队</c:if><c:if test='${reserve.status eq 1}'>组队成功</c:if><c:if test='${reserve.status eq 2}'>组队失败</c:if><c:if test='${reserve.status eq 3}'>比赛结束</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>使用开始时间:</label>
                        <input type="text" id="startDate" name="startDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${reserve.startDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15" >
                        <label>预约时间:</label>
                        <input type="text" id="createDate" name="createDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${reserve.createDate}'></date:date>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <c:if test="${reserve.systemInsurance ne null}">
                        <input type="hidden" id="insurance_id" value="${insurance.id}">
                        <div class="col-md-6 m-b-15" >
                            <label>购买保险人数:</label>
                            <input type="text" id="insuranceNum" name="insuranceNum" value="${insurance.insuranceNum eq null ? 0 : insurance.insuranceNum}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </div>
                        <div class="col-md-6 m-b-15" id="display">
                            <label>事故数:</label>
                            <input type="text" id="num" name="num" onblur="$stadiumBooking_detail.fn.check()" value="${insurance.num eq null ? 0 : insurance.num}" class="input-sm form-control validate[required]" placeholder="..." >
                            <a onclick="$stadiumBooking_detail.fn.accident();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">确定</a>
                        </div>
                    </c:if>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $stadiumBooking_detail = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                var insuranceNum = $("#insuranceNum").val();
                if(insuranceNum==0){
                    console.log(insuranceNum);
                    $("#display").css('display','none');
                }else{
                    $("#display").css('display','block');

                }
            },
            "detail" : function(userId) {
                window.location.href = "${contextPath}/admin/user/detail?userId=" + userId;
            },
            accident : function() {
                var id = $("#insurance_id").val();
                var num = $("#num").val();
                $.ajax({
                    url : "${contextPath}/admin/stadiumBooking/accident",
                    type : "POST",
                    data : {
                        "id" : id,
                        "num" : num
                    },
                    success : function(result) {
                        if(result!=null) {
                            $("#num").val(result.num);
                        }
                    }
                })
            },
            check : function(){
                if(!$("#display").validationEngine("validate")) {
                    return;
                }
                var insuranceNum = $("#insuranceNum").val();
                var num = $("#num").val();
                if(num>insuranceNum){
                    $leoman.notify('事故人数不能大于保险购买人数', "error");
                    $("#num").val("");
                }

            }
        }
    }
    $(function () {
        $stadiumBooking_detail.fn.init();
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

