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
                <li><a href="${contextPath}/admin/logout">退&nbsp;&nbsp;&nbsp;出</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
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
                <span class="menu-item">用户管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/user/index">用户信息列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-chart" href="">
                <span class="menu-item">散客管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/reserve/index">散客列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-ui" href="">
                <span class="menu-item">球队管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/team/index">球队列表</a></li>
                <li><a href="${contextPath}/admin/teamRace/index">赛事列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-folder" href="">
                <span class="menu-item">约场地管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/1/index">约场地列表</a></li>
                <li><a href="${contextPath}/admin/stadium/index">场地列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-page" href="">
                <span class="menu-item">病理讲座管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/pg/index">病理讲座列表</a></li>
                <li><a href="${contextPath}/admin/pg2/index">病理讲座列表V2</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-typography" href="">
                <span class="menu-item">题库管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/question/qc/index">题库列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-ui" href="">
                <span class="menu-item">签约管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/sign/index">签约列表</a></li>
            </ul>
        </li>
    </ul>
</aside>