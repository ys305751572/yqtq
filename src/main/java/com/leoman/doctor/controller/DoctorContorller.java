package com.leoman.doctor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.doctor.entity.Doctor;
import com.leoman.doctor.service.IDoctorManager;
import com.leoman.doctor.service.impl.DoctorManagerImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 医师管理
 * @author yesong
 *
 */
@Controller
@RequestMapping("/admin/doctor/")
public class DoctorContorller extends GenericEntityController<Doctor, Doctor, DoctorManagerImpl> {

	private static final String DOCTOR_LIST = "doctor/list"; // 医师列表页面
	private static final String DOCTOR_ADD = "doctor/add"; // 医师新增页面
	private static final String DOCTOR_EDIT = "doctor/add"; // 医师编辑页面
	private static final String DOCTORD_DETAIL = "doctor/detail"; // 医师详情页面
	
	@Autowired
	private IDoctorManager iDoctorManager;
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String pageAdd() {
		return DOCTOR_ADD;
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String paegList() {
		return DOCTOR_LIST;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String pageEdit(String id,Model model) {
		
		try {
			Doctor doctor = iDoctorManager.findById(id);
			doctor.setDetail(doctor.getDetail() !=null ? doctor.getDetail().replaceAll("&lt", "<").replaceAll("&gt",">").trim() : "");
			model.addAttribute("doctor", doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DOCTOR_EDIT;
	} 
	
	/**
	 * 保存医师信息
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result modifyDoctor(Doctor doctor, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, HttpServletRequest request) {
		
		Doctor _d = null;
		if(StringUtils.isNotBlank(doctor.getId())) {
			_d = iDoctorManager.queryByPK(doctor.getId());
		}
		
		if(imageFile!=null && imageFile.getSize()>0) {
			FileBo fileBo = null;
			try {
				fileBo = FileUtil.save(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
				doctor.setHead(fileBo.getPath());
			}
		}
		if(_d != null && StringUtils.isBlank(doctor.getHead())) {
			doctor.setHead(_d.getHead());
		}
		iDoctorManager.save(doctor);
		return Result.success();
	}
	
	@RequestMapping(value = "sfDoctorInfo",method = RequestMethod.POST)
	@ResponseBody
	public Result sfDoctorInfo(String id, Model model,HttpServletResponse response) {
		try {
			String msg = "";
			Doctor doctor = iDoctorManager.findById(id);
			if(doctor == null) {
				msg = "无法显示";
				return Result.failure(msg);
			}
			return Result.success();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "detail",method = RequestMethod.GET)
	public String viewDoctorInfo(String id, Model model) {
		try {
			Doctor doctor = iDoctorManager.findById(id);
			model.addAttribute("doctor", doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DOCTORD_DETAIL;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(String id) {
		iDoctorManager.deleteByPK(id);
		return Result.success();
	}

	@RequestMapping(value = "list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAll(HttpServletRequest request, HttpServletResponse response,Integer draw, Integer start, Integer length, String name) {
		
		Page<Doctor> doctorPage = null;
		int pagenum = getPageNum(start,length);
		try {
			Doctor d = new Doctor();
			d.setName(name);
			doctorPage = iDoctorManager.findAll(d, pagenum,length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataTableFactory.fitting(draw,doctorPage);
	}
}
