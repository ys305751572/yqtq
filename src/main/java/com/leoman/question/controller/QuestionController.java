package com.leoman.question.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.question.entity.vo.Question;
import com.leoman.question.entity.vo.QuestionCollection;
import com.leoman.question.entity.vo.QuestionContainer;
import com.leoman.question.service.IQuestionContainerManager;
import com.leoman.question.service.IQuestionManager;
import com.leoman.question.service.impl.QuestionManagerImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/admin/question")
@Controller
public class QuestionController extends GenericEntityController<QuestionContainer, QuestionContainer, QuestionManagerImpl> {

	// TODO
	public static final String QUESTION_LIST = "questionbank/list";
	public static final String QUESTION_EDIT = "questionbank/add";
	
	public static final String QUESTION_ADD = "/question/add";
	public static final String QUESTION_DETAIL = "/management/aol/question/questionDetail";
	
	@Autowired
	private IQuestionManager manager;
	
	@Autowired
	private IQuestionContainerManager qcManager;

	// ==========================================QuestionContainer===================================
	/**
	 * 题库列表
	 * @return
	 */
	@RequestMapping(value = "/qc/index", method = RequestMethod.GET)
	public String pageList() {
		return QUESTION_LIST;
	}
	
	@RequestMapping(value = "/qc/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAll(Integer draw,Integer start,Integer length,String title) {

		Page<QuestionContainer> questionContainerPage = null;
		try {
			QuestionContainer qc = new QuestionContainer();
			qc.setTitle(title);
			int pagenum = getPageNum(start,length);
			questionContainerPage = qcManager.findAll(qc,pagenum, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataTableFactory.fitting(draw,questionContainerPage);
	}
	
	/**
	 * 新增题库
	 * @param qc
	 * @return
	 */
	@RequestMapping(value = "/qc/save", method = RequestMethod.POST)
	public String createQuestionContainer(QuestionContainer qc,MultipartFile imageFile,HttpServletRequest request) {
		
		if(imageFile!=null&&imageFile.getSize()>0){
			try {
				FileBo file = FileUtil.save(imageFile);
				if(file != null && StringUtils.isNotBlank(file.getPath()))
					qc.setImage(file.getPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		qc.setCreateTime(new Date());
		qcManager.create(qc);
		return QUESTION_LIST;
	}

	@RequestMapping(value = "/qc/deleteBatch", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteBatchQuestionContainer(String ids) {
		String[] arrayId = JsonUtil.json2Obj(ids, String[].class);
		for (String id : arrayId) {
			qcManager.deleteByPK(id);
		}
		return Result.success();
	}

	@RequestMapping(value = "/qc/delete", method = RequestMethod.POST)
	public String deleteQuestionContainer(String id) {
		qcManager.deleteByPK(id);
		return QUESTION_LIST;
	}
	
	@RequestMapping(value = "/qc/edit", method = RequestMethod.GET)
	public String pageEdit(@RequestParam(value = "id" ,required = false) String id,Model model) {
		QuestionContainer qc = qcManager.queryByPK(id);
		model.addAttribute("qc", qc);
		return QUESTION_EDIT;
	}
	
	@RequestMapping(value = "/qc/add", method = RequestMethod.GET)
	public String pageAdd() {
		return QUESTION_EDIT;
	}
	
	/**
	 * 修改题库状态
	 * @param tid
	 */
	private void modifyQcStatus(String tid) {
		QuestionContainer qc = qcManager.queryByPK(tid);
		qc.setStatus(1);
		qcManager.save(qc);
	}
	
	// ========================================question===============================================
	
	@RequestMapping(value = "/q/add", method = RequestMethod.GET)
	public String pageQuestionAdd(String tid, Model model) {
		
		Map<String,Object> tidMap = new HashMap<String, Object>();
		tidMap.put("tid", tid);
		model.addAttribute("tidMap", tidMap);
		return QUESTION_ADD;
	} 
	
	@RequestMapping(value = "pageQuestionDetail", method = RequestMethod.GET)
	public String pageQuestionDetail(String tid,Model model) {
		List<Question> qList = manager.queryByProperty("tid", tid);
		model.addAttribute("list", qList);
		return QUESTION_DETAIL;
	}
	
	/**
	 * 保存题目s
	 * @return
	 */
	@RequestMapping(value = "/q/save", method = RequestMethod.POST)
	public String saveQuestions(QuestionCollection questions) {
		
		if(questions != null && questions.getQuestions() != null && questions.getQuestions().size() >0) {
			manager.saveQuestions(questions);
			modifyQcStatus(questions.getTid());
		}
		else {
			System.out.println("无效参数");
		}
		return QUESTION_LIST;
	}
}
