package com.leoman.user.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.security.entity.Role;

import javax.persistence.*;

/**
 *
 * Created by Administrator on 2016/7/12.
 */
@Entity
@Table(name = "t_user_role")
public class UserRole extends BaseEntity{

    @Column(name = "admin_id")
    private Long adminId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
