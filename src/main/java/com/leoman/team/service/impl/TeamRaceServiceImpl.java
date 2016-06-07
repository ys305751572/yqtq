package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamDao;
import com.leoman.team.dao.TeamRaceDao;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamRace;
import com.leoman.team.entity.vo.TeamVo;
import com.leoman.team.service.TeamRaceService;
import com.leoman.team.service.TeamService;
import org.apache.commons.lang.StringUtils;
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
    public Page<TeamRace> findAll(TeamRace teamRace, String teamName, Integer currentPage, Integer pageSize) throws Exception {
        Specification<TeamRace> spec = buildSpecification(teamRace,teamName);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<TeamRace> buildSpecification(final TeamRace t,final String teamName) {
        return new Specification<TeamRace>() {
            @Override
            public Predicate toPredicate(Root<TeamRace> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate p = null;
                if(StringUtils.isNotBlank(teamName)) {
                    p = cb.like(root.get("homeTeam").get("name").as(String.class),"%" + teamName + "%");
                    p = cb.or(p,cb.like(root.get("visitingTeam").get("name").as(String.class),"%" + teamName + "%"));
                }

                if(t.getCity().getCityId() != null){
//                    list.add(cb.equal(root.get("cityId").as(Long.class), t.getCityId()));
                    if(p != null) {
                        p = cb.and(p,cb.equal(root.get("city").get("cityId").as(Integer.class), t.getCity().getCityId()));
                    }
                    else {
                        p = cb.equal(root.get("city").get("cityId").as(Integer.class), t.getCity().getCityId());
                        cb.and(p);
                    }
                }
                if(t.getStadium().getId() !=null){
//                    list.add(cb.equal(root.get("stadium").as(Long.class), t.getStadiumId()));
                    if(p != null) {
                        p = cb.and(p,cb.equal(root.get("stadium").get("id").as(Long.class), t.getStadium().getId()));
                    }
                    else {
                        p = cb.and(cb.equal(root.get("stadium").get("id").as(Long.class), t.getStadium().getId()));
                    }

                }
//                Predicate[] p = new Predicate[list.size()];
                return p;
            }
        };
    }
}
