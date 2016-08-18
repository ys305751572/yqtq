package com.leoman.stadiumuser.stadium.controller;

import com.leoman.city.entity.Area;
import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.AreaService;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.sitemanage.entity.SiteManage;
import com.leoman.sitemanage.service.SiteManageService;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumSub;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumService;
import com.leoman.stadium.service.StadiumSubService;
import com.leoman.stadium.service.StadiumUserService;
import com.leoman.stadium.service.impl.StadiumServiceImpl;
import com.leoman.stadiumuser.stadium.vo.StartEndDate;
import com.leoman.stadiumuser.stadium.vo.TimeVo;
import com.leoman.utils.*;
import com.leoman.utils.DateUtils;
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
import java.util.*;

/**
 * Created by Administrator on 2016/5/31.
 */
@Controller
@RequestMapping(value = "/stadium/stadium")
public class Stadium1Controller extends GenericEntityController<Stadium, Stadium, StadiumServiceImpl> {

    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private StadiumUserService stadiumUserService;
    @Autowired
    private StadiumSubService stadiumSubService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private SiteManageService siteManageService;



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
            StadiumUser stadiumUser = stadiumUserService.queryByPK(stadium.getStadiumUserId());
            model.addAttribute("stadiumUser", stadiumUser);

            //天数设置 横轴
            List<TimeVo> timeList = new ArrayList<TimeVo>();
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int date = calendar.get(Calendar.DAY_OF_MONTH);

            List<Stadium> list1 = stadiumService.queryByProperty("stadiumUserId",stadium.getStadiumUserId());
            if(!list1.isEmpty() && list1.size()>0){
                for(int i=0;i<list1.get(0).getScheduledTime();i++){
                    int day = date + i;
                    TimeVo timeVo = new TimeVo();
                    timeVo.setYear(year);
                    timeVo.setMonth(month);
                    timeVo.setDay(day);
                    timeList.add(timeVo);
                }
            }else {
                for(int i=0;i<7;i++){
                    int day = date + i;
                    TimeVo timeVo = new TimeVo();
                    timeVo.setYear(year);
                    timeVo.setMonth(month);
                    timeVo.setDay(day);
                    timeList.add(timeVo);
                }
            }
            model.addAttribute("timeList",timeList);
            List<StadiumSub> stadiumSubList = stadiumSubService.queryByProperty("stadiumId",stadium.getId(),"code",true);
            model.addAttribute("stadiumSubList",stadiumSubList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadium/detail";
    }


