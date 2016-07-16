package com.leoman.stadiumuser.stadiumuserdetails.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumService;
import com.leoman.stadium.service.StadiumUserService;
import com.leoman.stadium.service.impl.StadiumUserServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * Created by Administrator on 2016/7/16.
 */
@Controller
@RequestMapping(value = "/stadiumUser/details")
public class StadiumUserDetailsController extends GenericEntityController<StadiumUser,StadiumUser,StadiumUserServiceImpl>{

    @Autowired
    private StadiumUserService stadiumUserService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        StadiumUser stadiumUser = (StadiumUser) session.getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        model.addAttribute("stadiumUser",stadiumUser);
        return "stadiumuserdetailes/list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id,Model model){
        StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
        model.addAttribute("stadiumUser",stadiumUser);
        return "stadiumuserdetailes/edit";
    }

    public Result save(StadiumUser stadiumUser, City city, Province province){
        StadiumUser s = null;
        try{
            if(null != stadiumUser.getId()){
                s = stadiumUserService.queryByPK(stadiumUser.getId());
            }

            if(s!=null){
                stadiumUser.setStatus(s.getStatus());
                stadiumUser.setReserveMoney(s.getReserveMoney());
                stadiumUser.setWithdrawMoney(s.getWithdrawMoney());
                stadiumUser.setBalance(s.getBalance());
                stadiumUser.setCreateDate(s.getCreateDate());
            }else {

            }

            if(city != null){
                City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
                stadiumUser.setCity(_city);
            }
            if(province != null){
                Province _province = provinceService.queryByProperty("provinceId",province.getProvinceId()).get(0);
                stadiumUser.setProvince(_province);
            }
            stadiumUserService.save(stadiumUser);
        }catch (RuntimeException e) {
            e.printStackTrace();
            return  Result.failure();
        }
        return Result.success();
    }

}
