package com.leoman.pg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.pg.entity.Pg;
import com.leoman.pg.service.IPgManager;
import com.leoman.pg.service.impl.PgManagerImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * 病理controller
 * @author yesong
 *
 */
@RequestMapping("/admin/pg")
@Controller
public class PgController extends GenericEntityController<Pg, Pg, PgManagerImpl> {

	public static final String PG_LIST = "pg/list";
	public static final String PG_EDIT = "pg/add";
	public static final String PG_DETAIL = "pg/detail";
	
	@Autowired
	private IPgManager manager;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String pageList() {
		return PG_LIST;
	}
	
	/**
	 * 查询列表信息
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAll(Integer draw,Integer start, Integer length,String title) {
		Page<Pg> pgPage = null;
		try {
			Pg pg = new Pg();
			pg.setTitle(title);
			int pagenum = getPageNum(start,length);
			pgPage = manager.findAll(pg, pagenum, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataTableFactory.fitting(draw,pgPage);
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String pageAdd() {
		return PG_EDIT;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.GET)
	public String pageEdit(String id,Model model) {
		Pg pg = manager.queryByPK(id);
		pg.setContent(StringUtils.isBlank(pg.getContent()) ? "" : pg.getContent().replaceAll("&lt","<").replaceAll("&gt",">"));
		model.addAttribute("pg", pg);
		return PG_EDIT;
	} 
	
	/**
	 * 新增 或 编辑
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result saveOrEdit(Pg pg, MultipartFile imageFile,HttpServletRequest request) {
		Pg _pg = null;
		if(StringUtils.isNotBlank(pg.getId())) {
			_pg = manager.queryByPK(pg.getId());
		}
		
		if(imageFile!=null&&imageFile.getSize()>0){
			try {
				FileBo file = FileUtil.save(imageFile);
				if(StringUtils.isNotBlank(file.getPath())) {
					pg.setImage(file.getPath());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(_pg != null && StringUtils.isBlank(pg.getImage())) {
			pg.setImage(_pg.getImage());
		}
		pg.setCreateDate(new Date());
		manager.save(pg);
		return Result.success();
	}

	/**
	 * 删除记录
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteBatch(String ids) {
		String[] arrayId = JsonUtil.json2Obj(ids, String[].class);
		for (String id : arrayId) {
			manager.deleteByPK(id);
		}
		return Result.success("删除成功");
	}

	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(String id) {
		manager.deleteByPK(id);
		return Result.success("删除成功");
	}
	
	/**
	 * 详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String detail(String id, Model model) {
		Pg pg = manager.queryByPK(id);
		model.addAttribute("pg", pg);
		return PG_DETAIL;
	}
}
