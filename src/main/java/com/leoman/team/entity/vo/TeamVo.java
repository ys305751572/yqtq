package com.leoman.team.entity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
public class TeamVo {

    @Id
    private Long id;

    @Column(name = "name")
    private Long name;
    @Column(name = "city_id")
    private Long city_id;
    @Column(name = "num")
    private Integer num;
    @Column(name = "season")
    private Integer season;
    @Column(name = "create_date")
    private Long create_date;

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }
}
