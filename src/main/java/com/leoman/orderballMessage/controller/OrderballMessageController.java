package com.leoman.orderballMessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.orderballMessage.entity.OrderballMessage;
import com.leoman.orderballMessage.service.OrderballMessageService;
import com.leoman.orderballMessage.service.impl.OrderballMessageServiceImpl;
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
@RequestMapping(value = "admin/orderballMessage")
public class OrderballMessageController extends GenericEntityController<OrderballMessage, OrderballMessage, OrderballMessageServiceImpl> {

    @Autowired
    private OrderballMessageService orderballMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        OrderballMessage orderballMessage = orderballMessageService.queryByPK(1L);

        model.addAttribute("orderballMessage", orderballMessage);

        return "orderballMessage/detail";
    }

    /**
     * 编辑
     *

     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model) {
        try {
            OrderballMessage orderballMessage = orderballMessageService.queryByPK(1L);
            model.addAttribute("orderballMessage", orderballMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "orderballMessage/add";
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
            OrderballMessage orderballMessage = null;

            orderballMessage = orderballMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                orderballMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            orderballMessageService.save(orderballMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
