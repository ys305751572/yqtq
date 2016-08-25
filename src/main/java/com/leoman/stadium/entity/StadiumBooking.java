//package com.leoman.stadium.entity;
//
//import com.leoman.city.entity.City;
//import com.leoman.city.entity.Province;
//import com.leoman.common.entity.BaseEntity;
//import com.leoman.user.entity.User;
//
//import javax.persistence.*;
//
///** 预约场地列表
// * Created by Administrator on 2016/6/16.
// */
//@Entity
//@Table(name = "t_stadium_booking")
//public class StadiumBooking extends BaseEntity{
//    //省份ID
//    @ManyToOne
//    @JoinColumn(name = "province_id",referencedColumnName = "province_id")
//    private Province province;
//    //城市ID
//    @ManyToOne
//    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
//    private City city;
//    //球场ID
//    @ManyToOne
//    @JoinColumn(name = "stadium_id")
//    private Stadium stadium;
//    //场地ID
//    @ManyToOne
//    @JoinColumn(name = "stadium_sub_id")
//    private StadiumSub stadiumSub;
//    //订购者ID
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    //预定时长
//    @Column(name = "book_time")
//    private Integer bookTime;
//    //开始时间
//    @Column(name ="start_date")
//    private Long startDate;
//    //应付金额
//    @Column(name = "price")
//    private Double price;
//    //预定类型 0:散客 1:其他
//    @Column(name = "type")
//    private Integer type;
//    //状态 0:未使用 1:已使用 2:已退款
//    @Column(name = "status")
//    private Integer status;
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public Stadium getStadium() {
//        return stadium;
//    }
//
//    public void setStadium(Stadium stadium) {
//        this.stadium = stadium;
//    }
//
//    public StadiumSub getStadiumSub() {
//        return stadiumSub;
//    }
//
//    public void setStadiumSub(StadiumSub stadiumSub) {
//        this.stadiumSub = stadiumSub;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Integer getBookTime() {
//        return bookTime;
//    }
//
//    public void setBookTime(Integer bookTime) {
//        this.bookTime = bookTime;
//    }
//
//    public Long getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Long startDate) {
//        this.startDate = startDate;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Province getProvince() {
//        return province;
//    }
//
//    public void setProvince(Province province) {
//        this.province = province;
//    }
//}
