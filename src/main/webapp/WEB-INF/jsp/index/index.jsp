<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8">

        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

        <title>Super Admin Responsive Template</title>
            
        <!-- CSS -->
        <%@ include file="../inc/new/css.jsp"%>
    </head>
    <body id="skin-cloth">
    <%@ include file="../inc/new/header.jsp"%>
        <div class="clearfix"></div>
        
        <section id="main" class="p-relative" role="main">
            <%@ include file="../inc/new/menu.jsp"%>
            <!-- Content -->
            <section id="content" class="container">
            
                <!-- Messages Drawer -->
                <div id="messages" class="tile drawer animated">
                    <div class="listview narrow">
                        <div class="media">
                            <a href="">Send a New Message</a>
                            <span class="drawer-close">&times;</span>
                            
                        </div>
                        <div class="overflow" style="height: 254px">
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/1.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Nadin Jackson - 2 Hours ago</small><br>
                                    <a class="t-overflow" href="">Mauris consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/2.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">David Villa - 5 Hours ago</small><br>
                                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/3.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Harris worgon - On 15/12/2013</small><br>
                                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/4.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Mitch Bradberry - On 14/12/2013</small><br>
                                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/1.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Nadin Jackson - On 15/12/2013</small><br>
                                    <a class="t-overflow" href="">Ipsum wintoo consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/2.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">David Villa - On 16/12/2013</small><br>
                                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/3.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Harris worgon - On 17/12/2013</small><br>
                                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/4.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Mitch Bradberry - On 18/12/2013</small><br>
                                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/5.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Wendy Mitchell - On 19/12/2013</small><br>
                                    <a class="t-overflow" href="">Integer a eros dapibus, vehicula quam accumsan, tincidunt purus</a>
                                </div>
                            </div>
                        </div>
                        <div class="media text-center whiter l-100">
                            <a href=""><small>VIEW ALL</small></a>
                        </div>
                    </div>
                </div>

                <!-- Notification Drawer -->
                <div id="notifications" class="tile drawer animated">
                    <div class="listview narrow">
                        <div class="media">
                            <a href="">Notification Settings</a>
                            <span class="drawer-close">&times;</span>
                        </div>
                        <div class="overflow" style="height: 254px">
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/1.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Nadin Jackson - 2 Hours ago</small><br>
                                    <a class="t-overflow" href="">Mauris consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/2.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">David Villa - 5 Hours ago</small><br>
                                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/3.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Harris worgon - On 15/12/2013</small><br>
                                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/4.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Mitch Bradberry - On 14/12/2013</small><br>
                                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/1.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">Nadin Jackson - On 15/12/2013</small><br>
                                    <a class="t-overflow" href="">Ipsum wintoo consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <img width="40" src="${contextPath}/html/img/profile-pics/2.jpg" alt="">
                                </div>
                                <div class="media-body">
                                    <small class="text-muted">David Villa - On 16/12/2013</small><br>
                                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                                </div>
                            </div>
                        </div>
                        <div class="media text-center whiter l-100">
                            <a href=""><small>VIEW ALL</small></a>
                        </div>
                    </div>
                </div>
                <h4 class="page-title">首页</h4>
                <!-- Quick Stats -->
                <div style="background-color: rgba(238, 238, 238, 0.09);width:auto;height: 70px">
                    <div style="float: left;margin-left: 25px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/user/index?details=1" title="新增用户" class="btn btn-alt m-r-5">新增用户( ${newUserNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/userVip/index?details=1" title="新增会员" class="btn btn-alt m-r-5">新增会员( ${newUserVipNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/reserve/index?details=1" title="新增约球" class="btn btn-alt m-r-5">新增约球( ${newReserveNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/stadiumBooking/index?details=1" title="场地订单" class="btn btn-alt m-r-5">场地订单( ${newStadiumBookingNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/team/index?details=1" title="新增球队" class="btn btn-alt m-r-5">新增球队( ${newTeamNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/girlUser/index?details=1" title="新增看球" class="btn btn-alt m-r-5">新增看球( ${newGirlUserNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/post/index?details=1" title="新增帖子" class="btn btn-alt m-r-5">新增帖子( ${newPostNum} )</a>
                    </div>
                    <div style="float: left;margin-left: 15px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/stadiumUserWithdraw/index?details=1" title="新增提现" class="btn btn-alt m-r-5">新增提现( ${newWithdrawNum} )</a>
                    </div>
                    <div style="float: right;margin-right: 20px;margin-top: 15px;">
                        <a data-toggle="modal" href="${contextPath}/admin/logout" title="退出" class="btn btn-alt m-r-5">退&nbsp;&nbsp;&nbsp;出</a>
                    </div>
                </div>
                <div class="block-area" id="tableBordered">
                    <div class="col-md-12 m-b-15">
                        <h5>场地订单统计信息:</h5>
                        <div class="table-responsive overflow">
                            <table class="tile table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <td width="45%">已订</td>
                                    <td >${allStadiumBookingNum}</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>已使用</td>
                                    <td>${useStadiumBookingNum}</td>
                                </tr>
                                <tr>
                                    <td>待使用</td>
                                    <td>${notUsedStadiumBookingNum}</td>
                                </tr>
                                <tr>
                                    <td>总金额</td>
                                    <td>${allStadiumBookingprice}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <h5>散客统计信息:</h5>
                        <div class="table-responsive overflow">
                            <table class="tile table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <td width="45%">组队数</td>
                                    <td>${allReserveNum}</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>组队成功数</td>
                                    <td>${successReserveNum}</td>
                                </tr>
                                <tr>
                                    <td>组队失败数</td>
                                    <td>${failureReserveNum}</td>
                                </tr>
                                <tr>
                                    <td>总金额</td>
                                    <td>${allReservePrice}</td>
                                </tr>
                                <tr>
                                    <td>确认金额</td>
                                    <td>${confirmReservePrice}</td>
                                </tr>
                                <tr>
                                    <td>退回金额</td>
                                    <td>${backReservePrice}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <h5>看球统计信息:</h5>
                        <div class="table-responsive overflow">
                            <table class="tile table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <td width="45%">宝贝订单</td>
                                    <td>${allGirlUserNum}</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>订单金额</td>
                                    <td>${allGirlUserprice}</td>
                                </tr>
                                <tr>
                                    <td>直播看球邀请次数</td>
                                    <td>${watchingRaceInvitation}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15">
                        <h5>访问统计:</h5>
                        <div class="table-responsive overflow">
                            <table class="tile table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <td width="45%">今日访问</td>
                                    <td>NULL</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>在线人数</td>
                                    <td>NULL</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        <%@ include file="../inc/new/foot.jsp"%>
    </body>
</html>
