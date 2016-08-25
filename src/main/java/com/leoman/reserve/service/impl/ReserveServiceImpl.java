package com.leoman.reserve.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.insurance.entity.Insurance;
import com.leoman.reserve.dao.ReserveDao;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.utils.TestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
@Service
public class ReserveServiceImpl extends GenericManagerImpl<Reserve,ReserveDao> implements ReserveService {

    @Autowired
    private ReserveDao dao;

    @Override
    public Page<Reserve> findPage(final String details,final Reserve reserve, int pagenum, int pagesize) {
        Specification<Reserve> spec = new Specification<Reserve>() {
            @Override
            public Predicate toPredicate(Root<Reserve> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (reserve.getStadium()!=null){
                    if (reserve.getStadium().getCity()!=null && reserve.getStadium().getCity().getCityId() != null) {
                        list.add(criteriaBuilder.equal(root.get("stadium").get("city").get("cityId").as(Long.class), reserve.getStadium().getCity().getCityId()));
                    }
                    if (reserve.getStadium().getProvince()!=null && reserve.getStadium().getProvince().getProvinceId() != null) {
                        list.add(criteriaBuilder.equal(root.get("stadium").get("province").get("provinceId").as(Long.class), reserve.getStadium().getProvince().getProvinceId()));
                    }
                    if (reserve.getStadium().getId() != null) {
                        list.add(criteriaBuilder.equal(root.get("stadium").get("id").as(Long.class), reserve.getStadium().getId()));
                    }
                    if (StringUtils.isNotBlank(reserve.getStadium().getName())) {
                        list.add(criteriaBuilder.like(root.get("stadium").get("name").as(String.class), "%"+reserve.getStadium().getName()+"%"));
                    }


                    if (reserve.getStadium().getStadiumUserId() != null) {
                        list.add(criteriaBuilder.equal(root.get("stadium").get("stadiumUserId").as(Long.class), reserve.getStadium().getStadiumUserId()));
                    }

                }

                if (reserve.getUser()!=null && StringUtils.isNotBlank(reserve.getUser().getNickName())) {
                    list.add(criteriaBuilder.like(root.get("user").get("nickName").as(String.class), "%"+reserve.getUser().getNickName()+"%"));
                }

                if (reserve.getTime() != null) {
                    list.add(criteriaBuilder.equal(root.get("time").as(Integer.class), reserve.getTime()));
                }

                if (reserve.getReserveType() != null) {
                    list.add(criteriaBuilder.equal(root.get("reserveType").as(Integer.class), reserve.getReserveType()));
                }

                if (reserve.getMatchType() != null) {
                    list.add(criteriaBuilder.equal(root.get("matchType").as(Integer.class), reserve.getMatchType()));
                }
                if (reserve.getPayment() != null) {
                    list.add(criteriaBuilder.equal(root.get("payment").as(Integer.class), reserve.getPayment()));
                }
                if (reserve.getSystemInsurance()!=null && reserve.getSystemInsurance().getId() != null) {
                    list.add(criteriaBuilder.equal(root.get("systemInsurance").get("id").as(String.class), reserve.getSystemInsurance().getId()));
                }
                if (reserve.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), reserve.getStatus()));
                }
                if (StringUtils.isNotBlank(details) && "1".equals(details)) {
                    list.add(criteriaBuilder.ge(root.get("createDate").as(Long.class), TestUtil.getTimesmorning()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<Reserve> page = dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
        List<Reserve> reserves = page.getContent();
        for(Reserve r : reserves){
            r.setNum(this.findNum(r.getId()));
        }
        return page;
    }

    @Override
    public Integer findNum(Long reserveId) {
        return dao.findNum(reserveId);
    }

    @Override
    public Long findStadiumBookingId(Long stadiumId, Long userId, Long createDate) {
        return dao.findStadiumBookingId(stadiumId,userId,createDate);
    }


}
