package com.leoman.user.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.user.entity.User;
import com.leoman.user.entity.UserVip;
import com.leoman.user.service.UserVipService;
import com.leoman.user.service.impl.UserVipServiceImpl;
import com.leoman.vipsetting.entity.SystemVipLevel;
import com.leoman.vipsetting.service.SystemVipLevelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping(value = "/admin/userVip")
public class UserVipController extends GenericEntityController<UserVip,UserVip,UserVipServiceImpl> {

    @Autowired
    private UserVipService userVipService;
    @Autowired
    private SystemVipLevelService systemVipLevelService;
    @RequestMapping(value = "/index")
    public String index(Model model,String details){
        if(StringUtils.isNotBlank(details) && "1".equals(details)){
            model.addAttribute("details",details);
        }
        List<SystemVipLevel> systemVipLevels = systemVipLevelService.OrderByLevel();
        model.addAttribute("systemVipLevels",systemVipLevels);
        return "/uservip/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, User user ,Integer level,String details){
        Page<UserVip> page = null;
        UserVip userVip = new UserVip();
        User u = new User();
        try {
            int pagenum = getPageNum(start,length);
            userVip.setUser(user);
            u.setVipLevel(level);
            userVip.setUser(u);
            page = userVipService.findAll(details,userVip, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,page);
    }

}
