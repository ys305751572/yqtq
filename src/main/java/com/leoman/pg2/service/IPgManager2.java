package com.leoman.pg2.service;

import com.leoman.common.service.GenericManager;
import com.leoman.pg2.entity.Pg2;
import org.springframework.data.domain.Page;

/**
 * 病理 / 讲座
 * @author yesong
 *
 */
public interface IPgManager2 extends GenericManager<Pg2> {

	public Page<Pg2> findAll(Pg2 pg, Integer start, Integer length);
}
