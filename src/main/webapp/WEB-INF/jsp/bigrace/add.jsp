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
    <input type="hidden" id="mian_module" value="看球管理">
    <input type="hidden" id="child_module" value="大型赛事列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">赛事编辑</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${bigRace.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>看球名称：</label>
                        <input type="text" id="name" name="name" value="${bigRace.name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-6 m-b-15">
                        <label>球队甲方：</label>
                        <input type="text" id="team1name" name="team1name" value="${bigRace.team1name}" class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>球队封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <c:if test="${bigRace.avater1AbsolutePath ne null}">
                                    <img src="${bigRace.avater1AbsolutePath}">
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
                                <c:if test="${bigRace.avater2AbsolutePath ne null}">
                                    <img src="${bigRace.avater2AbsolutePath}">
                                </c:if>
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile2" name="imageFile2" type="file"/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>比赛地点：</label>
                        <input type="text" id="stadiumName" name="stadiumName" value="${bigRace.stadium.name}"  class="input-sm form-control validate[required]" placeholder="..."  disabled>
                        <input type="hidden" id="stadiumId" name="stadiumId" value="${bigRace.stadium.id}" >
                        <a onclick="$user.fn.selectValue()" class="btn btn-alt m-r-5" style="margin-top: 10px">选择</a>
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
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                if($("#name").val()==""){
                    $leoman.notify('看球名称不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($('.fileupload-preview img').size()<2 || $('.fileupload-preview img').width()==0){
                    $leoman.notify('球队封面不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#team1name").val()==""){
                    $leoman.notify('甲方队名不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#team2name").val()==""){
                    $leoman.notify('乙方方队名不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#stadiumId").val()==""){
                    $leoman.notify('比赛地点不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($("#sDate").val()==""){
                    $leoman.notify('开始时间不能为空', "error");
                    isCheck=false;
                    return;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('球场简介不能为空', "error");
                    isCheck=false;
                    return;
                }
                if(isCheck){
                    var startDate = this.transdate($("#sDate").val());
                    $("#startDate").val(startDate);
                    $("#formId").ajaxSubmit({
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
            },
            selectValue : function(){
                var iWidth=1000; //弹出窗口的宽度;
                var iHeight=600; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                window.open("${contextPath}/admin/stadium/select","球场列表","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
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
        forceParse: 0
    });
</script>
</body>
</html>

