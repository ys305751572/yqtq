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
    <input type="hidden" value="球场管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">预约时间管理</h1>
        <div class="block-area">
            <input type="hidden" id="id" value="${sub.id}">
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
                <hr class="whiter m-t-20"/>
                <div class="col-md-12 m-b-15">
                    <label>预定排班:</label>
                </div>
                <div class="col-md-12 m-b-15">
                    <div class="col-md-8 clearfix">
                        <div id="calendar" class="p-relative p-10 m-b-20">
                            <!-- Calendar Views -->
                            <ul class="calendar-actions list-inline clearfix">
                                <li class="p-r-0">
                                    <a data-view="month" href="#" class="tooltips" title="Month">
                                        <%--<i class="sa-list-month"></i>--%>
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
<%@ include file="demo.jsp" %>
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
                        header: {
                            center: 'title',
                            left: 'prev, next',
                            right: ''
                        },
                        selectable: true,
                        selectHelper: true,
                        editable: false,     //是否可以拖动
                        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                        monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                        dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                        dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                        today: ["今天"],
                        firstDay: 1,
                        //根据场次ID查询对应的时间设置
                        viewDisplay: function () {
                            var id = $("#id").val();
                            $.post(
                                "${contextPath}/stadium/stadium/findTimeList",
                                { id: id },
                                function (data) {
                                    var list = [];
                                    for(var i=0;i<data.length;i++){
                                        var start = new Date(data[i].startDate).format("yyyy-MM-dd hh:mm");
                                        var end = new Date(data[i].endDate).format("yyyy-MM-dd hh:mm");
                                        var id = data[i].id;
                                        var date = {
                                            "id" : id,
                                            "start" : start,
                                            "end" : end,
                                            "title" : new Date(data[i].startDate).format("hh:mm")+"--"+new Date(data[i].endDate).format("hh:mm")
                                        };
                                        list.push(date);
                                    }
                                    //显示到页面
                                    $.each(list, function (index, term) {
                                        $("#calendar").fullCalendar('renderEvent', term, true);
                                    });
                                });
                        },
                        eventClick : function(event){
                            var id = event.id;
                            var start = $.fullCalendar.formatDate(event.start, "HH:mm");
                            var end = $.fullCalendar.formatDate(event.end, "HH:mm");

                            $("#start").val(start);
                            $("#start option").each(function(){
                                if($(this).val()==start){
                                    $(this).attr("selected",true);
                                }
                            });
                            $("#start").selectpicker('refresh');
                            $("#end").val(end);
                            $("#end option").each(function(){
                                if($(this).val()==end){
                                    $(this).attr("selected",true);
                                }
                            });
                            $("#end").selectpicker('refresh');

                            $('#addNew-event').modal('show');
                            $('body').off('click');
                            $('body').on('click', '#addEvent', function(){
                                var selectdate = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd");
                                console.log(selectdate);
                                var eventForm =  $(this).closest('.modal').find('.form-validation');
                                eventForm.validationEngine('validate');
                                if (!(eventForm).find('.formErrorContent')[0]) {
                                    var s_time = $('#start').val();
                                    var start = selectdate +" "+ s_time;
                                    console.log("start");
                                    console.log(start);
                                    var e_time = $('#end').val();
                                    var end = selectdate +" "+ e_time;
                                    console.log("end");
                                    console.log(end);
                                    $.ajax({
                                        type: "POST",
                                        url: "${contextPath}/stadium/stadium/siteManageSave",
                                        data: {
                                            start : start,
                                            end : end ,
                                            id : id
                                        },
                                        success: function (result) {
                                            if(!result.status) {
                                                $common.fn.notify(result.msg);
                                                return;
                                            }
                                            //Render Event
//                                            $('#calendar').fullCalendar('updateEvent',{
//                                                title: s_time+"--"+e_time,
//                                                start: start,
//                                                end:  end,
//                                                allDay: true
//                                            },true ); //Stick the event
                                            window.location.reload();
                                        }
                                    });

                                    //Render Event
                                    $('#calendar').fullCalendar('updateEvent',{
                                        title: s_time+"--"+e_time,
                                        start: start,
                                        end:  end,
                                        allDay: true
                                    },event ); //Stick the event

                                    $('#addNew-event form')[0].reset();
                                    $('#addNew-event').modal('hide');
                                };
                            });
                        },
                        dayClick: function(date, allDay, jsEvent, view) {
                            //获取所有的是event
//                            var events = $('#calendar').fullCalendar('clientEvents', function(event) {
//                                var events = $('#calendar').fullCalendar('clientEvents', function(event) {
//                                    var eventStart = event.start.format('YYYY-MM-DD');
//                                    var eventEnd = event.end ? event.end.format('YYYY-MM-DD') : null;
//                                    var theDate = date.format('YYYY-MM-DD');
//                                    // Make sure the event starts on or before date and ends afterward
//                                    // Events that have no end date specified (null) end that day, so check if start = date
//                                    return (eventStart <= theDate && (eventEnd >= theDate) && !(eventStart < theDate && (eventEnd == theDate))) || (eventStart == theDate && (eventEnd === null));
//                                });
//                                console.log(events); // do whatever with the events
//                            })
                            $('body').off('click');
                            $('body').on('click', '#addEvent', function(){
                                var siteId = $("#id").val();
                                var selectdate = $.fullCalendar.formatDate(date, "yyyy-MM-dd");
                                var eventForm =  $(this).closest('.modal').find('.form-validation');
                                eventForm.validationEngine('validate');
                                if (!(eventForm).find('.formErrorContent')[0]) {
                                    var s_time = $('#start').val();
                                    console.log(s_time);
                                    var start = selectdate +" "+ s_time;
                                    console.log(start);
                                    var e_time = $('#end').val();
                                    var end = selectdate +" "+ e_time;
                                    console.log(end);
                                    $.ajax({
                                        type: "POST",
                                        url: "${contextPath}/stadium/stadium/siteManageSave",
                                        data: {
                                            start : start,
                                            end : end ,
                                            siteId : siteId
                                        },
                                        success: function (result) {
                                            if(!result.status) {
                                                $common.fn.notify(result.msg);
                                                return;
                                            }
                                        }
                                    });
                                    //Render Event
                                    $('#calendar').fullCalendar('renderEvent',{
                                        title: s_time+"--"+e_time,
                                        start: $('#getStart').val(),
                                        end:  $('#getEnd').val(),
                                        allDay: true
                                    },true ); //Stick the event

                                    $('#addNew-event form')[0].reset();
                                    $('#addNew-event').modal('hide');
                                };
                            });
                        },


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

                $('body').on('click', '#chk', function(){
                    if($(".icheckbox_minimal").attr("aria-checked")=="true"){
                        $("#div_hidden").css('display','block');
                    }else {
                        $("#div_hidden").css('display','none');
                    }
                });

            },
            hid:function(){

                if($(".icheckbox_minimal").attr("aria-checked")=="true"){
                    $("#div_hidden").css('display','block');
                }else {
                    $("#div_hidden").css('display','none');
                }
            }

        }
    };
    $(function () {
        $team.fn.init();
        $("#chk").on("click",function(){
            alert("I'm ");
        })

    })
</script>
<script>
    $('.form_datetime').datetimepicker({
//        language: 'zh-CN',
//        weekStart: 1,
//        todayBtn: 1,
//        autoclose: 1,
//        todayHighlight: 1,
//        startView: 2,
//        minView: "2",
//        forceParse: 0,
//        showMeridian: 1,
//        format: 'yyyy-mm-dd'
    });
</script>
</body>
</html>

