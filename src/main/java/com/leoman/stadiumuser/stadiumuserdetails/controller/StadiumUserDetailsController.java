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
import com.leoman.utils.Md5Util;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/7/16.
 */
@Controller
@RequestMapping(value = "/stadium/details")
public class StadiumUserDetailsController extends GenericEntityController<StadiumUser,StadiumUser,StadiumUserServiceImpl>{

    @Autowired
    private StadiumUserService stadiumUserService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,Model model){
        StadiumUser stadiumUser = stadiumUserService.queryByPK(this.getStadiumUser(request).getId());
        model.addAttribute("stadiumUser",stadiumUser);
        Integer haveStadium = stadiumUserService.haveStadium(stadiumUser.getId());
        model.addAttribute("haveStadium",haveStadium);
        return "stadiumuserjsp/details/list";
    }

    /**
     * 编辑详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id,Model model){
        StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
        model.addAttribute("stadiumUser",stadiumUser);
        List<Province> province = provinceService.queryAll();
        model.addAttribute("province",province);
        List<City> city = cityService.queryAll();
        model.addAttribute("city",city);
        return "stadiumuserjsp/details/edit";
    }

    /**
     * 保存详情
     * @param stadiumUser
     * @param city
     * @param province
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(StadiumUser stadiumUser, City city, Province province){
        StadiumUser s = null;
        try{
            if(null != stadiumUser.getId()){
                s = stadiumUserService.queryByPK(stadiumUser.getId());
            }

            if(s!=null){
                stadiumUser.setUsername(s.getUsername());
                stadiumUser.setPassword(s.getPassword());
                stadiumUser.setStatus(s.getStatus());
                stadiumUser.setReserveMoney(s.getReserveMoney());
                stadiumUser.setWithdrawMoney(s.getWithdrawMoney());
                stadiumUser.setBalance(s.getBalance());
                stadiumUser.setCreateDate(s.getCreateDate());
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



    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/changePwd")
    public String changePwd(HttpServletRequest request,Model model){
        StadiumUser stadiumUser = stadiumUserService.queryByPK(this.getStadiumUser(request).getId());
        model.addAttribute("stadiumUser",stadiumUser);
        return "stadiumuserjsp/details/changepwd";
    }

    /**
     * 保存密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/savePwd")
    @ResponseBody
    public Result savePwd(String password,Long id){
        try{
            StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
            stadiumUser.setPassword(Md5Util.md5(password));
            stadiumUserService.update(stadiumUser);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    @RequestMapping(value = "/checkPwd")
    @ResponseBody
    public Result checkPwd(String password,HttpServletRequest request){
        Result result = new Result();
        StadiumUser stadiumUser = stadiumUserService.queryByPK(this.getStadiumUser(request).getId());
        if(stadiumUser.getPassword().equals(Md5Util.md5(password))){
            result.setStatus(true);
            return result;
        }
        result.setStatus(false);
        result.setMsg("原密码输入有误!  请重新输入!");
        return result;
    }

    /**
     * 关联城市
     * @param provinceId
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectCity")
    @ResponseBody
    public List<City> selectCity(Long provinceId,Model model){
        List<City> cities =  cityService.queryByProperty("provinceId",provinceId);
        return cities;
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
