package com.leoman.user.service;

import com.leoman.common.service.ICommonService;
import com.leoman.user.entity.User;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface UserService extends ICommonService<User> {

    public Page<User> findPage(User user, Integer sortId, int pagenum, int pagesize);

}
