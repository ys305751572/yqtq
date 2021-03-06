package com.leoman.admin.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.security.entity.Role;
import com.leoman.user.entity.UserRole;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/8.
 */
@Entity
@Table(name = "t_admin")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Admin extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_date")
    private Long lastLoginDate;

    @Column(name = "status")
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Set<UserRole> roles;

    @Transient
    private String name;

    public String getName() {
        StringBuffer name = new StringBuffer();
        for(UserRole ur : roles){
           name.append(ur.getRole().getName()+",");
        }
        return name.toString().substring(0,name.length()-1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "username :" + this.username + "password:" + this.password;
    }
}