package com.leoman.girl.controller;

import com.leoman.city.dao.CityDao;
import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.girl.dao.GirlDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlImage;
import com.leoman.girl.service.GirlImageService;
import com.leoman.girl.service.GirlService;
import com.leoman.girl.service.impl.GirlServiceImpl;
import com.leoman.utils.Result;
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
@RequestMapping(value = "/admin/girl")
public class GirlController extends GenericEntityController<Girl, Girl, GirlServiceImpl> {

    @Autowired
    private GirlService girlService;
    @Autowired
    private CityService cityService;
    @Autowired
    private GirlImageService girlImageService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<City> city = cityService.queryAll();
        model.addAttribute("city",city);
        return "/girl/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Girl girl,City cityId){
        Page<Girl> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            girl.setCity(cityId);
            Page = girlService.findAll(girl, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

    //详情
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Girl girl = girlService.queryByPK(id);
            model.addAttribute("girl", girl);
            List<GirlImage> image = girlImageService.queryByProperty("girlId",id);
            model.addAttribute("image",image);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/girl/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            Girl girl = girlService.queryByPK(id);
            if(girl == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //新增
    @RequestMapping(value = "/add")
    public String add(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/girl/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
            Girl girl = girlService.queryByPK(id);
            model.addAttribute("girl", girl);
            List<GirlImage> image = girlImageService.queryByProperty("girlId",id);
            model.addAttribute("image",image);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/girl/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Girl girl,City city){
        Girl g = null;
        try{
            if(null != girl.getId()){
                g = girlService.queryByPK(girl.getId());
            }
            if(null != g){
                girl.setAge(g.getAge());
                girl.setHeight(g.getHeight());
                girl.setWeight(g.getWeight());
                girl.setStatus(g.getStatus());
            }

            if(city != null){
                City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
                girl.setCity(_city);
            }
            girlService.save(girl);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    @RequestMapping(value = "/status")
    @ResponseBody
    public Result close(Long id){
        Girl girl = girlService.queryByPK(id);
        Integer status = girl.getStatus();
        try{
            if(status == 0 || status == null) {
                girl.setStatus(1);
                girlService.save(girl);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
