package com.leoman.systemInsurance.controller;

import com.leoman.activity.entity.Activity;
import com.leoman.activity.service.ActivityService;
import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.image.entity.FileBo;
import com.leoman.insurance.entity.Insurance;
import com.leoman.insurance.service.InsuranceService;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.systemInsurance.service.SystemInsuranceService;
import com.leoman.systemInsurance.service.impl.SystemInsuranceServiceImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/25.
 */
@Controller
@RequestMapping(value = "admin/systemInsurance")
public class SystemInsuranceController extends GenericEntityController<SystemInsurance, SystemInsurance, SystemInsuranceServiceImpl> {

    @Autowired
    private SystemInsuranceService systemInsuranceService;

    @Autowired
    private InsuranceService insuranceService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "systemInsurance/list";
    }

    /**
     * 查询列表
     *
     * @param request
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param model
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Model model) {
        try {
            int pageNum = getPageNum(start, length);

            Page<SystemInsurance> page = systemInsuranceService.findPage(pageNum, length);

            List<SystemInsurance> list = page.getContent();
            for (SystemInsurance systemInsurance : list) {

                Integer num = 0;
                Double price = 0.0;
                List<Insurance> insuranceList = insuranceService.findBySysInsuranceId(systemInsurance.getId());
                for (Insurance insurance : insuranceList) {

                    num += insurance.getNum();
                    price += insurance.getMoney();
                }

                systemInsurance.setPayNum(insuranceList.size());
                systemInsurance.setAccidentNum(num);
                systemInsurance.setSumPrice(price);
            }
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
     * @param systemInsuranceId
     * @param model
     */
    @RequestMapping(value = "/detail")
    public String detail(Long systemInsuranceId, Model model) {

        SystemInsurance systemInsurance = systemInsuranceService.queryByPK(systemInsuranceId);

        model.addAttribute("systemInsurance", systemInsurance);

        return "systemInsurance/detail";
    }

    /**
     * 封禁
     *
     * @param response
     * @param insuranceId
     * @return
     */
    @RequestMapping(value = "/banned")
    @ResponseBody
    public Integer banned(HttpServletResponse response, Long insuranceId, Integer status) {

        SystemInsurance systemInsurance = systemInsuranceService.queryByPK(insuranceId);
        systemInsurance.setStatus(status);
        systemInsuranceService.update(systemInsurance);

        return 1;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "systemInsurance/add";
    }

    /**
     * 编辑
     *
     * @param activityId
     * @param model
     */
    @RequestMapping(value = "/edit")
    public String edit(Long activityId, Model model) {
        try {
            SystemInsurance systemInsurance = systemInsuranceService.queryByPK(activityId);
            model.addAttribute("systemInsurance", systemInsurance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "systemInsurance/add";
    }

    /**
     * 保存
     *
     *
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Integer save(Long id, String name, Double price, Double bj, String company, String detail) {
        try {
            SystemInsurance systemInsurance = null;

            if (null == id) {
                systemInsurance = new SystemInsurance();
                systemInsurance.setCreateDate(System.currentTimeMillis());
            } else {
                systemInsurance = systemInsuranceService.queryByPK(id);
            }

            systemInsurance.setName(name);
            systemInsurance.setPrice(price);
            systemInsurance.setBj(bj);
            systemInsurance.setCompany(company);

            if (StringUtils.isNotEmpty(detail)) {
                systemInsurance.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
            }

            systemInsuranceService.save(systemInsurance);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
