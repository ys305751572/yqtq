package com.leoman.message.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.message.entity.Message;
import com.leoman.message.service.MessageService;
import com.leoman.message.service.impl.MessageServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Controller
@RequestMapping(value = "admin/girlServiceMessage")
public class GirlServiceMessageController extends GenericEntityController<Message, Message, MessageServiceImpl> {

    @Autowired
    private MessageService messageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        Message girlServiceMessage = messageService.getByType(2);
        model.addAttribute("girlServiceMessage", girlServiceMessage);

        return "girlServiceMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            Message girlServiceMessage = messageService.getByType(2);
            model.addAttribute("girlServiceMessage", girlServiceMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "girlServiceMessage/add";
    }

    /**
     * 保存
     *
     * @param detail
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Integer save(String detail) {
        try {
            Message girlServiceMessage = null;

            girlServiceMessage = messageService.getByType(2);


            if (StringUtils.isNotEmpty(detail)) {
                girlServiceMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            messageService.save(girlServiceMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
