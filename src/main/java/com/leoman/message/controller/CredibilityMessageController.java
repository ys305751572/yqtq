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
@RequestMapping(value = "admin/credibilityMessage")
public class CredibilityMessageController extends GenericEntityController<Message, Message, MessageServiceImpl> {

    @Autowired
    private MessageService messageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        Message message = messageService.getByType(1);
        model.addAttribute("credibilityMessage", message);

        return "credibilityMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            Message message = messageService.getByType(1);
            model.addAttribute("credibilityMessage", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "credibilityMessage/add";
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
            Message message = null;

            message = messageService.getByType(1);


            if (StringUtils.isNotEmpty(detail)) {
                message.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            messageService.save(message);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
