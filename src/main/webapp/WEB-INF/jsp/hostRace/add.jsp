<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h1 class="page-title">场地编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${hostRace.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>赛事名称：</label>
                        <input type="text" id="name" name="name" value="${hostRace.name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>球场封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <c:if test="${hostRace.avater ne null}">
                                    <img src="${hostRace.avater}">
                                </c:if>
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile" name="imageFile" type="file"/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <c:if test="${hostRace.id eq null}">
                        <div class="col-md-6 m-b-15">
                            <label>球场名称：</label>
                            <input type="text" id="stadium" name="stadium" value="" class="input-sm form-control validate[required]" placeholder="...">
                        </div>
                        <div class="col-md-6 m-b-15">
                            <label>比赛地址：</label>
                            <input type="text" id="address" name="address" value="" class="input-sm form-control validate[required]" placeholder="...">
                        </div>
                    </c:if>
                    <div class="col-md-6 m-b-15">
                        <label>比赛时间：</label>
                        <input type="text" id="startDate" name="startDate" value="<date:date format='yyyy年MM月dd日 HH:mm' value='${hostRace.startDate}'></date:date>" class="input-sm form_datetime form-control validate[required]" placeholder="...">
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述</label>
                        <div class="wysiwye-editor" id="detail" name="detail">${hostRace.description}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$user.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                <%--var sDate = ${hostRace.startDate};--%>
                <%--var startDate = new Date(sDate).format("yyyy-MM-dd hh:mm");--%>
                <%--$("#startDate").val(startDate);--%>
            },
            save : function () {
                var isCheck = true;
                if($("#name").val()==""){
                    alert("赛事名称不能为空!");
                    isCheck=false;
                }
                if($("#stadium").val()==""){
                    alert("球场名称不能为空!");
                    isCheck=false;
                }
                if($("#address").val()==""){
                    alert("比赛地址不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    var code =  $('.wysiwye-editor').code();
                    var startDate = this.transdate($("#startDate").val());
                    $("#startDate").val(startDate);
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/hostRace/save",
                        type : "POST",
                        data : {
                            "detail" : code,
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/hostRace/index";
                        }
                    });
                }

            },
            transdate :function(endTime){
                var date=new Date();
                date.setFullYear(endTime.substring(0,4));
                date.setMonth(endTime.substring(5,7)-1);
                date.setDate(endTime.substring(8,10));
                date.setHours(endTime.substring(11,13));
                date.setMinutes(endTime.substring(14,16));
                date.setSeconds(endTime.substring(17,19));
                return Date.parse(date);
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
        forceParse: 0,
        format: 'yyyy-mm-dd hh:mm'
    });
</script>
</body>
</html>

