package com.leoman.security.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.security.entity.Module;
import com.leoman.security.service.ModuleService;
import com.leoman.security.service.impl.ModuleServiceImpl;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Module module,Long moduleParentId) {
        Module m = null;
        if(module.getId()!=null){
            m = moduleService.queryByPK(module.getId());
        }
        if(m!=null){
            module.setParent(m.getParent());
            module.setCreateDate(m.getCreateDate());
            module.setSorting(m.getSorting());
        }else {
            if(moduleParentId !=null){
                Module _m = moduleService.queryByPK(moduleParentId);
                module.setParent(_m);
                List<Module> list = moduleService.queryByProperty("parent",moduleParentId);
                long a = module.getParent().getSorting();
                long b = list.size()+1;
                module.setSorting(a+b);
            }else {
                moduleService.save(module);
                module.setSorting(Long.parseLong(module.getId() + "0"));
            }

        }

        moduleService.save(module);
        return Result.success();
    }



}
