package com.leoman.viplevemessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.viplevemessage.entity.VipLeveMessage;
import com.leoman.viplevemessage.service.VipLeveMessageService;
import com.leoman.viplevemessage.service.impl.VipLeveMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping(value = "/admin/vipLeveMessage")
public class VipLeveMessageController extends GenericEntityController<VipLeveMessage,VipLeveMessage,VipLeveMessageServiceImpl>{

    @Autowired
    private VipLeveMessageService vipLeveMessageService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<VipLeveMessage> list = vipLeveMessageService.queryAll();
        String content = "";
        if(list.size()>0 && list!=null){
             content = list.get(0).getContent();
        }
        model.addAttribute("content",content);
        return "/viplevemessage/list";
    }



}
