package com.leoman.question.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.question.dao.QuestionContainerDAO;
import com.leoman.question.entity.vo.QuestionContainer;
import com.leoman.question.service.IQuestionContainerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * 
 * @author yesong
 *
 */
@Service
public class QuestionContainerManagerImpl extends GenericManagerImpl<QuestionContainer, QuestionContainerDAO> implements IQuestionContainerManager {

	@Autowired
	private QuestionContainerDAO qcDao;
	
	@Autowired
	private EntityManagerFactory em;
	/**
	 * 保存题目
	 */

	@Override
	public Page<QuestionContainer> findAll(QuestionContainer qc, Integer currentPage, Integer pageSize) throws Exception {
		
		
		return qcDao.findAll(new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id"));
	}
	
	@Override
	public Specification<QuestionContainer> buildSpecification(final QuestionContainer qc) {
		return new Specification<QuestionContainer>() {
			@Override
			public Predicate toPredicate(Root<QuestionContainer> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				if(qc.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class), "%" + qc.getTitle() + "%"));
				}
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		};
	}
	
	@Override
	public void create(QuestionContainer qc) {
		qcDao.save(qc);
	}
}
