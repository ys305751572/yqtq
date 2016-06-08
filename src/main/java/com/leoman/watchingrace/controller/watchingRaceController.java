package com.leoman.watchingrace.controller;

import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.Result;
import com.leoman.watchingrace.dao.WatchingRaceDao;
import com.leoman.watchingrace.entity.WatchingRace;
import com.leoman.watchingrace.service.WatchingRaceService;
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
@RequestMapping(value = "/admin/watchingRace")
public class WatchingRaceController extends GenericEntityController<WatchingRace,WatchingRace,WatchingRaceDao> {

    @Autowired
    private WatchingRaceService watchingRaceService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<City> city = cityService.queryAll();
        model.addAttribute("city",city);
        return "watchingrace/list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer draw, Integer start, Integer length,WatchingRace watchingRace,City cityId){
        Page<WatchingRace> Page = null;
        try {
            int pagenum = getPageNum(start,length);
            watchingRace.setCity(cityId);
            Page = watchingRaceService.findAll(watchingRace, pagenum, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataTableFactory.fitting(draw,Page);
    }

    //详情
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model){
        try{
            WatchingRace watchingRace = watchingRaceService.queryByPK(id);
            model.addAttribute("watchingRace", watchingRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/watchingrace/detail";
    }

    @RequestMapping(value = "/sfInfo")
    @ResponseBody
    public Result sfInfo(Long id, Model model) {
        try {
            String msg = "";
            WatchingRace watchingRace = watchingRaceService.queryByPK(id);
            if(watchingRace == null) {
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
    public String add(Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/watchingrace/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model){
        try{
            List<City> city = cityService.queryAll();
            model.addAttribute("city",city);
            WatchingRace watchingRace = watchingRaceService.queryByPK(id);
            model.addAttribute("watchingRace", watchingRace);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/watchingrace/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(WatchingRace watchingRace, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile,City city, String detail){
        WatchingRace w = null;
        try{
            if(null != watchingRace.getId()){
                w = watchingRaceService.queryByPK(watchingRace.getId());
            }
            if(null != w){
                watchingRace.setCreateDate(w.getCreateDate());
                watchingRace.setInvitation(w.getInvitation());
            }else {
                watchingRace.setInvitation(0);
            }
            if(imageFile!=null && imageFile.getSize()>0) {
                FileBo fileBo = null;
                try {
                    fileBo = FileUtil.save(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                    watchingRace.setAvater(fileBo.getPath());
                }
            }
            if (detail != null) {
                watchingRace.setDescription(detail.replace("&lt", "<").replace("&gt", ">"));
            }
            if(city != null){
                City _city = cityService.queryByProperty("cityId",city.getCityId()).get(0);
                watchingRace.setCity(_city);
            }
            watchingRaceService.save(watchingRace);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }



}
