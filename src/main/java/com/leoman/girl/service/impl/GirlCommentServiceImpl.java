package com.leoman.girl.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlCommentDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlComment;
import com.leoman.girl.service.GirlCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
@Service
public class GirlCommentServiceImpl extends GenericManagerImpl<GirlComment,GirlCommentDao> implements GirlCommentService{

    @Autowired
    private GirlCommentDao dao;
    @Override
    public Page<GirlComment> findAll(GirlComment girlComment, Integer currentPage, Integer pageSize) throws Exception {
        Specification<GirlComment> spec = buildSpecification(girlComment);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<GirlComment> buildSpecification(final GirlComment g) {
        return new Specification<GirlComment>() {
            @Override
            public Predicate toPredicate(Root<GirlComment> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(g.getId()!= null){
                    list.add(cb.equal(root.get("id").as(Long.class), g.getId() ));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}
