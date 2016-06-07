package com.leoman.team.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.service.StadiumService;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamRace;
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
    @Autowired
    private CityService cityService;
    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
            List<Stadium> stadium = stadiumService.findAll();
            model.addAttribute("stadium",stadium);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return "teamRace/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length, TeamRace teamRace, String teamName, City cityId, Stadium id){
        Page<TeamRace> teamRacePage = null;
        try {
            TeamRace t = new TeamRace();
            int pagenum = getPageNum(start,length);
            teamRace.setCity(cityId);
            teamRace.setStadium(id);
            teamRacePage = teamRaceService.findAll(teamRace,teamName, pagenum, length);
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
