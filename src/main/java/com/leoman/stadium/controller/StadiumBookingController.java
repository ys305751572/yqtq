package com.leoman.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.StadiumBooking;
import com.leoman.stadium.service.StadiumBookingService;
import com.leoman.stadium.service.impl.StadiumBookingServiceImpl;
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
    private CityService cityService;

    @RequestMapping(value ="/index")
    public String index(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "/stadiumbooking/list";
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,StadiumBooking stadiumBooking){
        Page<StadiumBooking> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Page = stadiumBookingService.findAll(stadiumBooking, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

}
