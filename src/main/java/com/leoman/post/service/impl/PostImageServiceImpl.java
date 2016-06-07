package com.leoman.post.service.impl;

import com.leoman.post.dao.PostImageDao;
import com.leoman.post.entity.PostImage;
import com.leoman.post.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 下午 5:04.
 */
@Service
public class PostImageServiceImpl implements PostImageService {

    @Autowired
    private PostImageDao postImageDao;

    @Override
    public List<PostImage> findAll() {
        return postImageDao.findAll();
    }

    @Override
    public Page<PostImage> find(int pageNum, int pageSize) {
        return postImageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<PostImage> find(int pageNum) {
        return null;
    }

    @Override
    public PostImage getById(Long id) {
        return null;
    }

    @Override
    public PostImage deleteById(Long id) {
        return null;
    }

    @Override
    public PostImage create(PostImage postImage) {
        return postImageDao.save(postImage);
    }

    @Override
    public PostImage update(PostImage postImage) {
        return postImageDao.save(postImage);
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public List<PostImage> findByPostId(Long postId) {
        return postImageDao.findByPostId(postId);
    }
}