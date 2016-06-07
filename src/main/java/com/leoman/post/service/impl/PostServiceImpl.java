package com.leoman.post.service.impl;

import com.leoman.post.dao.PostDao;
import com.leoman.post.entity.Post;
import com.leoman.post.service.PostService;
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
 * Created by Administrator on 2016/6/7 0007 下午 4:35.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public Page<Post> find(int pageNum, int pageSize) {
        return postDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Post> find(int pageNum) {
        return null;
    }

    @Override
    public Post getById(Long id) {
        return null;
    }

    @Override
    public Post deleteById(Long id) {
        return null;
    }

    @Override
    public Post create(Post post) {
        return postDao.save(post);
    }

    @Override
    public Post update(Post post) {
        return postDao.save(post);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public Page<Post> findPage(final Post post, int pagenum, int pagesize) {
        Specification<Post> spec = new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;

                if (post.getUser().getNickName() != null) {
                    Predicate pre = criteriaBuilder.like(root.get("user").get("nickname").as(String.class), "%" + post.getUser().getNickName() + "%");
                    list.add(pre);
                }

                if (post.getContent() != null) {
                    Predicate pre = criteriaBuilder.like(root.get("content").as(String.class), "%" + post.getContent() + "%");
                    list.add(pre);
                }
                if (post.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), post.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return postDao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }
}