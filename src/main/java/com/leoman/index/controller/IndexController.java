package com.leoman.index.controller;

import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.common.controller.common.CommonController;
import com.leoman.common.core.Constant;
import com.leoman.common.log.entity.Log;
import com.leoman.index.service.IndexService;
import com.leoman.index.service.LoginService;
import com.leoman.security.entity.Module;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2015/7/29.
 */
@Controller
@RequestMapping(value = "admin")
public class IndexController extends CommonController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private IndexService indexService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String error,
                        ModelMap model) {
        try {
            if (StringUtils.isNotBlank(error)) {
                model.addAttribute("error", error);
            }
            // 先读取cookies，如果存在，则将用户名回写到输入框
            Map<String, Object> params = CookiesUtils.ReadCookieMap(request);
            if (params != null && params.size() != 0) {
                String username = (String) params.get("username");
                model.put("username",username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index/login";
    }

    @Log(message = "{0}登录了系统")
    @RequestMapping(value = "/login/check")
    public String checkLogin(String username,
                             String password,
                             HttpServletRequest request, HttpServletResponse response,
                             String remark,
                             ModelMap model) {

        Boolean success = loginService.login(request, username, Md5Util.md5(password), Constant.MEMBER_TYPE_GLOBLE,remark);
        if (success) {
            // 登录成功后，将用户名放入cookies
            int loginMaxAge = 30 * 24 * 60 * 60; // 定义cookies的生命周期，这里是一个月。单位为秒
            CookiesUtils.addCookie(response, "username", username, loginMaxAge);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "用户名或密码错误!");
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model) {
        loginService.logOut(request,Constant.MEMBER_TYPE_GLOBLE);
        return "index/login";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/admin/dashboard";
    }


    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request,
                            HttpServletResponse response,
                            ModelMap model) {

        HttpSession session=request.getSession();
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        //最近一次登录时间
        admin.setLastLoginDate(System.currentTimeMillis());
        adminService.update(admin);
        Long id = adminService.queryByProperty("username",admin.getUsername()).get(0).getId();
        //子模块
        List<Module> moduleChild = indexService.moduleList(id);
        session.setAttribute("moduleChild",moduleChild);
        //父模块
        List<Long> parentId = new ArrayList<>();
        for(Module m : moduleChild){
            parentId.add(m.getParent().getId());
        }
        List<Module> moduleParent = indexService.parentModelueList(parentId);
        session.setAttribute("moduleParent",moduleParent);

        Integer newUserNum = indexService.newUserNum();
        model.addAttribute("newUserNum",newUserNum);

        Integer newUserVipNum = indexService.newUserVipNum();
        model.addAttribute("newUserVipNum",newUserVipNum);

        Integer newReserveNum = indexService.newReserveNum();
        model.addAttribute("newReserveNum",newReserveNum);

        Integer newStadiumBookingNum = indexService.newStadiumBookingNum();
        model.addAttribute("newStadiumBookingNum",newStadiumBookingNum);

        Integer newTeamNum = indexService.newTeamNum();
        model.addAttribute("newTeamNum",newTeamNum);

        Integer newGirlUserNum = indexService.newGirlUserNum();
        model.addAttribute("newGirlUserNum",newGirlUserNum);

        Integer newPostNum = indexService.newPostNum();
        model.addAttribute("newPostNum",newPostNum);

        Integer newWithdrawNum = indexService.newWithdrawNum();
        model.addAttribute("newWithdrawNum",newWithdrawNum);

        //场地订单统计信息
        Integer allStadiumBookingNum = indexService.allStadiumBookingNum();
        model.addAttribute("allStadiumBookingNum",allStadiumBookingNum);

        Integer useStadiumBookingNum = indexService.useStadiumBookingNum();
        model.addAttribute("useStadiumBookingNum",useStadiumBookingNum);

        Integer notUsedStadiumBookingNum = indexService.notUsedStadiumBookingNum();
        model.addAttribute("notUsedStadiumBookingNum",notUsedStadiumBookingNum);

        Integer allStadiumBookingprice = indexService.allStadiumBookingprice();
        model.addAttribute("allStadiumBookingprice",allStadiumBookingprice);

        //散客统计信息:
        Integer allReserveNum = indexService.allReserveNum();
        model.addAttribute("allReserveNum",allReserveNum);

        Integer successReserveNum = indexService.successReserveNum();
        model.addAttribute("successReserveNum",successReserveNum);

        Integer failureReserveNum = indexService.failureReserveNum();
        model.addAttribute("failureReserveNum",failureReserveNum);

        Integer allReservePrice = indexService.allReservePrice();
        model.addAttribute("allReservePrice",allReservePrice);

        Integer confirmReservePrice = indexService.confirmReservePrice();
        model.addAttribute("confirmReservePrice",confirmReservePrice);

        Integer backReservePrice = indexService.backReservePrice();
        model.addAttribute("backReservePrice",backReservePrice);

        ////看球统计信息:
        Integer allGirlUserNum = indexService.allGirlUserNum();
        model.addAttribute("allGirlUserNum",allGirlUserNum);

        Integer allGirlUserprice = indexService.allGirlUserprice();
        model.addAttribute("allGirlUserprice",allGirlUserprice);

        Integer watchingRaceInvitation = indexService.watchingRaceInvitation();
        model.addAttribute("watchingRaceInvitation",watchingRaceInvitation);

//        return "stadiumuserjsp/index/index";
        return "index/index";
    }


}
