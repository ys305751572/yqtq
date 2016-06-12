package com.leoman.information.service;


import com.leoman.common.service.GenericManager;
import com.leoman.information.entity.Information;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/8 0008 下午 4:34.
 */
public interface InformationService extends GenericManager<Information> {

    public Page<Information> findPage(String introduction, int pageNum, int pageSize);

    public Information findById(Long id);
}