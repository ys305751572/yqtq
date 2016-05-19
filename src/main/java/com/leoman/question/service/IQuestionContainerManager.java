package com.leoman.question.service;

import com.leoman.common.service.GenericManager;
import com.leoman.question.entity.vo.QuestionContainer;
import org.springframework.data.domain.Page;


public interface IQuestionContainerManager extends GenericManager<QuestionContainer> {

	public Page<QuestionContainer> findAll(QuestionContainer qc, Integer currentPage, Integer pageSize) throws Exception ;
	
	public void create(QuestionContainer qc);
}
