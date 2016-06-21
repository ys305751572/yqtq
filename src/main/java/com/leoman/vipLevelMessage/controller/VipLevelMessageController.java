package com.leoman.vipLevelMessage.controller;

import com.leoman.activity.entity.Activity;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.vipLevelMessage.entity.VipLevelMessage;
import com.leoman.vipLevelMessage.service.VipLevelMessageService;
import com.leoman.vipLevelMessage.service.impl.VipLevelMessageServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Controller
@RequestMapping(value = "admin/vipLevelMessage")
public class VipLevelMessageController extends GenericEntityController<VipLevelMessage, VipLevelMessage, VipLevelMessageServiceImpl> {

    @Autowired
    private VipLevelMessageService vipLevelMessageService;

    /**
     * 详情
     *
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        VipLevelMessage vipLevelMessage = vipLevelMessageService.queryByPK(1L);

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
            VipLevelMessage vipLevelMessage = vipLevelMessageService.queryByPK(1l);
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
            VipLevelMessage vipLevelMessage = null;

            /*if (null == id) {
                vipLevelMessage = new VipLevelMessage();
                vipLevelMessage.setCreateDate(System.currentTimeMillis());
            } else {
                vipLevelMessage = vipLevelMessageService.queryByPK(1l);
            }*/

            vipLevelMessage = vipLevelMessageService.queryByPK(1L);


            if (StringUtils.isNotEmpty(detail)) {
                vipLevelMessage.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            vipLevelMessageService.save(vipLevelMessage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
