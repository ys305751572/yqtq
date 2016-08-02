package com.leoman.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumBooking;
import com.leoman.stadium.service.StadiumBookingService;
import com.leoman.stadium.service.impl.StadiumBookingServiceImpl;
import com.leoman.user.entity.User;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
@Controller
@RequestMapping(value = "/admin/stadiumBooking")
public class StadiumBookingController extends GenericEntityController<StadiumBooking,StadiumBooking,StadiumBookingServiceImpl>{

    @Autowired
    private StadiumBookingService stadiumBookingService;
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value ="/index")
    public String index(Model model,String details){
        try{
            List<Province> province = provinceService.queryAll();
            model.addAttribute("province",province);
            if(StringUtils.isNotBlank(details) && "1".equals(details)){
                model.addAttribute("details",details);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "/stadiumbooking/list";
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, StadiumBooking stadiumBooking, City cityId,Province provinceId, Stadium name,User nickName,String details){
        Page<StadiumBooking> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            stadiumBooking.setProvince(provinceId);
            stadiumBooking.setCity(cityId);
            stadiumBooking.setStadium(name);
            stadiumBooking.setUser(nickName);
            Page = stadiumBookingService.findAll(details,stadiumBooking, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

    /**
     * 跳转详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model,Long stadiumId,Long userId,Long startDate){
        Long id1 =reserveService.findStadiumBookingId(stadiumId,userId,startDate);
        try{
            if(id1 !=null){
                StadiumBooking stadiumBooking = stadiumBookingService.queryByPK(id1);
                Stadium _s = stadiumBooking.getStadium();
                _s.setAvater(StringUtils.isNotBlank(stadiumBooking.getStadium().getAvater()) ? ConfigUtil.getString("upload.url")+stadiumBooking.getStadium().getAvater() : "");
                stadiumBooking.setStadium(_s);
                model.addAttribute("stadiumBooking", stadiumBooking);
            }else if(id !=null){
                StadiumBooking stadiumBooking = stadiumBookingService.queryByPK(id);
                Stadium _s = stadiumBooking.getStadium();
                _s.setAvater(StringUtils.isNotBlank(stadiumBooking.getStadium().getAvater()) ? ConfigUtil.getString("upload.url")+stadiumBooking.getStadium().getAvater() : "");
                stadiumBooking.setStadium(_s);
                model.addAttribute("stadiumBooking", stadiumBooking);
            }else {
                return "/stadiumbooking/detail";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadiumbooking/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            StadiumBooking stadiumBooking = stadiumBookingService.queryByPK(id);
            if(stadiumBooking == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
