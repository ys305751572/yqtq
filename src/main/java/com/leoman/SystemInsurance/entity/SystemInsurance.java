package com.leoman.systemInsurance.entity;

import com.leoman.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/25.
 */
@Entity
@Table(name = "t_system_insurance")
public class SystemInsurance extends BaseEntity {
    //保险名称
    @Column(name = "name")
    private String name;
    //单价
    @Column(name = "price")
    private Double price;
    //事故保金
    @Column(name = "bj")
    private Double bj;
    //保险公司名称
    @Column(name = "company")
    private String company;
    //联系方式
    @Column(name = "mobile")
    private String mobile;
    //状态 0:有效 1:失效
    @Column(name = "status")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getBj() {
        return bj;
    }

    public void setBj(Double bj) {
        this.bj = bj;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
