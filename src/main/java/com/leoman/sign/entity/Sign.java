package com.leoman.sign.entity;

import com.leoman.doctor.entity.Doctor;
import com.leoman.user.entity.AolUser;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/19.
 */
@Entity
@Table(name = "signing")
public class Sign{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AolUser aolUser;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "month")
    private Integer month;

    @Column(name = "sn")
    private String sn;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "is_sign")
    private Integer isSign;

    @Column(name = "create_date")
    private Long createDate;

    public AolUser getAolUser() {
        return aolUser;
    }

    public void setAolUser(AolUser aolUser) {
        this.aolUser = aolUser;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