    //场次AND时间
    @RequestMapping(value = "/siteList")
    @ResponseBody
    public List<StartEndDate> siteList(String time,Long stadiumId,Model model){
        List<StartEndDate> seDate = new ArrayList<StartEndDate>();
        try{
            Long t = DateUtils.stringToLong(time,"yyyy-MM-dd");
            List<StadiumSub> stadiumSubList = stadiumSubService.queryByProperty("stadiumId",stadiumId,"code",true);
            model.addAttribute("stadiumSubList",stadiumSubList);
            if(!stadiumSubList.isEmpty() && stadiumSubList.size()>0){
                for(StadiumSub _s : stadiumSubList){
                    List<SiteManage> siteManages = siteManageService.queryByProperty("siteId",_s.getId());
                    if(!siteManages.isEmpty() && siteManages.size()>0){
                        for(SiteManage siteManage : siteManages){
                            String sTime = DateUtils.longToString(siteManage.getStartDate(),"yyyy-MM-dd");
                            Long st = DateUtils.stringToLong(sTime,"yyyy-MM-dd");
                            if(st.equals(t)){
                                String start = DateUtils.longToString(siteManage.getStartDate(),"HH:mm");
                                String end = DateUtils.longToString(siteManage.getEndDate(),"HH:mm");
                                StartEndDate startEndDate = new StartEndDate();
                                startEndDate.setCode(_s.getCode());
                                startEndDate.setStart(start);
                                startEndDate.setEnd(end);
                                seDate.add(startEndDate);

                                System.out.println("---------------进---了----------------");
                                System.out.println("code:" + _s.getCode());
                                System.out.println("开始时间:" + start);
                                System.out.println("结束时间:" + end);
                            }
                        }
                    }
                }
            }
            model.addAttribute("seDate",seDate);
            System.out.println("seDate:" + seDate.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return seDate;
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
                Stadium stadium = stadiumService.findById(id);
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
    public Result save(HttpServletRequest request,Stadium stadium, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,Area area,String detail, City city,Province province,String codes,String types,String prices){
        Stadium s = null;

        if(null != stadium.getId()){
            s = stadiumService.queryByPK(stadium.getId());
        }

        if(null != s){
            stadium.setStadiumUserId(s.getStadiumUserId());
            stadium.setCreateDate(s.getCreateDate());
            stadium.setAvater(s.getAvater());
            stadium.setType(s.getType());
            stadium.setStadiumUserId(s.getStadiumUserId());
            stadium.setScheduledTime(s.getScheduledTime());
        }else{
            stadium.setType(0);//保存都是私人球场
            stadium.setStadiumUserId(getStadiumUser(request).getId());
            List<Stadium> list = stadiumService.queryByProperty("stadiumUserId",stadium.getStadiumUserId());
            if(!list.isEmpty() && list.size()>0){
                if(list.get(0).getScheduledTime() != null){
                    stadium.setScheduledTime(list.get(0).getScheduledTime());
                }
            }
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
        if(area != null){
            Area _area = areaService.queryByProperty("areaId",area.getAreaId()).get(0);
            stadium.setArea(_area);
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

    /**
     * 设置场次时间
     * @param start
     * @param end
     * @param id
     * @return
     */
    @RequestMapping(value = "/siteManageSave")
    @ResponseBody
    public Result siteManageSave(String start, String end, Long siteId, Long id){
        SiteManage siteManage = new SiteManage();
        SiteManage _s = null;

        try{
            if(id!=null){
                _s = siteManageService.queryByPK(id);
            }
            Long startDate = DateUtils.stringToLong(start, "yyyy-MM-dd HH:mm");
            Long endDate = DateUtils.stringToLong(end, "yyyy-MM-dd HH:mm");
            if(_s!=null){
                _s.setStartDate(startDate);
                _s.setEndDate(endDate);
                siteManageService.update(_s);
            }else {
                siteManage.setSiteId(siteId);
                siteManage.setStartDate(startDate);
                siteManage.setEndDate(endDate);
                siteManageService.save(siteManage);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.print("---场次时间保存错误---");
            return Result.failure();
        }
        return Result.success();
    }

    @RequestMapping(value = "findTimeList")
    @ResponseBody
    public List<SiteManage> findTimeList(Long id){
        List<SiteManage> siteManages = siteManageService.queryByProperty("siteId",id);
        return siteManages;
    }


    /**
     * 参数设置页面
     * @return
     */
    @RequestMapping(value = "/settings")
    public String settings(HttpServletRequest request,Model model){
        Long stadiumUserId = getStadiumUser(request).getId();
        List<Stadium> stadialist = stadiumService.queryByProperty("stadiumUserId",stadiumUserId);
        if(!stadialist.isEmpty() && stadialist.size()>0){
            model.addAttribute("scheduledTime",stadialist.get(0).getScheduledTime());
        }
        return "stadiumuserjsp/parametersettings/settings";
    }

    @RequestMapping(value = "/saveScheduledTime")
    @ResponseBody
    public Result saveScheduledTime(Integer scheduledTime,HttpServletRequest request){
        try{
            Long stadiumUserId = getStadiumUser(request).getId();
            List<Stadium> stadialist = stadiumService.queryByProperty("stadiumUserId",stadiumUserId);
            if(!stadialist.isEmpty() && stadialist.size()>0){
                for(Stadium _s : stadialist){
                    _s.setScheduledTime(scheduledTime);
                    stadiumService.save(_s);
                }
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }

        return Result.success();
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
