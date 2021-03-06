package com.leoman.stadiumuser.stadiumbooking.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.reserve.service.impl.ReserveServiceImpl;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
@Controller
@RequestMapping(value = "/stadium/stadiumBooking")
public class StadiumBooking1Controller extends GenericEntityController<Reserve,Reserve,ReserveServiceImpl>{

    @Autowired
    private StadiumService stadiumService;
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
            if(StringUtils.isNotBlank(details)){
                model.addAttribute("details",details);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadiumbooking/list";
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request,Integer draw, Integer start, Integer length, String details){
        Page<Reserve> Page = null;
        Reserve reserve = new Reserve();
        try {
            int pageNum = getPageNum(start, length);
            Stadium stadium = new Stadium();
            stadium.setStadiumUserId(getStadiumUser(request).getId());
            reserve.setStadium(stadium);
            Page = reserveService.findPage(details,reserve,pageNum,length);
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
        Reserve reserve = null;
        try{
            if(id1 !=null){
                reserve = reserveService.queryByPK(id1);
                model.addAttribute("reserve", reserve);
            }else if(id !=null){
                reserve = reserveService.queryByPK(id);
                model.addAttribute("reserve", reserve);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadiumbooking/detail";
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
