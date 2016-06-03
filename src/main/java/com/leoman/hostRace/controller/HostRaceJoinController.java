package com.leoman.hostRace.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.hostRace.entity.HostRaceJoin;
import com.leoman.hostRace.service.HostRaceJoinService;
import com.leoman.hostRace.service.impl.HostRaceJoinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/3.
 */
@Controller
@RequestMapping(value = "/admin/hostRaceJoin")
public class HostRaceJoinController extends GenericEntityController<HostRaceJoin, HostRaceJoin, HostRaceJoinServiceImpl> {

    @Autowired
    private HostRaceJoinService hostRaceJoinService;

    @RequestMapping(value = "/index")
    public String index(Long id,Model model){
        model.addAttribute("id",id);
        return "/hostRaceJoin/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,HostRaceJoin hostRaceJoin){
        Page<HostRaceJoin> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Page = hostRaceJoinService.findAll(hostRaceJoin, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

}
