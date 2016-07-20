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
@RequestMapping(value = "admin/vipLevelMessage")
public class VipLevelMessageController extends GenericEntityController<Message, Message, MessageServiceImpl> {

    @Autowired
    private MessageService messageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        Message vipLevelMessage = messageService.getByType(6);

//        VipLevelMessage vipLevelMessage = vipLevelMessageService.findById(1L);
        model.addAttribute("vipLevelMessage", vipLevelMessage);

        return "vipLevelMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            Message vipLevelMessage = messageService.getByType(6);
            model.addAttribute("vipLevelMessage", vipLevelMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "vipLevelMessage/add";
    }

    /**
     * 保存
     *
     * @param detail
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Integer save(Long id, String detail) {
        try {
            Message vipLevelMessage = null;

            /*if (null == id) {
                vipLevelMessage = new VipLevelMessage();
                vipLevelMessage.setCreateDate(System.currentTimeMillis());
            } else {
                vipLevelMessage = vipLevelMessageService.queryByPK(1l);
            }*/

            vipLevelMessage = messageService.getByType(6);


            if (StringUtils.isNotEmpty(detail)) {
                vipLevelMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            messageService.save(vipLevelMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
