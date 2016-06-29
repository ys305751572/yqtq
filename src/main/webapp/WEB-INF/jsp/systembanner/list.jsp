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
        <h1 class="page-title">首页banner</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${girl.id}">
                <input type="hidden" id="num" name="num" value="1">
                <div class="row">
                    <div class="col-md-12 m-b-15">
                    <div id = "cover">
                        <c:forEach var="v" items="${systemBanner}">
                            <div class="fileupload fileupload-new" data-provides="fileupload" style='float: left;margin-right: 10px;'>
                                <div class="fileupload-preview thumbnail form-control">
                                    <img src="${v.avater}">
                                </div>
                                <div>
                                    <a href='javascript:void(0);' class='btn btn-sm btn-alt m-r-5' onclick='$user.fn.delImage(${v.id});'>删除</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    </div>
                <hr class="whiter m-t-20"/>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="$user.fn.add()">新增</button>
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                    </div>
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
            delImage: function(data){
                var id = data;
                $.ajax({
                    url : "${contextPath}/admin/systemBanner/deleteImage",
                    data : {
                        "id" : id
                    },
                    type : "post",
                    dataType : "json",
                    success : function(result) {
                        if(!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        window.location.reload();

                    }
                });
            },
            add: function (){
                window.location.href = "${contextPath}/admin/systemBanner/add";
            },
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

