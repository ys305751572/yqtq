package com.leoman.stadiumuser.stadiumuserwithdraw.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.entity.StadiumUserWithdraw;
import com.leoman.stadium.service.StadiumUserWithdrawService;
import com.leoman.stadium.service.impl.StadiumUserWithdrawServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/7/20.
 */
@Controller
@RequestMapping(value = "/stadium/stadiumUserWithdraw")
public class StadiumUserWithdraw1Controller extends GenericEntityController<StadiumUserWithdraw,StadiumUserWithdraw,StadiumUserWithdrawServiceImpl> {

    @Autowired
    private StadiumUserWithdrawService stadiumUserWithdrawService;

    @RequestMapping(value = "/index")
    public String index(){
        return "stadiumuserjsp/stadiumuserwithdraw/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request,Integer draw, Integer start, Integer length, StadiumUserWithdraw stadiumUserWithdraw){
        Page<StadiumUserWithdraw> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            stadiumUserWithdraw.setStadiumUser(getStadiumUser(request));
            Page = stadiumUserWithdrawService.findAll(null,stadiumUserWithdraw, pagenum, length);
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
            StadiumUserWithdraw stadiumUserWithdraw = stadiumUserWithdrawService.queryByPK(id);
            model.addAttribute("stadiumUserWithdraw", stadiumUserWithdraw);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "stadiumuserjsp/stadiumuserwithdraw/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            StadiumUserWithdraw stadiumUserWithdraw = stadiumUserWithdrawService.queryByPK(id);
            if(stadiumUserWithdraw == null) {
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
     * 申请提现金额
     * @return
     */
    @RequestMapping(value = "/apply")
    public String apply(HttpServletRequest request,Model model){
        StadiumUser stadiumUser = getStadiumUser(request);
        model.addAttribute("stadiumUser",stadiumUser);
        return "stadiumuserjsp/stadiumuserwithdraw/apply";
    }

    @RequestMapping(value = "/applySave")
    @ResponseBody
    public Result applySave(Long id,Double withdrawMoney){

        StadiumUserWithdraw stadiumUserWithdraw = new StadiumUserWithdraw();
        StadiumUser stadiumUser = new StadiumUser();

        List<Integer> list = stadiumUserWithdrawService.findStatus(id);
        if(!list.isEmpty() && list.size()>0){
            for(Integer status : list){
                if(status==0){
                    return Result.failure();
                }
            }
        }else {
            stadiumUser.setId(id);
            stadiumUserWithdraw.setWithdrawMoney(withdrawMoney);
            stadiumUserWithdraw.setStadiumUser(stadiumUser);
            stadiumUserWithdraw.setStatus(0);
            stadiumUserWithdrawService.save(stadiumUserWithdraw);
            return Result.success();
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
