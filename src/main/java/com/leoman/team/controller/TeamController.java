package com.leoman.team.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamMember;
import com.leoman.team.service.TeamMemberService;
import com.leoman.team.service.TeamService;
import com.leoman.team.service.impl.TeamServiceImpl;
import com.leoman.user.entity.User;
import com.leoman.user.service.UserService;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/index")
    public String index(){
       return "/team/list";
    }

    /**
     * 表格
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param name
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response, Integer draw, Integer start, Integer length, Long name,Long cityId){
        Page<Team> teamPage = null;
//        List<Team> list = null;
        try {
            Team t = new Team();
            int pagenum = getPageNum(start,length);
            t.setName(name);
            t.setCityId(cityId);
            teamPage = teamService.findAll(t, pagenum, length);
//            list = teamService.findList(null,null,null,start,length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,teamPage);
//        return DataTableFactory.fitting(draw,list,list != null && !list.isEmpty() ? list.size() : 0);
    }

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Team team = teamService.findById(id);
            model.addAttribute("team", team);
            List<TeamMember> teamMember = teamMemberService.findByTeamId(id);
//            TeamMember teamMember = teamMemberService.findByTeamId(id);
            model.addAttribute("teamMember",teamMember);
            List<User> userList = userService.findAll();
            model.addAttribute("userList",userList);
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
