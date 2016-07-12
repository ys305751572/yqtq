package com.leoman.security.entity;

import com.leoman.admin.entity.Admin;
import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 模块表
 * Created by Administrator on 2016/5/22.
 */
@Entity
@Table(name = "t_module")
public class Module extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "sorting")
    private Long sorting;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Module parent;

    public Admin getAdmin() {
        return admin;
    }

    public Long getSorting() {
        return sorting;
    }

    public void setSorting(Long sorting) {
        this.sorting = sorting;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
