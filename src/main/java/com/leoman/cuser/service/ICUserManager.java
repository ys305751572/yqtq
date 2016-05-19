package com.leoman.cuser.service;

import com.leoman.common.service.GenericManager;
import com.leoman.cuser.entity.CUser;
import org.springframework.data.domain.Page;
public interface ICUserManager extends GenericManager<CUser> {

	public Page<CUser> finaAll(CUser cuser, Integer currentPage, Integer pageSize);
}
