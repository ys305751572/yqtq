package com.leoman.pg2.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.pg2.dao.PgDAO2;
import com.leoman.pg2.entity.Pg2;
import com.leoman.pg2.service.IPgManager2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class PgManagerImpl2 extends GenericManagerImpl<Pg2, PgDAO2> implements IPgManager2 {

	@Autowired
	private PgDAO2 dao;
	
	@Autowired
	private EntityManagerFactory em;

	@Override
	public Page<Pg2> findAll(Pg2 pg, Integer start, Integer length) {
		Specification<Pg2> spec = buildSpecification(pg);
		return dao.findAll(spec,new PageRequest(start - 1, length, Sort.Direction.DESC, "id"));
	}

	@Override
	public Specification<Pg2> buildSpecification(final Pg2 pg) {
		return new Specification<Pg2>() {
			@Override
			public Predicate toPredicate(Root<Pg2> root, CriteriaQuery<?> cq,
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
