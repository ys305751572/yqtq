package com.leoman.reserve.controller;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.city.service.CityService;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.controller.common.CommonController;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.reserve.dao.ReserveDao;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.service.StadiumService;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.systemInsurance.service.SystemInsuranceService;
import com.leoman.user.entity.User;
import com.leoman.user.entity.UserReserveJoin;
import com.leoman.user.service.UserReserveJoinService;
import com.leoman.user.service.UserService;
import com.leoman.utils.ConfigUtil;
import com.leoman.utils.Result;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
@RequestMapping(value = "admin/reserve")
public class ReserveController extends GenericEntityController<Reserve,Reserve,ReserveDao> {

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private UserReserveJoinService userReserveJoinService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private StadiumService stadiumService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model,String details) {
        List<Province> province = provinceService.queryAll();
        model.addAttribute("province",province);
        if(StringUtils.isNotBlank(details) && "1".equals(details)){
            model.addAttribute("details",details);
        }
        return "reserve/list";
    }


    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param reserve
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Reserve reserve,
                     SystemInsurance id,
                     String details,
                     City cityId,Province provinceId,Stadium stadium) {
        try {
            int pageNum = getPageNum(start, length);
            if(request.getParameter("stadiumId")!=null && request.getParameter("stadiumId")!=""){
                stadium.setId(Long.parseLong(request.getParameter("stadiumId").toString()));
            }
            reserve.setSystemInsurance(id);
            stadium.setCity(cityId);
            stadium.setProvince(provinceId);
            reserve.setStadium(stadium);
            Page<Reserve> page = reserveService.findPage(details,reserve,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id, Model model) {
        Reserve reserve = reserveService.queryByPK(id);
        model.addAttribute("reserve", reserve);
        Long reserveId = reserve.getId();
        List<UserReserveJoin> userReserveJoins = userReserveJoinService.queryByProperty("reserveId",reserveId);
        model.addAttribute("num",userReserveJoins.size());
        List<User> list = new ArrayList<User>();
        for(UserReserveJoin u : userReserveJoins){
            User user = userService.queryByPK(u.getUserId());
            if(user!=null){
                list.add(user);
            }
        }
        model.addAttribute("list", list);
        return "reserve/detail";
    }

    @RequestMapping(value = "/selectCity")
    @ResponseBody
    public List<City> selectCity(Long provinceId,Model model){
        List<City> cities =  cityService.queryByProperty("provinceId",provinceId);
        return cities;
    }

    @RequestMapping(value = "/selectStadium")
    @ResponseBody
    public List<Stadium> selectStadium(Long cityId,Model model){
        List<Stadium> stadia =  stadiumService.queryByProperty("city",cityId);
        return stadia;
    }

}
