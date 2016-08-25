package com.leoman.admin.controller;

import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.admin.service.impl.AdminServiceImpl;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.security.entity.Role;
import com.leoman.security.service.RoleService;
import com.leoman.user.entity.UserRole;
import com.leoman.user.service.UserRoleService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Md5Util;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping(value = "/admin/admin")
public class AdminController extends GenericEntityController<Admin,Admin,AdminServiceImpl> {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<Role> role = roleService.queryAll();
        model.addAttribute("role",role);
        return "/admin/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Admin admin,Long roleId){
        Page<Admin> page = null;
        List roleIds = new ArrayList();
        try {
            int pagenum = getPageNum(start,length);
            if(roleId!=null){
                List<UserRole> list = userRoleService.queryByProperty("role",roleId);
                if(list!=null && list.size()>0){
                    for(UserRole ur : list){
                        roleIds.add(ur.getAdminId());
                    }
                }
            }
            page = adminService.findAll(roleIds,admin, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,page);
    }

    @RequestMapping(value = "/add")
    public String edit(Long id, Model model){
        try {
            if(id !=null){
                Admin admin = adminService.queryByPK(id);
                model.addAttribute("admin",admin);
                List<UserRole> list = userRoleService.queryByProperty("adminId",id);
                StringBuffer userRole = new StringBuffer();
                if(list!=null && list.size()>0){
                    for(UserRole ur : list){
                        userRole.append(ur.getRole().getId()+ ",");
                    }
                }
                if(userRole.length()>0){
                    model.addAttribute("userRole",userRole.toString().substring(0, userRole.length() - 1));
                }else{
                    model.addAttribute("userRole","");
                }
             }
            List<Role> role = roleService.queryAll();
            model.addAttribute("role",role);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "/admin/add";
     }

    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id,Integer status){
            Admin admin = adminService.queryByPK(id);
            try{
                admin.setStatus(status);
                adminService.update(admin);
            }catch (RuntimeException e){
                e.printStackTrace();
                return Result.failure();
            }
            return Result.success();
        }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Admin admin,String ids){
        return adminService.saveAdmin(admin,ids);
    }

}
