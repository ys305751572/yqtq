package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamDao;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.vo.TeamVo;
import com.leoman.team.service.TeamService;
import com.leoman.utils.TestUtil;
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
public class TeamServiceImpl extends GenericManagerImpl<Team, TeamDao> implements TeamService {
    @Autowired
    private TeamDao dao;

    @Autowired
    private EntityManagerFactory factory;

    @Override
    public List<Team> findList(Long id,Long name,Long cityId,int start,int length) {
        EntityManager em = factory.createEntityManager();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT 									 ");
        sql.append("   t1.id,                                    ");
        sql.append("   t1.name,                                  ");
        sql.append("   t1.city_id,                               ");
        sql.append("   tta.num,                                  ");
        sql.append("   ttt.season,                               ");
        sql.append("   t1.create_date                            ");
        sql.append(" FROM                                        ");
        sql.append("   t_team t1                                 ");
        sql.append("   LEFT JOIN                                 ");
        sql.append("     (SELECT                                 ");
        sql.append("       tt.name AS na,                        ");
        sql.append("       COUNT(ttm.user_id) AS num             ");
        sql.append("     FROM                                    ");
        sql.append("       t_team_member AS ttm                  ");
        sql.append("       JOIN t_team AS tt                     ");
        sql.append("         ON ttm.team_id = tt.name            ");
        sql.append("     GROUP BY tt.name) AS tta                ");
        sql.append("     ON t1.name = tta.na                     ");
        sql.append("   LEFT JOIN                                 ");
        sql.append("     (SELECT                                 ");
        sql.append("       t1.name AS na,                        ");
        sql.append("       COUNT(t2.stadium_id) AS season        ");
        sql.append("     FROM                                    ");
        sql.append("       t_team t1                             ");
        sql.append("       JOIN t_team_race t2                   ");
        sql.append("         ON t1.name = t2.home_team_id        ");
        sql.append("         OR t1.name = t2.visiting_team_id    ");
        sql.append("     GROUP BY t1.name) ttt                   ");
        sql.append("     ON t1.name = ttt.na                     ");
        sql.append("WHERE t1.id="+1+" ");
        if(null!=name){
            sql.append("AND t1.name LIKE %"+name+"%");
        }
        if(null!=cityId){
            sql.append("AND t1.city_id LIKE %"+cityId+"%");
        }
        System.out.println("sql:" + sql.toString());
        Query query = em.createNativeQuery(sql.toString(), TeamVo.class);

        query.setFirstResult(start);
        query.setMaxResults(length);
        List<TeamVo> list = query.getResultList();
        em.close();
        return null;
    }

    @Override
    public Team findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public Page<Team> findAll(String details,Team team, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Team> spec = buildSpecification(details,team);
        Page<Team> page = dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<Team> list = page.getContent();
        for (Team team1 : list) {
            team1.setTmSize(this.findTmSize(team1.getId()));
        }
        return page;
    }

    @Override
    public List<Team> findAll() {
        return dao.findAll();
    }

    @Override
    public Integer findTmSize(Long teamId) {
        return dao.findTmSize(teamId);
    }

    public Specification<Team> buildSpecification(final String details,final Team t) {
        return new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(t.getName() != null) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + t.getName() + "%"));
                }
                if(t.getProvince().getProvinceId() != null){
                    list.add(cb.equal(root.get("province").get("provinceId").as(Long.class), t.getProvince().getProvinceId() ));
                }
                if(t.getCity().getCityId() != null){
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class), t.getCity().getCityId() ));
                }
                if (StringUtils.isNotBlank(details) && "1".equals(details)) {
                    list.add(cb.ge(root.get("createDate").as(Long.class), TestUtil.getTimesmorning()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}
