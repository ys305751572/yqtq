package com.leoman.stadium.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.entity.StadiumUserWithdraw;
import com.leoman.stadium.service.StadiumUserWithdrawService;
import com.leoman.stadium.service.impl.StadiumUserWithdrawServiceImpl;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/16.
 */

@Controller
@RequestMapping(value = "/admin/stadiumUserWithdraw")
public class StadiumUserWithdrawController extends GenericEntityController<StadiumUserWithdraw,StadiumUserWithdraw,StadiumUserWithdrawServiceImpl> {

    @Autowired
    private StadiumUserWithdrawService stadiumUserWithdrawService;

    @RequestMapping(value = "/index")
    public String index(Model model, String details){
        if(StringUtils.isNotBlank(details) && "1".equals(details)){
            model.addAttribute("details",details);
        }
        return "/stadiumuserwithdraw/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, StadiumUserWithdraw stadiumUserWithdraw,StadiumUser username,String details){
        Page<StadiumUserWithdraw> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            stadiumUserWithdraw.setStadiumUser(username);
            Page = stadiumUserWithdrawService.findAll(details,stadiumUserWithdraw, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);

    }

    /**
     * 改变状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id,Integer status){
        StadiumUserWithdraw stadiumUserWithdraw = stadiumUserWithdrawService.queryByPK(id);
        try{
            stadiumUserWithdraw.setStatus(status);
            stadiumUserWithdrawService.save(stadiumUserWithdraw);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


}
