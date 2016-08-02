package com.leoman.team.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamMember;
import com.leoman.team.service.TeamMemberService;
import com.leoman.team.service.TeamService;
import com.leoman.team.service.impl.TeamServiceImpl;
import com.leoman.user.entity.User;
import com.leoman.user.service.UserService;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.Result;
import org.apache.commons.collections.list.SynchronizedList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
@Controller
@RequestMapping(value = "/admin/team")
public class TeamController extends GenericEntityController<Team, Team, TeamServiceImpl> {

    @Autowired
    private  UserService userService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/index")
    public String index(Model model,String details){
        try{
            List<Province> province = provinceService.queryAll();
            model.addAttribute("province",province);
            if(StringUtils.isNotBlank(details) && "1".equals(details)){
                model.addAttribute("details",details);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
       return "/team/list";
    }

    /**
     * 表格
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Team team,City cityId,Province provinceId,String details){
        Page<Team> teamPage = null;
//        List<Team> list = null;
        try {
            int pagenum = getPageNum(start,length);
            team.setCity(cityId);
            team.setProvince(provinceId);
            teamPage = teamService.findAll(details,team, pagenum, length);
//            list = teamService.findList(null,null,null,start,length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,teamPage);
//        return DataTableFactory.fitting(draw,list,list != null && !list.isEmpty() ? list.size() : 0);
    }

    /**
     * 页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Team team = teamService.findById(id);
            team.setTmSize(teamService.findTmSize(id));
            team.setAvater(StringUtils.isNotBlank(team.getAvater()) ? ConfigUtil.getString("upload.url") + team.getAvater() : "");
            model.addAttribute("team", team);
            List<TeamMember> teamMember = teamMemberService.findByTeamId(id);
            model.addAttribute("teamMember",teamMember);
            List<User> list = userService.queryAll();
            List<User> userList = new ArrayList<>();
            for(User u : list){
                u.setAvater(StringUtils.isNotBlank(u.getAvater()) ? ConfigUtil.getString("upload.url") + u.getAvater() : "");
                userList.add(u);
            }
            model.addAttribute("userList",userList);
            List<User> user = teamMemberService.findByAvater(id);
            model.addAttribute("user",user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "/team/detail";
    }

    /**
     * 详情
     * @param id
     * @param model
     * @param response
     * @return
     */
    @RequestMapping(value = "/sfTeamInfo")
    @ResponseBody
    public Result sfTeamInfo(Long id, Model model, HttpServletResponse response) {
        try {
            String msg = "";
            Team team = teamService.findById(id);
            if(team == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
