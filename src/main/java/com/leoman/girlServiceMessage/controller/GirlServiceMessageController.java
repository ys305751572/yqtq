package com.leoman.girlServiceMessage.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.girlServiceMessage.entity.GirlServiceMessage;
import com.leoman.girlServiceMessage.service.GirlServiceMessageService;
import com.leoman.girlServiceMessage.service.impl.GirlServiceMessageServiceImpl;
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
public class GirlServiceMessageController extends GenericEntityController<GirlServiceMessage, GirlServiceMessage, GirlServiceMessageServiceImpl> {

    @Autowired
    private GirlServiceMessageService girlServiceMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        GirlServiceMessage girlServiceMessage = girlServiceMessageService.queryByPK(1L);
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
            GirlServiceMessage girlServiceMessage = girlServiceMessageService.queryByPK(1L);
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
            GirlServiceMessage girlServiceMessage = null;

            girlServiceMessage = girlServiceMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                girlServiceMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            girlServiceMessageService.save(girlServiceMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
