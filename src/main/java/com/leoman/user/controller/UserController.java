package com.leoman.user.controller;

import com.leoman.common.controller.common.CommonController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.user.entity.User;
import com.leoman.user.service.UserService;
import com.leoman.utils.Result;
import com.leoman.utils.WebUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23.
 */
@Controller
@RequestMapping(value = "admin/user")
public class UserController extends CommonController {

    @Autowired
    private UserService service;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "user/list";
    }

    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param user
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer sortId,
                     Integer draw,
                     Integer start,
                     Integer length,
                     User user,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);
            Page<User> page = service.findPage(user, sortId, pageNum, length);
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
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        User user = service.queryByPK(id);
        model.addAttribute("user", user);
        return "user/detail";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id){
        Map<String,Object> map = new HashMap<String, Object>();
        User user = service.queryByPK(id);
        Integer status = user.getStatus();
        try{
            if(status == 0) {
                user.setStatus(1);
                service.update(user);
            }else {
                user.setStatus(0);
                service.update(user);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
