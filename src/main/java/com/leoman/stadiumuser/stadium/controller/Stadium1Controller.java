package com.leoman.stadiumuser.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumSub;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumService;
import com.leoman.stadium.service.StadiumSubService;
import com.leoman.stadium.service.StadiumUserService;
import com.leoman.stadium.service.impl.StadiumServiceImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Controller
@RequestMapping(value = "/stadium/stadium")
public class Stadium1Controller extends GenericEntityController<Stadium, Stadium, StadiumServiceImpl> {

    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private StadiumUserService stadiumUserService;
    @Autowired
    private StadiumSubService stadiumSubService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;

    /**
     * 列表跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model){
        return "stadiumuserjsp/stadium/list";
    }

    /**
     * 地图
     * @return
     */
    @RequestMapping(value = "/map")
    public String map(){
        return "stadiumuserjsp/ibsamap/map";
    }

    /**
     * 列表显示
     * @param draw
     * @param start
     * @param length
     * @param stadium
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request,Integer draw, Integer start, Integer length,Stadium stadium){
        Page<Stadium> stadiumPage = null;
        try {
            int pagenum = getPageNum(start,length);
            stadium.setStadiumUserId(getStadiumUser(request).getId());
            stadiumPage = stadiumService.findAll(stadium, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,stadiumPage);
    }
    
    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model,Integer isEd){
        try{
            if(isEd!=null){
                model.addAttribute("isEd",isEd);
            }
            Stadium stadium = stadiumService.findById(id);
            model.addAttribute("stadium", stadium);
            List<StadiumUser> list = stadiumUserService.queryByProperty("id",stadium.getStadiumUserId());
            if(list.size()>0 && list!=null){
                StadiumUser stadiumUser = list.get(0);
                model.addAttribute("stadiumUser", stadiumUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadium/detail";
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
    /**
     * 新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            List<Province> province = provinceService.queryAll();
            model.addAttribute("province",province);
            if(id!=null){
                Stadium stadium = stadiumService.queryByPK(id);
                model.addAttribute("stadium", stadium);
                List<StadiumSub> stadiumSubs = stadiumSubService.queryByProperty("stadiumId",id);
                model.addAttribute("stadiumSubs", stadiumSubs);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadium/add";
    }

    /**
     * 保存私人球场
     * @param stadium
     * @param imageFile
     * @param detail
     * @param city
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(HttpServletRequest request,Stadium stadium, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,String detail, City city,Province province,String codes,String types,String prices){
        Stadium s = null;

        if(null != stadium.getId()){
            s = stadiumService.queryByPK(stadium.getId());
        }

        if(null != s){
            stadium.setAreaId(s.getAreaId());
            stadium.setStadiumUserId(s.getStadiumUserId());
            stadium.setCreateDate(s.getCreateDate());
            stadium.setAvater(s.getAvater());
            stadium.setType(s.getType());
            stadium.setStadiumUserId(s.getStadiumUserId());
        }else{
            stadium.setType(0);//保存都是私人球场
            stadium.setStadiumUserId(getStadiumUser(request).getId());
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
        if(province != null){
            Province _province = provinceService.queryByProperty("provinceId",province.getProvinceId()).get(0);
            stadium.setProvince(_province);
        }
        stadiumService.save(stadium);
        this.subSave(stadium.getId(),codes,types,prices);

        return Result.success();
    }

    /**
     * 新增球场场地
     * @param id 球场id
     */
    private void subSave(Long id,String code,String type,String price){
        String[] codes = JsonUtil.json2Obj(code, String[].class);
        Integer[] types = JsonUtil.json2Obj(type, Integer[].class);
        Double[] prices = JsonUtil.json2Obj(price, Double[].class);

        for(int i=0;i<codes.length;i++){
            StadiumSub sub = new StadiumSub();
            sub.setStadiumId(id);
                sub.setCode(codes[i]);
                sub.setType(types[i]);
                sub.setPrice(prices[i]);
                sub.setStatus(1);
            stadiumSubService.save(sub);

        }
    }


    /**
     * 保存球场场地
     * @return
     */
    @RequestMapping(value = "/editStadiumSub")
    @ResponseBody
    public Result stadiumSubSave(Stadium Stadium,String subCode,Integer subType,Double subPrice){
        try{
            StadiumSub sub = stadiumSubService.findSite(Stadium.getId(),subCode);
            if(sub!=null){
                sub.setCode(subCode);
                sub.setType(subType);
                sub.setPrice(subPrice);
                stadiumSubService.update(sub);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 球场场次
     * @param id
     * @return
     */
    @RequestMapping(value = "/stadiumSubFrom")
    @ResponseBody
    public List<StadiumSub> stadiumSubFrom(Long id){
        if(id!=null){
            List<StadiumSub> list = stadiumSubService.queryByProperty("stadiumId",id);
            return list;
        }else {
            return null;
        }
    }

    /**
     * 场地预约时间
     * @param id
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "/reserve")
    public String reserve(Long id,String code,Model model){
        StadiumSub sub = stadiumSubService.findSite(id,code);
        model.addAttribute("sub",sub);
        Stadium stadium = stadiumService.queryByPK(id);
        model.addAttribute("stadium",stadium.getName());
        return "stadiumuserjsp/stadium/reserve";
    }


    @RequestMapping(value = "/dayEdit")
    public String dayEdit(String action,String date,Model model){
        model.addAttribute("date",date);
        model.addAttribute("action",action);
        return "stadiumuserjsp/stadium/event";
    }

    /**
     * 参数设置页面
     * @return
     */
    @RequestMapping(value = "/settings")
    public String settings(){
        return "stadiumuserjsp/parametersettings/settings";
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
