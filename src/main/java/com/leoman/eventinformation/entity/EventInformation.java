package com.leoman.eventinformation.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.hostRace.entity.HostRace;

import javax.persistence.*;

/**
 * 赛事资讯表
 * Created by Administrator on 2016/6/27.
 */
@Entity
@Table(name = "t_event_information")
public class EventInformation extends BaseEntity{
    @Column(name = "race_id")
    //赛事id
    private Long raceId;

    //资讯内容
    @Column(name = "content")
    private String content;

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


