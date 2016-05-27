package com.leoman.reserve.controller;

import com.leoman.common.controller.common.CommonController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
@RequestMapping(value = "admin/reserve")
public class ReserveController extends CommonController {

    @Autowired
    private ReserveService service;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "reserve/list";
    }


    /**
     * 查询列表
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param reserve
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Reserve reserve,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Reserve> page = service.findPage(reserve,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id,Model model) {

        Reserve reserve = service.getById(id);
//        if(admin.getContent() != null) {
//            admin.setContent(admin.getContent().replace("&lt","<").replace("&gt",">"));
//        }
        model.addAttribute("reserve",reserve);
        return "reserve/detail";
    }

}