<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside id="sidebar">
    <!-- Sidbar Widgets -->
    <%--<div class="side-widgets overflow">--%>
        <!-- Profile Menu -->
        <%--<div class="text-center s-widget m-b-25 dropdown" id="profile-menu">--%>
            <%--<a href="" data-toggle="dropdown">--%>
                <%--<img class="profile-pic animated" src="${contextPath}/html/img/profile-pic.jpg" alt="">--%>
            <%--</a>--%>
            <%--<ul class="dropdown-menu profile-menu">--%>
                <%--<li><a href="${contextPath}/admin/logout">退&nbsp;&nbsp;&nbsp;出</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%----%>
        <%--<!-- Calendar -->--%>
        <%--<div class="s-widget m-b-25">--%>
            <%--<div id="sidebar-calendar"></div>--%>
        <%--</div>--%>
        <%----%>
        <%--<!-- Feeds -->--%>
        <%--<div class="s-widget m-b-25">--%>
            <%--<h2 class="tile-title">--%>
               <%--热门新闻--%>
            <%--</h2>--%>
            <%----%>
            <%--<div class="s-widget-body">--%>
                <%--<div id="news-feed"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <!-- Side Menu -->
    <ul class="list-unstyled side-menu">
        <c:forEach items="${sessionScope.moduleParent}" var="v">
            <li class="dropdown">
                <a class="sa-side-ui" href="">
                    <span class="menu-item">${v.name}</span>
                </a>
                <ul class="list-unstyled menu-item">
                    <c:forEach items="${sessionScope.moduleChild}" var="m">
                        <c:if test="${v.id eq m.parent.id}">
                            <li><a href="${contextPath}${m.url}">${m.name}</a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-form" href="">--%>
                <%--<span class="menu-item">用户管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/user/index">用户列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/userVip/index">会员列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/admin/index">管理人员</a></li>--%>
                <%--<li><a href="${contextPath}/admin/stadiumUser/index">球场主列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-form" href="">--%>
                <%--<span class="menu-item">权限管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/module/index">模块列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/role/index">角色列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-chart" href="">--%>
                <%--<span class="menu-item">散客管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/reserve/index">散客列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-ui" href="">--%>
                <%--<span class="menu-item">球队管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/team/index">球队列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/teamRace/index">赛事列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-folder" href="">--%>
                <%--<span class="menu-item">约场地管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/stadiumBooking/index">约场地列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-page" href="">--%>
                <%--<span class="menu-item">球场管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/stadium/index">球场列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-page" href="">--%>
                <%--<span class="menu-item">平台赛事管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/hostRace/index">平台赛事列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-typography" href="">--%>
                <%--<span class="menu-item">看球管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/watchingRace/index">直播看球列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/bigRace/index">赛事列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/girl/index">宝贝列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/girlUser/index">宝贝订单管理</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-ui" href="">--%>
                <%--<span class="menu-item">互动管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/post/index">帖子列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/activity/index">活动列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/information/index">资讯列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-ui" href="">--%>
                <%--<span class="menu-item">说明页面管理</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/vipLevelMessage/detail">vip等级说明</a></li>--%>
                <%--<li><a href="${contextPath}/admin/vipMessage/detail">vip优惠说明</a></li>--%>
                <%--<li><a href="${contextPath}/admin/credibilityMessage/detail">信誉度说明</a></li>--%>
                <%--<li><a href="${contextPath}/admin/orderballMessage/detail">约球须知说明</a></li>--%>
                <%--<li><a href="${contextPath}/admin/serviceMessage/detail">注册服务条款</a></li>--%>
                <%--<li><a href="${contextPath}/admin/girlServiceMessage/detail">看球宝贝服务说明</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="sa-side-ui" href="">--%>
                <%--<span class="menu-item">APP设置相关</span>--%>
            <%--</a>--%>
            <%--<ul class="list-unstyled menu-item">--%>
                <%--<li><a href="${contextPath}/admin/systemBanner/index">首页设置</a></li>--%>
                <%--<li><a href="${contextPath}/admin/systemVipSetting/index">vip相关设置</a></li>--%>
                <%--<li><a href="${contextPath}/admin/systemVipCredibility/index">信誉度相关设置</a></li>--%>
                <%--<li><a href="${contextPath}/admin/systemMessage/index">系统消息列表</a></li>--%>
                <%--<li><a href="${contextPath}/admin/userFeedback/index">反馈管理</a></li>--%>
                <%--<li><a href="${contextPath}/admin/systemInsurance/index">保险列表</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    </ul>
</aside>