package com.leoman.insuranceMessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.insuranceMessage.entity.InsuranceMessage;
import com.leoman.insuranceMessage.service.InsuranceMessageService;
import com.leoman.insuranceMessage.service.impl.InsuranceMessageServiceImpl;
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
@RequestMapping(value = "admin/insuranceMessage")
public class InsuranceMessageController extends GenericEntityController<InsuranceMessage, InsuranceMessage, InsuranceMessageServiceImpl> {

    @Autowired
    private InsuranceMessageService insuranceMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        InsuranceMessage insuranceMessage = insuranceMessageService.findById(1L);
        model.addAttribute("insuranceMessage", insuranceMessage);

        return "insuranceMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            InsuranceMessage insuranceMessage = insuranceMessageService.queryByPK(1L);
            model.addAttribute("insuranceMessage", insuranceMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "insuranceMessage/add";
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
            InsuranceMessage insuranceMessage = null;

            insuranceMessage = insuranceMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                insuranceMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            insuranceMessageService.save(insuranceMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
