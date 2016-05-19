package com.leoman.question.service.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.question.dao.QuestionDAO;
import com.leoman.question.entity.vo.Question;
import com.leoman.question.entity.vo.QuestionCollection;
import com.leoman.question.service.IQuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author yesong
 *
 */
@Service
public class QuestionManagerImpl extends GenericManagerImpl<Question, QuestionDAO> implements IQuestionManager {

	@Autowired
	private QuestionDAO dao;
	
	@Autowired
	private EntityManagerFactory em;
	/**
	 * 保存题目
	 */
	@Override
	@Transactional
	public void saveQuestions(QuestionCollection questions) {
		List<Question> qList = questions.getQuestions();
		for(int i = 0;i < qList.size();i++) {
			Question question = qList.get(i);
			if(question.getQuestion() != null) {
				question.setQno(i+1);
				question.setTid(questions.getTid());
				dao.save(question);
			}
		}
	}
}
