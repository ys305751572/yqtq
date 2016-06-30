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
        <h1 class="page-title">平台赛事</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>系统标题：</label>
                        <input type="text" id="systemTitle" name="systemTitle" value="" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>接收用户：</label>
                        <select id="userType1" name="userType1" class="select">
                            <option value="0">全部</option>
                            <option value="1">个人</option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15" id="uid" style="display: none">
                        <label>用户：</label>
                        <input type="text" id="nickName" name="nickName" value="" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        <input type="hidden" id="toUserId" name="toUserId" value="" disabled>
                        <a onclick="_systemMessage.fn.selectValue()" class="btn btn-alt m-r-5" style="margin-top: 10px">选择</a>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>消息内容: </label>
                        <div class="wysiwye-editor" id="detail" name="detail"></div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="_systemMessage.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    _systemMessage = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {

            },
            save : function () {
                var isCheck = true;
                if($("#title").val()==""){
                    alert("系统标题不能为空!");
                    isCheck=false;
                }
                if($("#toUserId").val()==1 && $("#userId").val()==""){
                    alert("用户ID不能为空!");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    alert("消息内容不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    var userType = $("#userType1").val();
                    var title = $("#systemTitle").val();
                    var toUserId = $("#toUserId").val();
                    var code =  $('.wysiwye-editor').code();
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/systemMessage/save",
                        type : "POST",
                        data : {
                            "detail" : code,
                            "title" : title,
                            "toUserId" : toUserId,
                            "userType" : userType
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/systemMessage/index";
                        }
                    });
                }
            },
            selectValue : function(){
                var iWidth=1000; //弹出窗口的宽度;
                var iHeight=600; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                window.open("${contextPath}/admin/systemMessage/userSelect","球场列表","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
            }
        }
    }
    $(function () {
        _systemMessage.fn.init();
        $("#userType1").change(function(){
            if($("#userType1").val()==1){
                $("#uid").css('display','block');
            }else {
                $("#uid").css('display','none');
            }
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
        forceParse: 0,
        format: 'yyyy-mm-dd hh:mm'
    });
</script>
</body>
</html>

