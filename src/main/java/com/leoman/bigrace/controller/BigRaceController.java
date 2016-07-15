package com.leoman.bigrace.controller;

import com.leoman.bigrace.dao.BigRaceDao;
import com.leoman.bigrace.entity.BigRace;
import com.leoman.bigrace.service.BigRaceService;
import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
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
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<Province> province = provinceService.queryAll();
        model.addAttribute("province",province);
        return "bigrace/list";
    }

    /**
     * 列表
     * @param draw
     * @param start
     * @param length
     * @param bigRace
     * @param cityId
     * @param teamName
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,BigRace bigRace,City cityId,Province provinceId,String teamName){
        Page<BigRace> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            Stadium stadium = new Stadium();
            stadium.setCity(cityId);
            stadium.setProvince(provinceId);
            bigRace.setStadium(stadium);
            Page = bigRaceService.findAll(teamName,bigRace, pagenum, length);
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

    /**
     * 新增
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            if(id!=null){
                BigRace bigRace = bigRaceService.queryByPK(id);
                model.addAttribute("bigRace", bigRace);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/bigrace/add";
    }

    /**
     * 保存
     * @param bigRace
     * @param imageFile
     * @param imageFile2
     * @param detail
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(BigRace bigRace, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile2,String detail,Long stadiumId){
        BigRace b = null;
        Stadium s = new Stadium();
        try{
            if(null != bigRace.getId()){
                b = bigRaceService.queryByPK(bigRace.getId());
            }
            if(null != b){
                bigRace.setCreateDate(b.getCreateDate());
                bigRace.setStatus(b.getStatus());
                bigRace.setAvater1(b.getAvater1());
                bigRace.setAvater2(b.getAvater2());
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
            if(stadiumId != null){
                s.setId(stadiumId);
                bigRace.setStadium(s);
            }

            if (detail != null) {
                bigRace.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
            }
            bigRaceService.save(bigRace);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 改变状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id,Integer status){
        BigRace bigRace = bigRaceService.queryByPK(id);
        try{
            bigRace.setStatus(status);
            bigRaceService.save(bigRace);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }



}
