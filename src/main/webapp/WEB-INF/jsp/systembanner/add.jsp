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
    <input type="hidden" value="APP相关设置">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">首页banner</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${watchingRace.id}">
                <div class="row">
                    <div class="col-md-12 m-b-15">
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="">
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile" name="imageFile" type="file" value=""/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>跳转类型：</label>
                        <select id="type" name="type" class="select" >
                            <option value="0">活动</option>
                            <option value="1">资讯</option>
                            <option value="2">球赛</option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>跳转对象：</label>
                        <input type="text" id="object" name="object" value=""  class="input-sm form-control validate[required]" placeholder="..."  disabled>
                        <input type="hidden" id="toId" name="toId" value="" disabled>
                        <a onclick="$user.fn.selectValue()" class="btn btn-alt m-r-5">选择</a>
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
            },
            save : function () {
                var isCheck = true;
                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    alert("图片不能为空!");
                    isCheck=false;
                }
                if($("#toId").val() == ""){
                    alert("跳转对象不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    var toId =  $('#toId').val();
                    $("#formId").ajaxSubmit({
                        url : "${contextPath}/admin/systemBanner/save",
                        type : "POST",
                        data : {
                            "toId" : toId
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/systemBanner/index";
                        }
                    });
                }
            },
            selectValue : function(){
                var type = $("#type").val();
                var iWidth=1000; //弹出窗口的宽度;
                var iHeight=600; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                window.open("${contextPath}/admin/systemBanner/select?type="+type,"","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
            }
        }
    }
    $(function () {
        $user.fn.init();
        $("#type").change(function(){
            $("#toId").val("");
            $("#object").val("");
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

