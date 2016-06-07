package com.leoman.post.service.impl;

import com.leoman.post.dao.PostCommentDao;
import com.leoman.post.entity.PostComment;
import com.leoman.post.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 下午 5:01.
 */
@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Autowired
    private PostCommentDao postCommentDao;

    @Override
    public List<PostComment> findAll() {
        return postCommentDao.findAll();
    }

    @Override
    public Page<PostComment> find(int pageNum, int pageSize) {
        return postCommentDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<PostComment> find(int pageNum) {
        return null;
    }

    @Override
    public PostComment getById(Long id) {
        return null;
    }

    @Override
    public PostComment deleteById(Long id) {
        return null;
    }

    @Override
    public PostComment create(PostComment postComment) {
        return postCommentDao.save(postComment);
    }

    @Override
    public PostComment update(PostComment postComment) {
        return postCommentDao.save(postComment);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public List<PostComment> findByPostId(Long postId) {
        return postCommentDao.findByPostId(postId);
    }
}