package com.leoman.vipsetting.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.utils.Result;
import com.leoman.vipsetting.entity.SystemVip;
import com.leoman.vipsetting.entity.SystemVipExperience;
import com.leoman.vipsetting.entity.SystemVipLevel;
import com.leoman.vipsetting.service.SystemVipExperienceService;
import com.leoman.vipsetting.service.SystemVipLevelService;
import com.leoman.vipsetting.service.SystemVipService;
import com.leoman.vipsetting.service.impl.SystemVipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/admin/systemVipSetting")
public class SystemVipSettingController extends GenericEntityController<SystemVip,SystemVip,SystemVipServiceImpl>{

    @Autowired
    private SystemVipService systemVipService;
    @Autowired
    private SystemVipLevelService systemVipLevelService;
    @Autowired
    private SystemVipExperienceService systemVipExperienceService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<SystemVip> systemVips = systemVipService.queryAll();
        if(!systemVips.isEmpty()){
            model.addAttribute("systemVips",systemVips.get(0));
        }
        List<SystemVipLevel> systemVipLevels = systemVipLevelService.OrderByLevel();
        model.addAttribute("systemVipLevels",systemVipLevels);
        return "/systemvipsetting/list";
    }

    /**
     * VIP会员价格保存
     * @param systemVip
     * @return
     */
    @RequestMapping(value = "/systemVipSave")
    @ResponseBody
    public Result systemVipSave(SystemVip systemVip) {
        SystemVip s = null;
        if(null!= systemVip.getId()){
            s = systemVipService.queryByPK(systemVip.getId());
        }
        if(null != s){
            systemVip.setCreateDate(s.getCreateDate());
        }
        systemVipService.save(systemVip);
        return Result.success();
    }

    /**
     * VIP会员等级保存
     * @param systemVipLevel
     * @return
     */
    @RequestMapping(value = "/systemVipLevelSave")
    @ResponseBody
    public Result systemVipLevelSave(SystemVipLevel systemVipLevel,Integer level1) {
        SystemVipLevel s = null;
        if(null!=systemVipLevel && systemVipLevel.getLevel().equals(level1)){
            List<SystemVipLevel> list = systemVipLevelService.queryByProperty("level",level1);
            if(!list.isEmpty()){
                s = list.get(0);
            }
        }
        if(null != s){
            s.setLevel(systemVipLevel.getLevel());
            s.setExperience(systemVipLevel.getExperience());
            s.setPreferente(systemVipLevel.getPreferente());
            systemVipLevelService.update(s);
        }else {
            systemVipLevel.setSystemVipId(Long.parseLong(level1.toString()));
            systemVipLevelService.save(systemVipLevel);
        }
        return Result.success();
    }

    /**
     * VIP活动获得经验保存
     * @param systemVipExperience
     * @param experience
     * @param systemVipId
     * @return
     */
    @RequestMapping(value = "/systemVipExperienceSave")
    @ResponseBody
    public Result systemVipExperienceSave(SystemVipExperience systemVipExperience,Integer experience,Long systemVipId) {
        SystemVipExperience s = null;
        if(null!=systemVipExperience && null!=systemVipId){
            List<SystemVipExperience> list = systemVipExperienceService.experienceList(systemVipId,systemVipExperience.getAction());
            if(!list.isEmpty()){
                s = list.get(0);
            }
        }
        if(null != s){
            s.setExperience(experience);
            systemVipExperienceService.update(s);
        }else {
            systemVipExperience.setSystemVipId(systemVipId);
            systemVipExperience.setExperience(experience);
            systemVipExperienceService.save(systemVipExperience);
        }
        return Result.success();
    }

    /**
     * 经验列表
     * @param systemVipId
     * @return
     */
    @RequestMapping(value = "/vipExperienceFrom")
    @ResponseBody
    public List<SystemVipExperience> vipExperienceFrom(Long systemVipId){
        List<SystemVipExperience> systemVipExperiences = systemVipExperienceService.queryByProperty("systemVipId",systemVipId);
        return systemVipExperiences;
    }

}
