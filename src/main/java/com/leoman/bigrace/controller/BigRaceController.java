package com.leoman.bigrace.controller;

import com.leoman.bigrace.dao.BigRaceDao;
import com.leoman.bigrace.entity.BigRace;
import com.leoman.bigrace.service.BigRaceService;
import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.stadium.entity.Stadium;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
@Controller
@RequestMapping(value = "/admin/bigRace")
public class BigRaceController extends GenericEntityController<BigRace,BigRace,BigRaceDao> {

    @Autowired
    private BigRaceService bigRaceService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<City> city = cityService.queryAll();
        model.addAttribute("city",city);
        return "bigrace/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,BigRace bigRace,City cityId){
        Page<BigRace> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Stadium stadium = new Stadium();
            stadium.setCity(cityId);
            bigRace.setStadium(stadium);
            Page = bigRaceService.findAll(bigRace, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

    //详情
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            BigRace bigRace = bigRaceService.queryByPK(id);
            model.addAttribute("bigRace", bigRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/bigrace/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            BigRace bigRace = bigRaceService.queryByPK(id);
            if(bigRace == null) {
                msg = "无法显示";
                return Result.failure(msg);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //新增
    @RequestMapping(value = "/add")
    public String add(){
        return "/bigrace/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            BigRace bigRace = bigRaceService.queryByPK(id);
            model.addAttribute("bigRace", bigRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/bigrace/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(BigRace bigRace, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile2,String detail,Long startDate){
        BigRace b = null;
        try{
            if(null != bigRace.getId()){
                b = bigRaceService.queryByPK(bigRace.getId());
            }
            if(null != b){
                bigRace.setCreateDate(b.getCreateDate());
                bigRace.setStadium(b.getStadium());
                bigRace.setStatus(b.getStatus());
            }else{
                bigRace.setStatus(0);
            }

            if(imageFile!=null && imageFile.getSize()>0) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    bigRace.setAvater1(fileBo.getPath());
                }
            }
            if(imageFile2!=null && imageFile2.getSize()>0) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(imageFile2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    bigRace.setAvater2(fileBo.getPath());
                }
            }
//            if(bigRace.getAvater1()==null){
//                bigRace.setAvater1(b.getAvater1());
//            }
//            if(bigRace.getAvater2()==null){
//                bigRace.setAvater2(b.getAvater2());
//            }
            if (detail != null) {
                bigRace.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
            }
//            if(startDate!=null){
//                bigRace.setStartDate(startDate);
//            }
            bigRaceService.save(bigRace);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id){
        BigRace bigRace = bigRaceService.queryByPK(id);
        Integer status = bigRace.getStatus();
        try{
            if(status == 0) {
                bigRace.setStatus(1);
                bigRaceService.save(bigRace);
            }else {
                bigRace.setStatus(0);
                bigRaceService.save(bigRace);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }



}
