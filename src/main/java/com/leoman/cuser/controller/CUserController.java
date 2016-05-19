package com.leoman.cuser.controller;

import java.util.HashMap;
import java.util.Map;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.cuser.entity.CUser;
import com.leoman.cuser.service.ICUserManager;
import com.leoman.cuser.service.impl.CUserManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户--子女端
 * @author yesong
 *
 */
@Controller
@RequestMapping("/admin/cuser/")
public class CUserController extends GenericEntityController<CUser, CUser, CUserManagerImpl> {

	@Autowired
	private ICUserManager manager;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String listPage() {
		return "cuser/list";
	}
	
	/**
	 * 子女用户列表
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAll(Integer draw,Integer start,Integer length,String nickname ) {
		
		Page<CUser> cuserPage = null;
		try {
			CUser d = new CUser();
			d.setNickname(nickname);
			int pagenum = getPageNum(start,length);
			cuserPage = manager.finaAll(d, pagenum, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataTableFactory.fitting(draw,cuserPage);
	}
}
