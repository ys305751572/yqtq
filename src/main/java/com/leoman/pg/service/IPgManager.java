package com.leoman.pg.service;

import com.leoman.common.service.GenericManager;
import com.leoman.pg.entity.Pg;
import org.springframework.data.domain.Page;

/**
 * 病理 / 讲座
 * @author yesong
 *
 */
public interface IPgManager extends GenericManager<Pg> {

	public Page<Pg> findAll(Pg pg, Integer start, Integer length);
}
