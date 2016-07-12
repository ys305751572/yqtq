package com.leoman.security.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色模块关联关系表
 * Created by Administrator on 2016/7/11.
 */
@Entity
@Table(name = "t_role_module")
public class RoleModule extends BaseEntity{

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "module_id")
    private Long moduleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
