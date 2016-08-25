package com.leoman.admin.service.impl;

import com.leoman.admin.dao.AdminDao;
import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.entity.Role;
import com.leoman.security.service.RoleService;
import com.leoman.user.entity.UserRole;
import com.leoman.user.service.UserRoleService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Md5Util;
import com.leoman.utils.Result;
import com.leoman.utils.TestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class AdminServiceImpl extends GenericManagerImpl<Admin,AdminDao> implements AdminService {

    @Autowired
    private AdminDao dao;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Admin findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public Page<Admin> findAll(List roleIds,Admin admin, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Admin> spec = buildSpecification(roleIds,admin);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    @Transactional
    public Result saveAdmin(Admin admin,String ids){
        Admin a = null;
        Long[] roleIds = JsonUtil.json2Obj(ids, Long[].class);
        List<UserRole> list = null;
        List<Admin> admin_name =  queryByProperty("username",admin.getUsername());
        Result result = new Result();
        result.setStatus(false);
        result.setMsg("已有相同的账号名称!");
        try{
            if(admin.getId()!=null){
                a = queryByPK(admin.getId());
                list = userRoleService.queryByProperty("adminId",admin.getId());
            }
            if(a != null){
                if(admin_name!=null && admin_name.size()>0 && !admin_name.get(0).getId().equals(admin.getId())){
                    return result;
                }
                if(!a.getPassword().equals(admin.getPassword())){
                    String pwd = Md5Util.md5(admin.getPassword());
                    admin.setPassword(pwd);
                }
                admin.setCreateDate(a.getCreateDate());
                admin.setLastLoginDate(a.getLastLoginDate());
                admin.setStatus(a.getStatus());
            }else {
                if(admin_name!=null && admin_name.size()>0){
                    return result;
                }
                admin.setStatus(0);
                String pwd = Md5Util.md5(admin.getPassword());
                admin.setPassword(pwd);
            }
            if(list != null && list.size()>0){
                for(UserRole urList : list){
                    UserRole u = userRoleService.queryByPK(urList.getId());
                    userRoleService.delete(u);
                }
            }
            save(admin);
            for(Long roleId : roleIds){
                UserRole userRole = new UserRole();
                userRole.setAdminId(admin.getId());
                Role role = new Role();
                role.setId(roleId);
                userRole.setRole(role);
                userRoleService.save(userRole);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    public Specification<Admin> buildSpecification(final List roleIds,final Admin u) {
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(!u.getUsername().isEmpty()){
                    list.add(cb.like(root.get("username").as(String.class),"%"+u.getUsername()+"%"));
                }
                if(roleIds.size()>0 && !roleIds.isEmpty()) {
                    Iterator iterator = roleIds.iterator();
                    CriteriaBuilder.In in = cb.in(root.get("id"));
                    while (iterator.hasNext()) {
                        in.value(iterator.next());
                    }
                    list.add(in);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
