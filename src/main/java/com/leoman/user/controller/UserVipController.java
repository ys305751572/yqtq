package com.leoman.user.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.user.entity.User;
import com.leoman.user.entity.UserVip;
import com.leoman.user.service.UserVipService;
import com.leoman.user.service.impl.UserVipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping(value = "/admin/userVip")
public class UserVipController extends GenericEntityController<UserVip,UserVip,UserVipServiceImpl> {

    @Autowired
    private UserVipService userVipService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/uservip/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, User user){
        Page<UserVip> page = null;
        UserVip userVip = new UserVip();
        try {
            int pagenum = getPageNum(start,length);
            userVip.setUser(user);
            page = userVipService.findAll(userVip, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,page);
    }

}
