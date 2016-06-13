package com.leoman.activity.controller;

import com.leoman.activity.entity.Activity;
import com.leoman.activity.service.ActivityService;
import com.leoman.activity.service.impl.ActivityServiceImpl;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Controller
@RequestMapping(value = "admin/activity")
public class ActivityController extends GenericEntityController<Activity, Activity, ActivityServiceImpl> {

    @Autowired
    private ActivityService activityService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "activity/list";
    }

    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param introduction
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     String introduction,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);

            Page<Activity> page = activityService.findPage(introduction, pageNum, length);

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
     * @param activityId
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Long activityId, Model model) {

        Activity activity = activityService.findById(activityId);

        model.addAttribute("activity", activity);

        return "activity/detail";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "activity/add";
    }

    /**
     * 编辑
     *
     * @param activityId
     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Long activityId, Model model) {
        try {
            Activity activity = activityService.findById(activityId);
            model.addAttribute("activity", activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "activity/add";
    }

    /**
     * 保存
     *
     * @param title
     * @param introduction
     * @param detail
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Integer save(Long id, String title, String introduction, String detail, MultipartRequest multipartRequest) {
        try {
            Activity activity = null;

            if (null == id) {
                activity = new Activity();
                activity.setCreateDate(System.currentTimeMillis());
            } else {
                activity = activityService.findById(id);
            }

            MultipartFile multipartFile = multipartRequest.getFile("imageFile");
            if (null != multipartFile) {
                FileBo fileBo = FileUtil.save(multipartFile);
                activity.setAveter(fileBo.getPath());
            }

            activity.setTitle(title);
            activity.setIntroduction(introduction);

            if (StringUtils.isNotEmpty(detail)) {
                activity.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            activityService.save(activity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
