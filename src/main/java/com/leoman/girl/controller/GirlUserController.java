package com.leoman.girl.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlUser;
import com.leoman.girl.service.GirlUserService;
import com.leoman.girl.service.impl.GirlUserServiceImpl;
import com.leoman.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
@Controller
@RequestMapping(value = "/admin/girlUser")
public class GirlUserController extends GenericEntityController<GirlUser, GirlUser, GirlUserServiceImpl> {

    @Autowired
    private GirlUserService girlUserService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<City> city = cityService.queryAll();
        model.addAttribute("city",city);
        return "/girluser/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, GirlUser girlUser, Girl girl,User nickName,City cityId){
        Page<GirlUser> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            girl.setCity(cityId);
            girlUser.setGirl(girl);
            girlUser.setUser(nickName);
            Page = girlUserService.findAll(girlUser, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }
}
