package com.leoman.activity.service.impl;

import com.leoman.activity.dao.ActivityDao;
import com.leoman.activity.entity.Activity;
import com.leoman.activity.service.ActivityService;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.hostRace.entity.HostRace;
import com.leoman.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8 0008 下午 4:35.
 */
@Service
public class ActivityServiceImpl extends GenericManagerImpl<Activity,ActivityDao>implements ActivityService  {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public Activity findById(Long id) {
        return activityDao.findById(id);
    }

    @Override
    public Page<Activity> findPage(final String introduction, int pageNum, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Activity> page = activityDao.findAll(new Specification<Activity>() {
            @Override
            public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (introduction != null) {
                    Predicate pre = cb.like(root.get("introduction").as(String.class), "%" + introduction + "%");
                    predicateList.add(pre);
                }
                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }
}