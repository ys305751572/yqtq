package com.leoman.activity.controller;

import com.leoman.activity.entity.Activity;
import com.leoman.activity.service.ActivityService;
import com.leoman.activity.service.impl.ActivityServiceImpl;
import com.leoman.common.controller.common.CommonController;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.hostRace.entity.HostRace;
import com.leoman.hostRace.service.impl.HostRaceServiceImpl;
import com.leoman.post.entity.Post;
import com.leoman.post.entity.PostComment;
import com.leoman.stadium.entity.Stadium;
import com.leoman.utils.Result;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
     * 查询列表
     *
     * @param activityId
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Long activityId, Model model) {

        Activity activity = activityService.findById(activityId);

        model.addAttribute("activity",activity);

        return "activity/detail";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "hostRace/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long activityId, Model model){
        try{
            Activity activity = activityService.findById(activityId);
            model.addAttribute("activity", activity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "activity/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Activity activity, String detail) {

        Activity h = null;
        if (null != activity.getId()) {
            h = activityService.queryByPK(activity.getId());
        }
        /*if (null != h) {
            hostRace.setStadium(h.getStadium());
            hostRace.setStatus(h.getStatus());
            hostRace.setMatchType(h.getMatchType());
            hostRace.setHrSet(h.getHrSet());
            hostRace.setCreateDate(h.getCreateDate());
        }
        if (detail != null) {
            hostRace.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
        }
        if(stadium != null){
            Stadium _stadium = stadiumService.queryByPK(stadium.getId());
            hostRace.setStadium(_stadium);
        }
        hostRaceService.save(hostRace);*/
        return Result.success();
    }
}
