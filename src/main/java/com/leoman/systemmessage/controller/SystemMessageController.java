package com.leoman.systemmessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.systemmessage.entity.SystemMessage;
import com.leoman.systemmessage.service.SystemMessageService;
import com.leoman.systemmessage.service.impl.SystemMessageServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * APP相关设置--系统消息列表
 * Created by Administrator on 2016/6/30.
 */
@Controller
@RequestMapping(value = "/admin/systemMessage")
public class SystemMessageController extends GenericEntityController<SystemMessage,SystemMessage,SystemMessageServiceImpl>{


    @Autowired
    private SystemMessageService systemMessageService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/systemmessage/list";
    }

    /**
     * 列表
     * @param draw
     * @param start
     * @param length
     * @param systemMessage
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,SystemMessage systemMessage){
        Page<SystemMessage> page = null;
        try {
            int pagenum = getPageNum(start,length);
            page = systemMessageService.findAll(systemMessage, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,page);
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(){
        return "/systemmessage/add";
    }

    /**
     * 用户选择
     * @return
     */
    @RequestMapping(value = "/userSelect")
    public String userSelect(){
        return "/systemmessage/userlist";
    }

    /**
     * 新增系统消息
     * @param systemMessage
     * @param detail
     * @param userType
     * @param toUserId
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(SystemMessage systemMessage,String detail,Integer userType,Long toUserId) {
        if(userType == 0){
            systemMessage.setToUserId((long)0);
        }else {
            systemMessage.setToUserId(toUserId);
        }
        if (detail != null) {
            systemMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
        }
        systemMessageService.save(systemMessage);
        return Result.success();
    }

}
