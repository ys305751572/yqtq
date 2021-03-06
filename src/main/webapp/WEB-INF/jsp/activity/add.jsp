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
    <input type="hidden" id="mian_module" value="互动管理">
    <input type="hidden" id="child_module" value="活动列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">活动编辑</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${activity.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>活动标题：</label>
                        <input type="text" id="title" name="title" value="${activity.title}"
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>活动封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${activity.aveter}">
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
                    <div class="col-md-6 m-b-15">
                        <label>活动简介：</label>
                        <textarea cols="40" rows="6" id="introduction" name="introduction" class="form-control"
                                  placeholder="...">${activity.introduction}</textarea>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>活动详情</label>
                        <div class="wysiwye-editor">${activity.description}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="activity.fn.save();" class="btn btn-info btn-sm m-t-10">提交
                        </button>
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
    activity = {
        v: {
            list: [],
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
                activity.fn.initImage();
            },
            checkData: function () {
                var flag = true;
                var title = $('#title').val();
                var imageFile = $('#imageFile').val();
                var introduction = $('#introduction').val();
                var code = $('.wysiwye-editor').code();


                if (null == title || title == '') {
                    $common.fn.notify("请输入活动标题", "error");
                    flag = false;
                    return;
                }

                if (null == imageFile || imageFile == '') {
                    $common.fn.notify("请上传活动封面", "error");
                    flag = false;
                    return;
                }

                if (null == introduction || introduction == '') {
                    $common.fn.notify("请输入活动简介", "error");
                    flag = false;
                    return;
                }
                if (null == code || code == '') {
                    $common.fn.notify("请输入活动详情", "error");
                    flag = false;
                    return;
                }

                return flag;
            },
            save: function () {
                if (activity.fn.checkData()) {
                    var code = $('.wysiwye-editor').code();
                    $("#formId").ajaxSubmit({
                        url: "${contextPath}/admin/activity/save",
                        type: "POST",
                        data: {
                            "detail": code
                        },
                        success: function (result) {
                            if (result == 1) {
                                $common.fn.notify("操作成功", "success");
                                window.location.href = "${contextPath}/admin/activity/index";
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                }

            }
        }
    }
    $(function () {
        activity.fn.init();
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

