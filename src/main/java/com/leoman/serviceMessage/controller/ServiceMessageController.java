package com.leoman.serviceMessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.serviceMessage.entity.ServiceMessage;
import com.leoman.serviceMessage.service.ServiceMessageService;
import com.leoman.serviceMessage.service.impl.ServiceMessageServiceImpl;
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
@RequestMapping(value = "admin/serviceMessage")
public class ServiceMessageController extends GenericEntityController<ServiceMessage, ServiceMessage, ServiceMessageServiceImpl> {

    @Autowired
    private ServiceMessageService serviceMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        ServiceMessage serviceMessage = serviceMessageService.queryByPK(1L);
        model.addAttribute("serviceMessage", serviceMessage);

        return "serviceMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            ServiceMessage serviceMessage = serviceMessageService.queryByPK(1L);
            model.addAttribute("serviceMessage", serviceMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "serviceMessage/add";
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
            ServiceMessage serviceMessage = null;

            serviceMessage = serviceMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                serviceMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            serviceMessageService.save(serviceMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
