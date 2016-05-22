package com.leoman.admin.entity;

import com.leoman.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/8.
 */
@Entity
@Table(name = "t_admin")
public class Admin extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_date")
    private Long lastLoginDate;

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
}