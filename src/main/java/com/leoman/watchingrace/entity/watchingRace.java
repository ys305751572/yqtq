package com.leoman.watchingrace.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/8.
 */
@Entity
@Table(name = "t_watching_race")
public class WatchingRace extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    @Column(name = "avater")
    private String avater;

    @Column(name = "invitation")
    private Integer invitation;

    @Column(name = "description")
    private String description;

    public Integer getInvitation() {
        return invitation;
    }

    public void setInvitation(Integer invitation) {
        this.invitation = invitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
