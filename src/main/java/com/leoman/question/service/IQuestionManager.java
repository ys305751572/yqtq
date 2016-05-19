package com.leoman.question.service;

import com.leoman.common.service.GenericManager;
import com.leoman.question.entity.vo.Question;
import com.leoman.question.entity.vo.QuestionCollection;

public interface IQuestionManager extends GenericManager<Question> {

	public void saveQuestions(QuestionCollection questions);
}
