package com.leoman.team.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamMember;
import com.leoman.team.entity.TeamRace;
import com.leoman.team.service.TeamMemberService;
import com.leoman.team.service.TeamRaceService;
import com.leoman.team.service.TeamService;
import com.leoman.team.service.impl.TeamRaceServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
@Controller
@RequestMapping(value = "/admin/teamRace")
public class TeamRaceController  extends GenericEntityController<TeamRace, TeamRace, TeamRaceServiceImpl> {

    @Autowired
    private TeamRaceService teamRaceService;
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/index")
    public String index(){
        return "teamRace/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, Long teamId,Long cityId,Long stadiumId){
        Page<TeamRace> teamRacePage = null;
        try {
            TeamRace t = new TeamRace();
            int pagenum = getPageNum(start,length);
//            t.setHomeTeamId();
//            t.setVisitingTeamId();
            t.setCityId(cityId);
            t.setStadiumId(stadiumId);
            teamRacePage = teamRaceService.findAll(t, pagenum, length);
//            teamRaceService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,teamRacePage);
    }

    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            TeamRace teamRace = teamRaceService.findById(id);
            model.addAttribute("teamRace", teamRace);
            List<Team> team = teamService.findAll();
            model.addAttribute("team", team);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/teamRace/detail";
    }

    @RequestMapping(value = "/sfTeamRaceInfo")
    @ResponseBody
    public Result sfTeamInfo(Long id, Model model) {
        try {
            String msg = "";
            TeamRace teamRace = teamRaceService.findById(id);
            if(teamRace == null) {
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
