package com.leoman.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.service.StadiumService;
import com.leoman.stadium.service.impl.StadiumServiceImpl;
import com.leoman.team.entity.TeamRace;
import com.leoman.user.entity.User;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Controller
@RequestMapping(value = "/admin/stadium")
public class StadiumController extends GenericEntityController<Stadium, Stadium, StadiumServiceImpl> {

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "stadium/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Stadium stadium,City cityId){
        Page<Stadium> stadiumPage = null;
        try {
            int pagenum = getPageNum(start,length);
            stadium.setCity(cityId);
            stadiumPage = stadiumService.findAll(stadium, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,stadiumPage);
    }

    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Stadium stadium = stadiumService.findById(id);
            model.addAttribute("stadium", stadium);
            List<User> user = stadiumService.findByNickName(id);
            if(user!=null && user.size()>0){
                model.addAttribute("nickName", user.get(0).getNickName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadium/detail";
    }

    @RequestMapping(value = "/sfStadiumInfo")
    @ResponseBody
    public Result sfStadiumInfo(Long id, Model model) {
        try {
            String msg = "";
            Stadium stadium = stadiumService.findById(id);
            if(stadium == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadium/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            Stadium stadium = stadiumService.findById(id);
            model.addAttribute("stadium", stadium);
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadium/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Stadium stadium, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,String detail, City city){
        Stadium s = null;
        if(null != stadium.getId()){
            s = stadiumService.queryByPK(stadium.getId());
        }

        if(null != s){
            stadium.setAreaId(s.getAreaId());
            stadium.setStadiumUserId(s.getStadiumUserId());
            stadium.setType(s.getType());
            stadium.setAddress(s.getAddress());
            stadium.setRtSet(s.getRtSet());////预定总数
            stadium.setrSet(s.getrSet());//当前预定数
            stadium.setSsSet(s.getSsSet());//场地数
            stadium.setSsStatus(s.getSsStatus());//状态
            stadium.setCreateDate(s.getCreateDate());
        }
        if(imageFile!=null && imageFile.getSize()>0) {
            FileBo fileBo = null;
            try {
                fileBo = FileUtil.save(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                stadium.setAvater(fileBo.getPath());
            }
        }
        if (detail != null) {
            stadium.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
        }
        if(city != null){
            City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
            stadium.setCity(_city);
        }
        stadiumService.save(stadium);
        return Result.success();
    }
}
