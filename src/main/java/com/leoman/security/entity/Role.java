package com.leoman.security.entity;

import com.leoman.admin.entity.Admin;
import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.UserRole;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色表
 * Created by Administrator on 2016/5/22.
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Transient
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
