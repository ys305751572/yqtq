package com.leoman.hostRace.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.hostRace.entity.HostRace;
import com.leoman.hostRace.service.HostRaceService;
import com.leoman.hostRace.service.impl.HostRaceServiceImpl;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.service.StadiumService;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/2.
 */

@Controller
@RequestMapping(value = "/admin/hostRace")
public class HostRaceController extends GenericEntityController<HostRace, HostRace, HostRaceServiceImpl> {
    @Autowired
    private HostRaceService hostRaceService;
    @Autowired
    private StadiumService stadiumService;


    @RequestMapping(value ="/index")
    public String index(){
        return "hostRace/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,HostRace hostRace){
        Page<HostRace> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Page = hostRaceService.findAll(hostRace, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }
    //详情
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            HostRace hostRace = hostRaceService.findById(id);
            model.addAttribute("hostRace", hostRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/hostRace/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            HostRace hostRace = hostRaceService.findById(id);
            if(hostRace == null) {
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
        return "/hostRace/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            HostRace hostRace = hostRaceService.findById(id);
            model.addAttribute("hostRace", hostRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/hostRace/add";
    }
    //保存
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(HostRace hostRace,String detail,Stadium stadium) {
        HostRace h = null;
        if (null != hostRace.getId()) {
            h = hostRaceService.queryByPK(hostRace.getId());
        }
        if (null != h) {
            hostRace.setStadium(h.getStadium());
            hostRace.setStatus(h.getStatus());
            hostRace.setMatchType(h.getMatchType());
            hostRace.setHrSet(h.getHrSet());
            hostRace.setCreateDate(h.getCreateDate());
        }
        if (detail != null) {
            hostRace.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
        }
        if(stadium != null){
            Stadium _stadium = stadiumService.queryByPK(stadium.getId());
            hostRace.setStadium(_stadium);
        }
        hostRaceService.save(hostRace);
        return Result.success();
    }

    //关闭
    @RequestMapping(value = "close")
    @ResponseBody
    public Result close(Long id) {
        HostRace hostRace = hostRaceService.findById(id);
        Integer status = hostRace.getStatus();
        try{
            if(status==0 || status==1){
                hostRace.setStatus(2);
                hostRaceService.update(hostRace);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }

        return Result.success();
    }

    //新增资讯
    @RequestMapping(value = "/information")
    public String information(Long id, Model model){
        try{
            HostRace hostRace = hostRaceService.findById(id);
            model.addAttribute("hostRace", hostRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/hostRace/information";
    }

}
