package com.leoman.information.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.information.entity.Information;
import com.leoman.information.service.InformationService;
import com.leoman.information.service.impl.InformationServiceImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Controller
@RequestMapping(value = "admin/information")
public class InformationController extends GenericEntityController<Information, Information, InformationServiceImpl> {

    @Autowired
    private InformationService informationService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "information/list";
    }

    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param introduction
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     String introduction,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);

            Page<Information> page = informationService.findPage(introduction, pageNum, length);

            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 详情
     *
     * @param informationId
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Long informationId, Model model) {

        Information information = informationService.findById(informationId);

        model.addAttribute("information", information);

        return "information/detail";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "information/add";
    }

    /**
     * 编辑
     *
     * @param informationId
     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Long informationId, Model model) {
        try {
            Information information = informationService.findById(informationId);
            model.addAttribute("information", information);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "information/add";
    }

    /**
     * 保存
     *
     * @param title
     * @param introduction
     * @param detail
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Integer save(Long id, String title, Integer type, String introduction, String description, String detail, MultipartRequest multipartRequest) {
        try {
            Information information = null;

            if (null == id) {
                information = new Information();
                information.setCreateDate(System.currentTimeMillis());
            } else {
                information = informationService.findById(id);
            }

            MultipartFile multipartFile = multipartRequest.getFile("imageFile");
            if (null != multipartFile) {
                FileBo fileBo = FileUtil.save(multipartFile);
                information.setAvater(fileBo.getPath());
            }

            information.setTitle(title);
            information.setIntroduction(introduction);

            if (type == 1) {
                information.setDescription(description);
                information.setType(1);
            }else if (type == 0) {
                if (StringUtils.isNotEmpty(detail)) {
                    information.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
                    information.setType(0);
                }
            }

            informationService.save(information);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
