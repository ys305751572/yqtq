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
    <link rel="stylesheet" type="text/css" href="${contextPath}/html/js/Fullcalendar/css/fancybox.css" />
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
        <h1 class="page-title">预约时间管理</h1>
        <div class="block-area">
            <div class="row">
                <div class="col-md-6 m-b-15">
                    <label>球场名称:</label>
                    <input type="text" id="name" name="name" value="${stadium}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>场地编号:</label>
                    <input type="text" id="code" name="code" value="${sub.code}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                <div class="col-md-6 m-b-15">
                    <label>场地类型:</label>
                    <input type="text" id="type" name="type" value="<c:if test="${sub.type eq 3}">三人制</c:if><c:if test="${sub.type eq 5}">五人制</c:if><c:if test="${sub.type eq 7}">七人制</c:if><c:if test="${sub.type eq 11}">十一人制</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                </div>
                    <label>预定排班:</label>
                    <div class="col-md-8 clearfix">

                        <div id="calendar" class="p-relative p-10 m-b-20">
                            <!-- Calendar Views -->
                            <ul class="calendar-actions list-inline clearfix">
                                <li class="p-r-0">
                                    <a data-view="month" href="#" class="tooltips" title="Month">
                                        <i class="sa-list-month"></i>
                                    </a>
                                </li>
                                <li class="p-r-0">
                                    <a data-view="agendaWeek" href="#" class="tooltips" title="Week">
                                        <i class="sa-list-week"></i>
                                    </a>
                                </li>
                                <li class="p-r-0">
                                    <a data-view="agendaDay" href="#" class="tooltips" title="Day">
                                        <i class="sa-list-day"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
            </div>
            <hr class="whiter m-t-20"/>
        </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                </div>
            </div>
        </div>
    </section>

    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<%@ include file="event.jsp" %>
<script src="${contextPath}/html/js/Fullcalendar/js/jquery.fancybox-1.3.1.pack.js"></script>
<script>
    $team = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $(document).ready(function() {
                    var date = new Date();
                    var d = date.getDate();
                    var m = date.getMonth();
                    var y = date.getFullYear();
                    $('#calendar').fullCalendar({
//                        dayClick: function(event) {
//                            console.log($(event));
//                            alert(event);
//
//                        },
                        <%--dayClick: function(date, allDay, jsEvent, view) {--%>
                            <%--$("#setting").modal("show");--%>
                            <%--$("#confirm").off("click");--%>
                            <%--$("#confirm").on("click",function(){--%>
                                <%--$.ajax({--%>
                                    <%--"url": "${contextPath}/stadium/stadium/dayEdit",--%>
                                    <%--"data": {--%>
                                        <%--"id": id,--%>
                                        <%--"status":tempStatus--%>
                                    <%--},--%>
                                    <%--"dataType": "json",--%>
                                    <%--"type": "POST",--%>
                                    <%--success: function (result) {--%>
                                        <%--if (result.status) {--%>
                                            <%--$("#delete").modal("hide");--%>
                                            <%--$girl.v.dTable.ajax.reload(null,false);--%>
                                        <%--} else {--%>
                                            <%--$common.fn.notify("操作失败", "error");--%>
                                        <%--}--%>
                                    <%--}--%>
                                <%--});--%>
                            <%--})--%>
                        <%--},--%>
                        header: {
                            center: 'title',
                            left: 'prev, next',
                            right: ''
                        },

                        selectable: true,
                        selectHelper: true,
                        editable: true,     //是否可以拖动
                        events: [
                            {
                                title: '不会用烦的一笔',
                                start: new Date(y, m, d),
                                end: new Date(y, m, d)
                            }
                        ],

                        //On Day Select
                        select: function(start, end, allDay) {
                            $('#addNew-event').modal('show');
                            $('#addNew-event input:text').val('');
                            $('#getStart').val(start);
                            $('#getEnd').val(end);
                        },

                        eventResize: function(event,dayDelta,minuteDelta,revertFunc) {
                            $('#editEvent').modal('show');

                            var info =
                                            "The end date of " + event.title + "has been moved " +
                                            dayDelta + " days and " +
                                            minuteDelta + " minutes."
                                    ;

                            $('#eventInfo').html(info);


                            $('#editEvent #editCancel').click(function(){
                                revertFunc();
                            })
                        }
                    });

                    $('body').on('click', '#addEvent', function(){
                        var eventForm =  $(this).closest('.modal').find('.form-validation');
                        eventForm.validationEngine('validate');

                        if (!(eventForm).find('.formErrorContent')[0]) {

                            //Event Name
                            var eventName = $('#eventName').val();

                            //Render Event
                            $('#calendar').fullCalendar('renderEvent',{
                                title: eventName,
                                start: $('#getStart').val(),
                                end:  $('#getEnd').val(),
                                allDay: true,
                            },true ); //Stick the event

                            $('#addNew-event form')[0].reset();
                            $('#addNew-event').modal('hide');
                        }
                    });
                });

                //Calendar views
                $('body').on('click', '.calendar-actions > li > a', function(e){
                    e.preventDefault();
                    var dataView = $(this).attr('data-view');
                    $('#calendar').fullCalendar('changeView', dataView);

                    //Custom scrollbar
                    var overflowRegular, overflowInvisible = false;
                    overflowRegular = $('.overflow').niceScroll();

                });
            }
        }
    };
    $(function () {
        $team.fn.init();
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

