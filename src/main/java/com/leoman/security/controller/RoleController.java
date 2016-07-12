package com.leoman.security.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.security.entity.Module;
import com.leoman.security.entity.Role;
import com.leoman.security.entity.RoleModule;
import com.leoman.security.service.ModuleService;
import com.leoman.security.service.RoleModuleService;
import com.leoman.security.service.RoleService;
import com.leoman.security.service.impl.RoleServiceImpl;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * Created by Administrator on 2016/7/12.
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController extends GenericEntityController<Role,Role,RoleServiceImpl> {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private RoleModuleService roleModuleService;

    @RequestMapping(value = "/index")
    public String index(){
        return "role/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Role role){
        Page<Role> stadiumPage = null;
        try {
            int pagenum = getPageNum(start,length);
            stadiumPage = roleService.findAll(role, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,stadiumPage);
    }

    @RequestMapping(value = "/add")
    public String add(Long id, Model model){
        if(id!=null){
            Role role = roleService.queryByPK(id);
            model.addAttribute("role",role);
            List<RoleModule> list = roleModuleService.queryByProperty("roleId",id);
//            List<Long> roleModule = new ArrayList<>();
            StringBuffer roleModule = new StringBuffer();
            if(list.size()>0){
                for(RoleModule r : list){
                    roleModule.append(r.getModuleId()+ ",");
                }
                if(roleModule.length()>0){
                    model.addAttribute("roleModule",roleModule.toString().substring(0, roleModule.length() - 1));
                }else{
                    model.addAttribute("roleModule","");
                }
            }

        }
        List<Module> mainModuleList = moduleService.mainModule();
        model.addAttribute("mainModuleList",mainModuleList);
        List<Module> subModuleList = moduleService.subModule();
        model.addAttribute("subModuleList",subModuleList);
        return "role/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Role role, String ids) {
        Role r = null;
        Long[] moduleIds = JsonUtil.json2Obj(ids, Long[].class);
        try {
            if(role.getId()!=null){
                r = roleService.queryByPK(role.getId());
            }
            if(r!=null){
                role.setCreateDate(r.getCreateDate());
            }
            roleService.save(role);
            for(Long moduleId : moduleIds){
                RoleModule roleModule = new RoleModule();
                roleModule.setRoleId(role.getId());
                roleModule.setModuleId(moduleId);
                roleModuleService.save(roleModule);
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
