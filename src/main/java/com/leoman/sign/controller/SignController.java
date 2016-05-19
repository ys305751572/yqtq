package com.leoman.sign.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.question.entity.vo.QuestionContainer;
import com.leoman.sign.dao.SignDao;
import com.leoman.sign.entity.Sign;
import com.leoman.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/19.
 */
@RequestMapping(value = "/admin/sign")
@Controller
public class SignController extends GenericEntityController<Sign, Sign, SignDao> {

    @Autowired
    private SignService service;

    @RequestMapping(value = "/index")
    public String index() {
        return "sign/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Integer draw, Integer start, Integer length) {
        Page<Sign> page = null;
        try {
            int pagenum = getPageNum(start, length);
            page = service.findPage(pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw, page);
    }
}
