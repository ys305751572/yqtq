package com.leoman.pg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.pg.dao.PgDAO;
import com.leoman.pg.entity.Pg;
import com.leoman.pg.service.IPgManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class PgManagerImpl extends GenericManagerImpl<Pg, PgDAO> implements IPgManager {

	@Autowired
	private PgDAO dao;
	
	@Autowired
	private EntityManagerFactory em;

	@Override
	public Page<Pg> findAll(Pg pg, Integer start, Integer length) {
		Specification<Pg> spec = buildSpecification(pg);
		return dao.findAll(spec,new PageRequest(start - 1, length, Sort.Direction.DESC, "id"));
	}

	@Override
	public Specification<Pg> buildSpecification(final Pg pg) {
		return new Specification<Pg>() {
			@Override
			public Predicate toPredicate(Root<Pg> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				if(StringUtils.isNotBlank(pg.getTitle())) {
					list.add(cb.like(root.get("title").as(String.class), "%" + pg.getTitle() + "%"));
				}
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		};
	}
}
