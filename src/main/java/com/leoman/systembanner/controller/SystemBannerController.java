package com.leoman.systembanner.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.hostRace.entity.HostRace;
import com.leoman.image.entity.FileBo;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systembanner.entity.SystemBanner;
import com.leoman.systembanner.service.SystemBannerService;
import com.leoman.systembanner.service.impl.SystemBannerServiceImpl;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/admin/systemBanner")
public class SystemBannerController extends GenericEntityController<SystemBanner,SystemBanner,SystemBannerServiceImpl>{

    @Autowired
    private SystemBannerService systemBannerService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<SystemBanner> systemBannerList =  systemBannerService.queryAll();
        List<SystemBanner> systemBanner = new ArrayList<>();
        for(SystemBanner s :  systemBannerList){
            s.setAvater(StringUtils.isNotBlank(s.getAvater()) ? ConfigUtil.getString("upload.url")+s.getAvater() : "");
            systemBanner.add(s);
        }
        model.addAttribute("systemBanner",systemBanner);
        return "/systembanner/list";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "/systembanner/add";
    }

    @RequestMapping(value = "/select")
    public String selectValue(Integer type){
        //活动
        if(type==0){
            return "/systembanner/activitylist";
        }
        //资讯
        if(type==1){
            return "/systembanner/informationlist";
        }
        //平台赛事
        return "/systembanner/hostracelist";

    }


    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(SystemBanner systemBanner, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile) {
        if(imageFile!=null && imageFile.getSize()>0) {
            FileBo fileBo = null;
            try {
                fileBo = FileUtil.save(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                systemBanner.setAvater(fileBo.getPath());
            }
        }
        systemBannerService.save(systemBanner);
        return Result.success();
    }

    /**
     * 编辑删除图片
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteImage")
    @ResponseBody
    public Result deleteBatch(Long id) {
        try{
            SystemBanner systemBanner = systemBannerService.queryByPK(id);
            systemBannerService.delete(systemBanner);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }



}
