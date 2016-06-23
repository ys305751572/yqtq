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
        <h1 class="page-title">赛事编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${bigRace.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>看球名称：</label>
                        <input type="text" id="name" name="name" value="${bigRace.name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>球队甲方：</label>
                        <input type="text" id="team1name" name="team1name" value="${bigRace.team1name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>球队封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <c:if test="${bigRace.avater1 ne null}">
                                    <img src="${bigRace.avater1}">
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
                    <div class="col-md-6 m-b-15">
                        <label>球队乙方：</label>
                        <input type="text" id="team2name" name="team2name" value="${bigRace.team2name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>球队封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <c:if test="${bigRace.avater2 ne null}">
                                    <img src="${bigRace.avater2}">
                                </c:if>
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile2" name="imageFile" type="file"/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>比赛地点：</label>
                        <input type="text" id="didian" name="didians" value=""  class="input-sm form_datetime form-control validate[required]" placeholder="..." >
                        <a href="${contextPath}/admin/stadium/index">选择</a>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>比赛时间：</label>
                        <input type="text" id="sDate" value="<date:date format='yyyy-MM-dd HH:mm' value='${bigRace.startDate}'></date:date>" name="sDate" class="input-sm form_datetime form-control validate[required]" placeholder="..." >
                        <input type="hidden" id="startDate" value="" name="startDate">
                    </div>

                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述</label>
                        <div class="wysiwye-editor" id="detail" name="detail">${bigRace.description}</div>
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
                var code =  $('.wysiwye-editor').code();
                var isCheck = true;
                if($("#name").val()==""){
                    alert("看球名称不能为空!");
                    isCheck=false;
                }
                if($("#cityId").val()==""){
                    alert("城市不能为空!");
                    isCheck=false;
                }
                if($("#team1name").val()==""){
                    alert("甲方队名不能为空!");
                    isCheck=false;
                }
                if($("#team2name").val()==""){
                    alert("乙方方队名不能为空!");
                    isCheck=false;
                }
                if($("#sDate").val()==""){
                    alert("开始时间不能为空!");
                    isCheck=false;
                }
                if($('.fileupload-preview img').size()<2 || $('.fileupload-preview img').width()==0){
                    alert("球队封面不能为空!");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    alert("球场简介不能为空!");
                    isCheck=false;
                }
                if(isCheck){
                    var startDate = this.transdate($("#sDate").val());
                    $("#startDate").val(startDate);
                    $("#fromId").ajaxSubmit({
                        url : "${contextPath}/admin/bigRace/save",
                        type : "POST",
                        data : {
                            "detail" : code
                        },
                        success : function(result) {
                            if(!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/bigRace/index";
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
    };
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

