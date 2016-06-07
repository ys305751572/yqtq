package com.leoman.girl.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/3.
 */
@Entity
@Table(name = "t_girl")
public class Girl extends BaseEntity{
    //城市ID
    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;
    //宝贝名称
    @Column(name = "name")
    private String name;
    //价格 /小时
    @Column(name = "price")
    private Double price;
    //兴趣
    @Column(name = "interest")
    private String interest;
    //喜欢球队
    @Column(name = "favorite_team")
    private String favoriteTeam;
    //职业
    @Column(name = "profession")
    private String profession;
    //年龄
    @Column(name = "age")
    private Integer age;
    //身高
    @Column(name = "height")
    private Double height;
    //体重
    @Column(name = "weight")
    private Double weight;
    //签名
    @Column(name = "label")
    private String label;
    //能否预约
    @Column(name = "status")
    private Integer status;
    //图片
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "girl_id")
    private Set<GirlImage> images;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

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

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
