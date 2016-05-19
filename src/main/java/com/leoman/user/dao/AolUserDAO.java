package com.leoman.user.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.user.entity.AolUser;

public interface AolUserDAO extends IBaseJpaRepository<AolUser> {
	/**
	 * 根据帐号查找用户
	 * @param account 帐号
	 * @return
	 */
	public AolUser findByAccount(String account);
	
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return
	 */
	public AolUser findByMobile(String mobile);
	
	/**
	 * 根据邮箱查找用户
	 * @param email 邮箱
	 * @return
	 */
	public AolUser findByEmail(String email);

}
