package com.leoman.security.controller;

import com.leoman.admin.entity.Admin;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.core.Constant;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.security.entity.Module;
import com.leoman.security.service.ModuleService;
import com.leoman.security.service.impl.ModuleServiceImpl;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23.
 */
@Controller
@RequestMapping(value = "/admin/module")
public class ModuleController extends GenericEntityController<Module,Module,ModuleServiceImpl>{

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/index")
    public String index() {
        return "module/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String,Object> list(Integer draw, Integer start, Integer length) {

        Integer pagenum = getPageNum(start,length);
        Page<Module> page = moduleService.findPage(pagenum,length);
        return DataTableFactory.fitting(draw,page);
    }

    /**
     * 新增编辑
     * @param id
     * @param model
     * @param isChild
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Long id, Model model,String isChild) {
        if(id != null){
            Module module = moduleService.queryByPK(id);
            model.addAttribute("module",module);
            if(StringUtils.isBlank(isChild)){
                if(module.getParent()==null){
                    model.addAttribute("isParent",1);
                }
                model.addAttribute("isEdit",1);
            }
            if( "1".equals(isChild)){
                model.addAttribute("isChild",isChild);
            }
        }
        return "module/add";
    }

    /**
     *判断新增子模块
     * @param id
     * @return
     */
    @RequestMapping(value = "/addChild")//, method = RequestMethod.POST
    @ResponseBody
    public Result addChild(Long id) {
        Module m = moduleService.queryByPK(id);
        if(m.getParent()==null){
            return Result.success();
        }else{
            return Result.failure();
        }
    }

    /**
     * 保存模块
     * @param request
     * @param module
     * @param moduleParentId
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(HttpServletRequest request,Module module, Long moduleParentId) {
        Module m = null;
        List<Module> module_name =  moduleService.queryByProperty("name",module.getName());
        Result result = new Result();
        result.setStatus(false);
        result.setMsg("已有相同的模块!");
        if(module.getId()!=null){
            m = moduleService.queryByPK(module.getId());
        }
        if(m!=null){
            if(module_name!=null && module_name.size()>0 && !module_name.get(0).getId().equals(module.getId())){
                return result;
            }
            module.setParent(m.getParent());
            module.setCreateDate(m.getCreateDate());
            module.setSorting(m.getSorting());
        }else {
            if(module_name!=null && module_name.size()>0){
                return result;
            }
            if(moduleParentId !=null){
                Module _m = moduleService.queryByPK(moduleParentId);
                module.setParent(_m);
                List<Module> list = moduleService.queryByProperty("parent",moduleParentId);
                long a = module.getParent().getSorting();
                long b = list.size()+1;
                module.setSorting(a+b);
            }else {
                module.setSorting(Long.parseLong(module.getId() + "0"));
            }

        }
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        module.setAdmin(admin);
        moduleService.save(module);
        return Result.success();
    }



}
