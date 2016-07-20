package com.leoman.message.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.message.entity.Message;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface MessageDao extends IBaseJpaRepository<Message>,JpaSpecificationExecutor<Message> {

    @Query("select a from Message a where a.type = ?1")
    public Message getByType(Integer type);
}

