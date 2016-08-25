package com.leoman.girl.controller;

import com.leoman.city.dao.CityDao;
import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.girl.dao.GirlDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlComment;
import com.leoman.girl.entity.GirlImage;
import com.leoman.girl.service.GirlCommentService;
import com.leoman.girl.service.GirlImageService;
import com.leoman.girl.service.GirlService;
import com.leoman.girl.service.impl.GirlServiceImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import com.leoman.utils.TestUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/6/7.
 */
@Controller
@RequestMapping(value = "/admin/girl")
public class GirlController extends GenericEntityController<Girl, Girl, GirlServiceImpl> {

    @Autowired
    private GirlService girlService;
    @Autowired
    private CityService cityService;
    @Autowired
    private GirlImageService girlImageService;
    @Autowired
    private GirlCommentService girlCommentService;
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<Province> province = provinceService.queryAll();
        model.addAttribute("province",province);
        return "/girl/list";
    }

    /**
     * 列表
     * @param draw
     * @param start
     * @param length
     * @param girl
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,Girl girl,City cityId,Province provinceId,Integer appointment){
        Page<Girl> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            girl.setCity(cityId);
            girl.setProvince(provinceId);
            Page = girlService.findAll(appointment,girl, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            Girl girl = girlService.queryByPK(id);
            girl.setGuSize(girlService.findSize(id));
            model.addAttribute("girl", girl);
            List<GirlImage> imageList = girlImageService.queryByProperty("girlId",id);
            model.addAttribute("image",imageList);
            Integer avgStar = girlCommentService.avgStar(id);
            model.addAttribute("avgStar",avgStar);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/girl/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            Girl girl = girlService.queryByPK(id);
            if(girl == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增编辑跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            List<Province> province = provinceService.queryAll();
            model.addAttribute("province",province);
            if(id!=null){
                Girl girl = girlService.queryByPK(id);
                model.addAttribute("girl", girl);
                List<GirlImage> imageList = girlImageService.queryByProperty("girlId",id);
                model.addAttribute("image",imageList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/girl/add";
    }

    /**
     * 保存
     * @param girl
     * @param city
     * @param multipartRequest
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(Girl girl, City city,Province province, MultipartHttpServletRequest multipartRequest){
        return  girlService.saveGirl(girl,city,province,multipartRequest);
    }


    /**
     * 编辑删除图片
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteImage")
    @ResponseBody
    public Object deleteBatch(Long id) {
        try {
            GirlImage girlImage = girlImageService.queryByPK(id);
            girlImageService.delete(girlImage);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 能否预约
     * @param id
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id,Integer status){
        Girl girl = girlService.queryByPK(id);
        try{
            girl.setStatus(status);
            girlService.save(girl);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

}
