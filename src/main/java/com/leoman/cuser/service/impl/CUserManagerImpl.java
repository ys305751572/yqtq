package com.leoman.cuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.cuser.dao.CUserDAO;
import com.leoman.cuser.entity.CUser;
import com.leoman.cuser.service.ICUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CUserManagerImpl extends GenericManagerImpl<CUser, CUserDAO> implements ICUserManager {

	@Autowired
	private CUserDAO dao;
	
	@Autowired
	private EntityManagerFactory em;

	@Override
	public Page<CUser> finaAll(CUser cuser,Integer currentPage, Integer pageSize) {
		Specification<CUser> spec = buildSpecification(cuser);
		return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
	}
	
	@Override
	public Specification<CUser> buildSpecification(final CUser d) {
		return new Specification<CUser>() {
			@Override
			public Predicate toPredicate(Root<CUser> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				
				if(d.getNickname() != null) {
					list.add(cb.like(root.get("nickname").as(String.class), "%" + d.getNickname() + "%"));
				}
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		};
	}
}
