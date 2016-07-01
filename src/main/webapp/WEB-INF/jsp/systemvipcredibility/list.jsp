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
        .from2{
            float: left;
            margin-right: 1px;
            margin-top: 10px;
        }
    </style>
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
        <h1 class="page-title">信誉度设置</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <div class="row">
                    <div class="col-md-12 m-b-15" style="width: 164px;">
                        <label>选择对应等级:</label>
                        <select id="systemVipId" class="select" >
                            <c:forEach items="${systemVipLevels}" var="v">
                                <option value="${v.systemVipId}">Lv ${v.level}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <div id="systemVipCredibility"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15" style="width: 164px;">
                        <select id="action" class="select">
                            <option value="1">散客组队成功</option>
                            <option value="2">场地预订</option>
                            <option value="3">约看宝贝</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <input type="text" id="credibility1" name="credibility" maxlength="6" value="" class="input-sm form-control validate[required]" style="width: auto" onkeyup="value=value.replace(/[^0-9]/g,'')">
                        <a onclick="$user.fn.systemVipExperienceSave();" class="btn btn-alt m-r-5" style="margin-top: 10px !important;">设定</a>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group" >
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
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
            },
            systemVipExperienceSave : function(){
                var isCheck = true;
                if($("#vipExperience").val()==""){
                    alert("经验不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    var credibility1 = $("#credibility1").val();
                    var systemVipId =  $('#systemVipId').val();
                    var action =  $('#action').val();
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/systemVipCredibility/systemVipCredibilitySave",
                        type : "POST",
                        data : {
                            "credibility1" : credibility1,
                            "systemVipId" : systemVipId,
                            "action" : action
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            $user.fn.vipCredibilityFrom(systemVipId);
                        }
                    });
                }
            },
            vipCredibilityFrom : function(data){
                console.log(data);
                $.ajax({
                    url:"${contextPath}/admin/systemVipCredibility/vipCredibilityFrom",
                    data:{
                        "systemVipId":data
                    },
                    success:function(data){
                        console.log(data);
                        $("#systemVipCredibility").empty();
                        for(var i= 0;i<data.length;i++){
                            var action = data[i].action;
                            var credibility = data[i].credibility;
                            if(action==1){
                                var a = "<div class='from2'>" +
                                            "<label>散客组队成功:</label>" +
                                            "<input style='width: auto' type='text' value=\""+credibility+ "点信誉度\" class='input-sm form-control validate[required]' disabled>" +
                                        "</div>";
                            }
                            if(action==2){
                                var a = "<div class='from2'>" +
                                            "<label>场地预订:</label>" +
                                            "<input style='width: auto' type='text' value=\""+credibility+ "点信誉度\" class='input-sm form-control validate[required]' disabled>" +
                                        "</div>";
                            }
                            if(action==3){
                                var a = "<div class='from2'>" +
                                            "<label>约看宝贝:</label>" +
                                            "<input style='width: auto' type='text' value=\""+credibility+ "点信誉度\" class='input-sm form-control validate[required]' disabled>" +
                                        "</div>";
                            }
                            $("#systemVipCredibility").append(a);

                        }
                    }
                });
            }
        }
    }
    $(function () {
        $user.fn.init();
        $user.fn.vipCredibilityFrom($("#systemVipId").val());
        $("#systemVipId").change(function(){
            var opt=$("#systemVipId").val();
            $user.fn.vipCredibilityFrom(opt);
        })
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

