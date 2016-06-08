<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
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
        <h1 class="page-title">帖子详情</h1>
        <div class="block-area">
            <h2 class="tile-title">基本信息</h2>
            <div class="p-10" style="height:520px">
                <div class="col-md-6 m-b-15">
                    <label>发布人:</label>
                    <%--<date:date format="yyyy-MM-dd HH:mm" value="${reserve.startDate}"></date:date>--%>
                    <input type="text" id="nickName" value="${post.user.nickName}" name="nickName" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>发布时间:</label>
                    <input type="text" id="createDate" name="createDate" class="input-sm form-control validate[required]" placeholder="..." disabled value="<date:date value="${post.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date>" />
                </div>
                <div class="col-md-6 m-b-15">
                    <label>评论数:</label>
                    <input type="text" id="commentNum" value="${postCommentList.size()}" name="commentNum" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>分享数:</label>
                    <input type="text" id="shareNum" value="${post.shareNum}" name="shareNum" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>举报数:</label>
                    <input type="text" id="reportNum" value="${post.reportNum}" name="reportNum" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>帖子内容：</label>
                    <textarea id="detail" name="detail" class="form-control" rows="8" cols="40" style="margin-bottom: 10px;">${post.content}</textarea>
                    <div id="lastImageDiv" value="" name="lastImageDiv" />
                </div>
                <%--<div class="col-md-6 m-b-15">
                    <label>操作:</label>
                    <button title='封禁' class='btn btn-primary btn-circle changeStatus' onclick='$post.fn.changeStatus()'></button>
                </div>--%>
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                    <label>评论:</label>
                    <!-- 评论列表及评论列表 -->
                    <c:if test="${null == postCommentList || postCommentList.size() == 0 }">
                        暂无评论
                    </c:if>
                    <c:if test="${null != postCommentList && postCommentList.size() > 0 }">
                        <c:forEach var="co" items="${postCommentList }">
                            <ul id="ul" + ${co.id }>
                                <span style="color:yellow;">${co.fUser.nickName}</span>回复
                                <span style="color:yellow;">${co.tUser.nickName}</span>：
                                <span style="white-space:normal;word-wrap:break-word;word-break:break-all;">${co.content }</span>
                                &nbsp;&nbsp; <date:date value="${co.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date>
                            </ul>
                        </c:forEach>
                    </c:if>
                </div>

                <div id="tempDiv" style="display:none;float: left; height: 200px;width: 300px;margin-right:6px; z-index: 0;margin-bottom: 50px;">
                    <img class="imgs" alt="" src="" style="height: 200px;width: 300px; z-index: 1;"/>
                    <input name="imageIdTemp" type="hidden"/>
                </div>
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    var post = {
        v: {
            id: "post",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $.ajaxSetup({
                    async: false
                });

                // 加载图片数组
                post.fn.getSerImages();

            },
            getSerImages: function () {
                var imgList = ${postImageList };

                $.each(imgList, function (i, item) {
                    if (null != item) {
                        post.fn.insertImage(item.path, item.id);
                    } else {
                        $('#lastImageDiv').html('暂无');
                    }
                });

            },
            insertImage: function (path, id) {
                var tempDiv = $("#tempDiv").clone();
                tempDiv.prop('id', '');
                tempDiv.css("display", "block");
                tempDiv.children(":first").prop("src", path);
                tempDiv.children(":first").next().prop("value", id);
                tempDiv.insertBefore("#lastImageDiv");
            }
        }
    }
    $(function () {
        post.fn.init();
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

