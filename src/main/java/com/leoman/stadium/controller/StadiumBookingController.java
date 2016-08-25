package com.leoman.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.insurance.entity.Insurance;
import com.leoman.insurance.service.InsuranceService;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.reserve.service.impl.ReserveServiceImpl;
import com.leoman.stadium.entity.Stadium;
import com.leoman.user.entity.User;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.Result;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 约场地列表
 * Created by Administrator on 2016/6/16.
 */
@Controller
@RequestMapping(value = "/admin/stadiumBooking")
public class StadiumBookingController extends GenericEntityController<Reserve,Reserve,ReserveServiceImpl>{

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private InsuranceService insuranceService;

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
    public Object list(HttpServletRequest request, HttpServletResponse response, Integer draw, Integer start, Integer length, Reserve reserve, String details,City cityId,Province provinceId, String name,User nickName){
        int pageNum = getPageNum(start, length);
        Stadium stadium = new Stadium();
        stadium.setProvince(provinceId);
        stadium.setCity(cityId);
        stadium.setName(name);
        reserve.setUser(nickName);
        reserve.setStadium(stadium);
        Page<Reserve> page = reserveService.findPage(details,reserve,pageNum,length);
        return DataTableFactory.fitting(draw, page);
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
        Reserve reserve = null;
        try{
            if(id1 !=null){
                reserve = reserveService.queryByPK(id1);
                model.addAttribute("reserve", reserve);
            }else if(id != null){
                reserve = reserveService.queryByPK(id);
                model.addAttribute("reserve", reserve);
            }
            if(reserve != null){
                Insurance insurance = insuranceService.findInsurance(reserve.getUser().getId(),reserve.getId());
                model.addAttribute("insurance",insurance);
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
            Reserve reserve = reserveService.queryByPK(id);
            if(reserve == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/accident")
    @ResponseBody
    public Insurance accident(Long id,Integer num){
        Insurance insurance = null;
        try{
            if(id!=null){
                insurance = insuranceService.queryByPK(id);
            }
            if(insurance!=null){
                insurance.setNum(num);
                insuranceService.save(insurance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return insurance;
    }



}
