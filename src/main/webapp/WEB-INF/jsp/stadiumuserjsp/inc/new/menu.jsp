<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside id="sidebar">
    <!-- Sidbar Widgets -->
    <div class="side-widgets overflow">
        <!-- Profile Menu -->
        <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
            <a href="" data-toggle="dropdown">
                <img class="profile-pic animated" src="${contextPath}/html/img/profile-pic.jpg" alt="">
            </a>
            <ul class="dropdown-menu profile-menu">
                <li><a href="${contextPath}/stadium/logout">退&nbsp;&nbsp;&nbsp;出</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
            </ul>
        </div>
        
        <!-- Calendar -->
        <div class="s-widget m-b-25">
            <div id="sidebar-calendar"></div>
        </div>
        
        <!-- Feeds -->
        <div class="s-widget m-b-25">
            <h2 class="tile-title">
               热门新闻
            </h2>
            
            <div class="s-widget-body">
                <div id="news-feed"></div>
            </div>
        </div>
    </div>

    <!-- Side Menu -->
    <ul class="list-unstyled side-menu">

        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">个人管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/stadium/details/index">个人资料</a></li>
                <li><a href="${contextPath}/stadium/details/changePwd">修改密码</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">球场管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/stadium/stadium/index">球场列表</a></li>
                <li><a href="${contextPath}/stadium/stadiumBooking/index">订单列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">账单管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/stadium/stadiumUserWithdraw/index">提现列表</a></li>
                <li><a href="${contextPath}/stadium/stadiumUserWithdraw/apply">提现申请</a></li>
                <%--<li><a href="${contextPath}/stadium/1/index">提现详情</a></li>--%>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">相关设置</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/stadium/1/index">参数设置</a></li>
            </ul>
        </li>

    </ul>
</aside>