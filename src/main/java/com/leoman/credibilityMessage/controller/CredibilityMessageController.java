package com.leoman.credibilityMessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.credibilityMessage.entity.CredibilityMessage;
import com.leoman.credibilityMessage.service.CredibilityMessageService;
import com.leoman.credibilityMessage.service.impl.CredibilityMessageServiceImpl;
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
public class CredibilityMessageController extends GenericEntityController<CredibilityMessage, CredibilityMessage, CredibilityMessageServiceImpl> {

    @Autowired
    private CredibilityMessageService credibilityMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        CredibilityMessage credibilityMessage = credibilityMessageService.queryByPK(1L);
        model.addAttribute("credibilityMessage", credibilityMessage);

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
            CredibilityMessage credibilityMessage = credibilityMessageService.queryByPK(1L);
            model.addAttribute("credibilityMessage", credibilityMessage);
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
            CredibilityMessage credibilityMessage = null;

            credibilityMessage = credibilityMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                credibilityMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            credibilityMessageService.save(credibilityMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
