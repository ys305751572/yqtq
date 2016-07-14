package com.leoman.systemvipcredibility.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.systemvipcredibility.entity.SystemVipCredibility;
import com.leoman.systemvipcredibility.service.SystemVipCredibilityService;
import com.leoman.systemvipcredibility.service.impl.SystemVipCredibilityServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/6/30.
 */
@Controller
@RequestMapping(value = "/admin/systemVipCredibility")
public class SystemVipCredibilityController extends GenericEntityController<SystemVipCredibility,SystemVipCredibility,SystemVipCredibilityServiceImpl>{

    @Autowired
    private SystemVipCredibilityService systemVipCredibilityService;

    @RequestMapping(value = "index")
    public String index(Model model){
        return "/systemvipcredibility/list";
    }

    @RequestMapping(value = "/vipCredibilityFrom")
    @ResponseBody
    public List<SystemVipCredibility> vipCredibilityFrom(){
        List<SystemVipCredibility> systemVipCredibilities = systemVipCredibilityService.queryAll();
        return systemVipCredibilities;
    }


    @RequestMapping(value = "/systemVipCredibilitySave")
    @ResponseBody
    public Result systemVipCredibilitySave(SystemVipCredibility systemVipCredibility) {
        SystemVipCredibility s = null;
        if(null!=systemVipCredibility){
            List<SystemVipCredibility> list = systemVipCredibilityService.queryByProperty("action",systemVipCredibility.getAction());
            if(!list.isEmpty() && list.size()>0){
                s = list.get(0);
            }
        }
        if(null != s){
            s.setCredibility(systemVipCredibility.getCredibility());
            systemVipCredibilityService.update(s);
        }else{
            systemVipCredibilityService.save(systemVipCredibility);
        }
        return Result.success();
    }

}
