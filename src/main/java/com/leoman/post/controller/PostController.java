package com.leoman.post.controller;

import com.leoman.common.controller.common.CommonController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.post.entity.Post;
import com.leoman.post.entity.PostComment;
import com.leoman.post.entity.PostImage;
import com.leoman.post.service.PostCommentService;
import com.leoman.post.service.PostImageService;
import com.leoman.post.service.PostService;
import com.leoman.reserve.entity.Reserve;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@Controller
@RequestMapping(value = "/admin/post")
public class PostController extends CommonController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostImageService postImageService;

    @Autowired
    private PostCommentService postCommentService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "post/list";
    }

    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param post
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Post post,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);

            Page<Post> page = postService.findPage(post,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 详情
     *
     * @param postId
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long postId, Model model) {

        Post post = postService.getById(postId);
        List<PostImage> postImageList = postImageService.findByPostId(postId);
        List<PostComment> postCommentList = postCommentService.findByPostId(postId);

        model.addAttribute("post",post);
        model.addAttribute("postImageList",postImageList);
        model.addAttribute("postCommentList",postCommentList);

        return "reserve/detail";
    }
}
