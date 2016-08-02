package com.leoman.stadiumuser.index.controller;

import com.leoman.common.controller.common.CommonController;
import com.leoman.common.core.Constant;
import com.leoman.common.log.entity.Log;
import com.leoman.index.service.IndexService;
import com.leoman.index.service.LoginService;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumUserService;
import com.leoman.utils.CookiesUtils;
import com.leoman.utils.Md5Util;
import com.leoman.utils.TestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wangbin on 2015/7/29.
 */
@Controller
@RequestMapping(value = "stadium")
public class StadiumIndexController extends CommonController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private IndexService indexService;
    @Autowired
    private StadiumUserService stadiumUserService;

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
        return "stadiumuserjsp/index/login";
    }

    @Log(message = "{0}登录了系统")
    @RequestMapping(value = "/login/check")
    public String checkLogin(String username,
                             String password,
                             HttpServletRequest request, HttpServletResponse response,
                             String remark,
                             ModelMap model) {

        Boolean success = loginService.stadiumUserLogin(request, username, Md5Util.md5(password), Constant.MEMBER_TYPE_GLOBLE,remark);
        if (success) {
            // 登录成功后，将用户名放入cookies
            int loginMaxAge = 30 * 24 * 60 * 60; // 定义cookies的生命周期，这里是一个月。单位为秒
            CookiesUtils.addCookie(response, "username", username, loginMaxAge);
            return "redirect:/stadium/dashboard";
        }
        model.addAttribute("error", "用户名或密码错误!");
        return "redirect:/stadium/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model) {
        loginService.logOut(request,Constant.MEMBER_TYPE_GLOBLE);
        return "stadiumuserjsp/index/login";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/stadium/dashboard";
    }


    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request,
                            HttpServletResponse response,
                            ModelMap model) {

        StadiumUser stadiumUser = getStadiumUser(request);
        //最新订单
        Integer newStadiumBooking = indexService.newStadiumBooking(stadiumUser.getId());
        model.addAttribute("newStadiumBooking",newStadiumBooking);
        //退场订单
        Integer exitStadiumBooking = indexService.exitStadiumBooking(stadiumUser.getId());
        model.addAttribute("exitStadiumBooking",exitStadiumBooking);
        //成功订单
        Integer successfulStadiumBooking = indexService.successfulStadiumBooking(stadiumUser.getId());
        model.addAttribute("successfulStadiumBooking",successfulStadiumBooking);

        Integer day = indexService.income(stadiumUser.getId(), TestUtil.getTimesmorning());
        model.addAttribute("day",day);
        Integer week = indexService.income(stadiumUser.getId(), TestUtil.getTimesWeekmorning());
        model.addAttribute("week",week);
        Integer month = indexService.income(stadiumUser.getId(), TestUtil.getTimesMonthmorning());
        model.addAttribute("month",month);
        Integer sum = indexService.sumIncome(stadiumUser.getId());
        model.addAttribute("sum",sum);
        return "stadiumuserjsp/index/index";
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    private StadiumUser getStadiumUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        StadiumUser stadiumUser = (StadiumUser) session.getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        return stadiumUser;
    }


}
