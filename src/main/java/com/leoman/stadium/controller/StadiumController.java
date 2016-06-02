package com.leoman.stadium.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.service.StadiumService;
import com.leoman.stadium.service.impl.StadiumServiceImpl;
import com.leoman.team.entity.TeamRace;
import com.leoman.user.entity.User;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Controller
@RequestMapping(value = "/admin/stadium")
public class StadiumController  extends GenericEntityController<Stadium, Stadium, StadiumServiceImpl> {

    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "/index")
    public String index(){
        return "stadium/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Stadium stadium){
        Page<Stadium> stadiumPage = null;
        try {
            int pagenum = getPageNum(start,length);
            stadiumPage = stadiumService.findAll(stadium, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,stadiumPage);
    }

    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Stadium stadium = stadiumService.findById(id);
            model.addAttribute("stadium", stadium);
            List<User> user = stadiumService.findByNickName(id);
            if(user!=null && user.size()>0){
                model.addAttribute("nickName", user.get(0).getNickName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadium/detail";
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

    @RequestMapping(value = "/add")
    public String add(){
        return "/stadium/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            Stadium stadium = stadiumService.findById(id);
            model.addAttribute("stadium", stadium);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/stadium/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Stadium stadium,String detail){
        Stadium s = null;
        if(null != stadium.getId()){
            s = stadiumService.queryByPK(stadium.getId());
        }
        if(null != s){
            stadium.setStadiumUserId(s.getStadiumUserId());
        }
        if (detail != null) {
            stadium.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
        }
        stadiumService.save(stadium);
        return Result.success();
    }
}
