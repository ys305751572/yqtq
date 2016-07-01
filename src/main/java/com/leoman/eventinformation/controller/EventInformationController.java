package com.leoman.eventinformation.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.eventinformation.entity.EventInformation;
import com.leoman.eventinformation.service.EventInformationService;
import com.leoman.eventinformation.service.impl.EventInformationServiceImpl;
import com.leoman.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
@Controller
@RequestMapping(value = "/admin/eventInformation")
public class EventInformationController extends GenericEntityController<EventInformation,EventInformation,EventInformationServiceImpl>{

    @Autowired
    private EventInformationService eventInformationService;

    @RequestMapping(value = "/save")
    @ResponseBody
    private Result save(String detail,Long id){
            EventInformation eventInformation = new EventInformation();
            List<EventInformation> list = eventInformationService.queryByProperty("raceId",id);
            EventInformation ei = null;
            if(!list.isEmpty() && list.size()>0){
                ei = list.get(0);
            }
            try{
                if(ei!=null){
                    ei.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
                    eventInformationService.update(ei);
                }else {
                    eventInformation.setRaceId(id);
                    eventInformation.setContent(detail.replace("&lt", "<").replace("&gt", ">"));
                    eventInformationService.save(eventInformation);
                }
            }catch (RuntimeException e){
                e.printStackTrace();
            Result.failure();
        }
        return Result.success();
    }

}
