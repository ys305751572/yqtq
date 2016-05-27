package com.leoman.systemInsurance.entity;

import com.leoman.common.entity.BaseEntity;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/25.
 */
@Entity
@Table(name = "t_system_insurance")
public class SystemInsurance extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "bj")
    private Double bj;

    @Column(name = "company")
    private String company;

    @Column(name = "mobile")
    private String mobile;

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
