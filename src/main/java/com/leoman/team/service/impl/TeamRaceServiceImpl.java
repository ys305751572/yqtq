package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamDao;
import com.leoman.team.dao.TeamRaceDao;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamRace;
import com.leoman.team.entity.vo.TeamVo;
import com.leoman.team.service.TeamRaceService;
import com.leoman.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
@Service
public class TeamRaceServiceImpl extends GenericManagerImpl<TeamRace, TeamRaceDao> implements TeamRaceService {
    @Autowired
    private TeamRaceDao dao;

    @Autowired
    private EntityManagerFactory factory;


    @Override
    public TeamRace findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public Page<TeamRace> findAll(TeamRace teamRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<TeamRace> spec = buildSpecification(teamRace);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<TeamRace> buildSpecification(final TeamRace t) {
        return new Specification<TeamRace>() {
            @Override
            public Predicate toPredicate(Root<TeamRace> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

//                if(t.getHomeTeamId() != null || t.getVisitingTeamId() !=null) {
//                    list.add(cb.like(root.get("teamId").as(String.class), "%" + t.getHomeTeamId() + "% OR %"+t.getVisitingTeamId()+"%"));
//                }
                if(t.getCityId() != null){
                    list.add(cb.equal(root.get("cityId").as(Long.class), t.getCityId()));
                }
                if(t.getStadiumId() !=null){
                    list.add(cb.equal(root.get("stadium").as(Long.class), t.getStadiumId()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
