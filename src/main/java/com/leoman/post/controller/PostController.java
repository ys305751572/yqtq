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
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
     * @param nickName
     * @param content
     * @param status
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     String nickName,
                     String content,
                     Integer status,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);

            Page<Post> page = postService.findPage(nickName, content, status, pageNum, length);
            List<Post> list = page.getContent();
            for (Post post1 : list) {
                List<PostComment> postCommentList = postCommentService.findByPostId(post1.getId());
                post1.setCommentNum(postCommentList.size());
            }
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 封禁
     *
     * @param response
     * @param postId
     * @return
     */
    @RequestMapping(value = "/banned")
    @ResponseBody
    public Integer banned(HttpServletResponse response, Long postId, Integer status) {

        Post post = postService.getById(postId);
        post.setStatus(status);
        postService.update(post);

        return 1;
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
        try {

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = null;

            Post post = postService.getById(postId);
            List<PostImage> postImageList = postImageService.findByPostId(postId);

            List<PostComment> postCommentList = postCommentService.findByPostId(postId);
            for (PostImage image : postImageList) {
                map = new HashMap<String, Object>();
                map.put("id", image.getId());
                map.put("path", image.getAvater());

                list.add(map);
            }

            model.addAttribute("post", post);
            model.addAttribute("postCommentList", postCommentList);
            model.addAttribute("postImageList", JSONArray.fromObject(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "post/detail";
    }
}
