package com.leoman.girl.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.girl.dao.GirlCommentDao;
import com.leoman.girl.entity.GirlComment;
import com.leoman.girl.service.GirlCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/8.
 */
@Controller
@RequestMapping(value ="/admin/girlComment")
public class GirlCommentController extends GenericEntityController<GirlComment,GirlComment,GirlCommentDao> {

    @Autowired
    private GirlCommentService girlCommentService;

    @RequestMapping(value = "/index")
    public String index(Long id, Model model){
        model.addAttribute("id",id);
        return "girlcomment/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,GirlComment girlComment){

        Page<GirlComment> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Page = girlCommentService.findAll(girlComment, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);

    }

}
