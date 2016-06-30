package com.leoman.userfeedback.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.userfeedback.entity.UserFeedback;
import com.leoman.userfeedback.service.UserFeedbackService;
import com.leoman.userfeedback.service.impl.UserFeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * APP相关设置--反馈管理
 * Created by Administrator on 2016/6/30.
 */
@Controller
@RequestMapping(value = "/admin/userFeedback")
public class UserFeedbackController extends GenericEntityController<UserFeedback,UserFeedback,UserFeedbackServiceImpl>{

    @Autowired
    private UserFeedbackService userFeedbackService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/userfeedback/list";
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,UserFeedback userFeedback){
        Page<UserFeedback> page = null;
        try {
            int pagenum = getPageNum(start,length);
            page = userFeedbackService.findAll(userFeedback, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,page);
    }




}
