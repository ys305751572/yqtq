package com.leoman.stadium.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumUserService;
import com.leoman.stadium.service.impl.StadiumUserServiceImpl;
import com.leoman.utils.Md5Util;
import com.leoman.utils.Result;
import com.leoman.watchingrace.entity.WatchingRace;
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
@RequestMapping(value = "/admin/stadiumUser")
public class StadiumUserController extends GenericEntityController<StadiumUser,StadiumUser,StadiumUserServiceImpl> {

    @Autowired
    private StadiumUserService stadiumUserService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/stadiumuser/list";

    }

    /**
     * 列表
     * @param draw
     * @param start
     * @param length
     * @param stadiumUser
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, StadiumUser stadiumUser){
        Page<StadiumUser> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Page = stadiumUserService.findAll(stadiumUser, pagenum, length);
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
    public String detail(Long id, Model model){
        try{
            StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
            model.addAttribute("stadiumUser", stadiumUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadiumuser/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
            if(stadiumUser == null) {
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
     * 新增跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadiumuser/add";
    }

    /**
     * 新增
     * @param stadiumUser
     * @param city
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(StadiumUser stadiumUser,City city){
        Md5Util md5Util = new Md5Util();
        try{
            String pwd = md5Util.md5(stadiumUser.getPassword());
            stadiumUser.setPassword(pwd);
            stadiumUser.setStatus(0);
            stadiumUser.setReserveMoney(0.0);
            stadiumUser.setWithdrawMoney(0.0);
            stadiumUser.setBalance(0.0);
            if(city != null){
                City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
                stadiumUser.setCity(_city);
            }
            stadiumUserService.save(stadiumUser);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();

    }


    /**
     * 改变状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id){
        StadiumUser stadiumUser = stadiumUserService.queryByPK(id);
        Integer status = stadiumUser.getStatus();
        try{
            if(status == 0) {
                stadiumUser.setStatus(1);
                stadiumUserService.save(stadiumUser);
            }else {
                stadiumUser.setStatus(0);
                stadiumUserService.save(stadiumUser);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


}
