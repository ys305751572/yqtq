package com.leoman.girl.service.impl;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlImage;
import com.leoman.girl.entity.GirlUser;
import com.leoman.girl.service.GirlCommentService;
import com.leoman.girl.service.GirlImageService;
import com.leoman.girl.service.GirlService;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import com.leoman.utils.TestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
@Service
public class GirlServiceImpl extends GenericManagerImpl<Girl,GirlDao> implements GirlService{

    @Autowired
    private GirlDao dao;

    @Autowired
    private CityService cityService;
    @Autowired
    private GirlImageService girlImageService;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public Page<Girl> findAll(Integer appointment,Girl girl, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Girl> spec = buildSpecification(appointment,girl);
        Page<Girl> page = dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<Girl> girls = page.getContent();
        for(Girl g : girls){
            g.setGuSize(this.findSize(g.getId()));
        }
        return page;
    }

    @Override
    public Integer findSize(Long id) {
        return dao.findSize(id);
    }

    @Override
    @Transactional
    public Result saveGirl(Girl girl, City city, Province province, MultipartHttpServletRequest multipartRequest) {
        Girl g = null;
        try{
            if(null != girl.getId()){
                g = queryByPK(girl.getId());
            }
            if(null != g){
                girl.setStatus(g.getStatus());
                girl.setCreateDate(g.getCreateDate());
            }else {
                girl.setStatus(0);
            }
            if(city != null){
                City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
                girl.setCity(_city);
            }
            if(province != null){
                Province _province = provinceService.queryByProperty("provinceId",province.getProvinceId()).get(0);
                girl.setProvince(_province);
            }
            save(girl);
            this.saveImage(girl,multipartRequest);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 保存图片
     * @param girl
     * @param multipartRequest
     */
    @Transactional
    public void saveImage(Girl girl,MultipartHttpServletRequest multipartRequest){
        Iterator<String> list = multipartRequest.getFileNames();
        MultipartFile albumImageFile =null;
        MultipartFile coverImageFile =null;
        while (list.hasNext()) {
            String fileName = list.next();
            if (fileName.indexOf("coverImageFile") >= 0) {
                // 封面
                coverImageFile = multipartRequest.getFile(fileName);
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(coverImageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    GirlImage girlImage = new GirlImage();
                    girlImage.setGirlId(girl.getId());
                    girlImage.setType(0);
                    girlImage.setUrl(fileBo.getPath());
                    girlImageService.save(girlImage);
                }
            }
            if (fileName.indexOf("albumImageFile") >= 0) {
                // 相册
                albumImageFile = multipartRequest.getFile(fileName);
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(albumImageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    GirlImage girlImage = new GirlImage();
                    girlImage.setGirlId(girl.getId());
                    girlImage.setType(1);
                    girlImage.setUrl(fileBo.getPath());
                    girlImageService.save(girlImage);
                }
            }
        }
    }



    public Specification<Girl> buildSpecification(final Integer appointment,final Girl g) {
        return new Specification<Girl>() {
            @Override
            public Predicate toPredicate(Root<Girl> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(!g.getName().isEmpty()) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + g.getName() + "%"));
                }
                if(g.getCity().getCityId() != null){
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class), g.getCity().getCityId() ));
                }
                if(g.getProvince().getProvinceId() != null){
                    list.add(cb.equal(root.get("province").get("provinceId").as(Long.class), g.getProvince().getProvinceId() ));
                }
                if(g.getStatus() != null){
                    list.add(cb.equal(root.get("status").as(Integer.class), g.getStatus() ));
                }
                if(appointment !=null){
                    if(appointment==1){
                        List ids = dao.appointmentId(TestUtil.getTimesmorning(),TestUtil.getTimesnight());
                        if(ids.size()>0 && !ids.isEmpty()) {
                            Iterator iterator = ids.iterator();
                            CriteriaBuilder.In in = cb.in(root.get("id"));
                            while (iterator.hasNext()) {
                                in.value(iterator.next());
                            }
                                list.add(in);
                        }else {
                            list.add(cb.equal(root.get("id").as(Long.class), 0));
                        }
                    }else {
                            //今天没有预约的
                        List ids = dao.notAppointmentId(TestUtil.getTimesmorning(),TestUtil.getTimesnight());
                        if(ids.size()>0 && !ids.isEmpty()) {
                            Iterator iterator = ids.iterator();
                            CriteriaBuilder.In in = cb.in(root.get("id"));
                            while (iterator.hasNext()) {
                                in.value(iterator.next());
                            }
                            list.add(in);
                        }
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
