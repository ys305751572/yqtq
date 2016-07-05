package com.leoman.systemvipcredibility.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.systemvipcredibility.entity.SystemVipCredibility;
import com.leoman.systemvipcredibility.service.SystemVipCredibilityService;
import com.leoman.systemvipcredibility.service.impl.SystemVipCredibilityServiceImpl;
import com.leoman.utils.Result;
import com.leoman.vipsetting.entity.SystemVipLevel;
import com.leoman.vipsetting.service.SystemVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
@RequestMapping(value = "/admin/systemVipCredibility")
public class SystemVipCredibilityController extends GenericEntityController<SystemVipCredibility,SystemVipCredibility,SystemVipCredibilityServiceImpl>{


    @Autowired
    private SystemVipLevelService systemVipLevelService;
    @Autowired
    private SystemVipCredibilityService systemVipCredibilityService;

    @RequestMapping(value = "index")
    public String index(Model model){
        List<SystemVipLevel> systemVipLevels = systemVipLevelService.OrderByLevel();
        model.addAttribute("systemVipLevels",systemVipLevels);
        return "/systemvipcredibility/list";
    }

    @RequestMapping(value = "/vipCredibilityFrom")
    @ResponseBody
    public List<SystemVipCredibility> vipCredibilityFrom(Long systemVipId){
        List<SystemVipCredibility> systemVipCredibilities = systemVipCredibilityService.queryByProperty("systemVipId",systemVipId);
        return systemVipCredibilities;
    }


    @RequestMapping(value = "/systemVipCredibilitySave")
    @ResponseBody
    public Result systemVipCredibilitySave(SystemVipCredibility systemVipCredibility,Integer credibility1,Long systemVipId) {
        SystemVipCredibility s = null;
        if(null!=systemVipCredibility && null!=systemVipId){
            List<SystemVipCredibility> list = systemVipCredibilityService.credibilityList(systemVipId,systemVipCredibility.getAction());
            if(!list.isEmpty()){
                s = list.get(0);
            }
        }
        if(null != s){
            s.setCredibility(credibility1);
            systemVipCredibilityService.update(s);
        }else {
            systemVipCredibility.setSystemVipId(systemVipId);
            systemVipCredibility.setCredibility(credibility1);
            systemVipCredibilityService.save(systemVipCredibility);
        }
        return Result.success();
    }

}
