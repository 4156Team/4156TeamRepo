package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("query")
@RequestMapping("/query")
public class QueryController {

    @Autowired
    QueryService queryService;

    @RequestMapping(value = "/Event", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryEvent(@RequestParam(name = "eventName") String eventName){
        try{
            return CommonReturnType.create(queryService.queryEvent(eventName));
        }
        catch (BusinessException e){
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_EVENT);
        }
    }

    @RequestMapping(value = "/Facility", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryFacility(@RequestParam(name = "facilityName") String facilityName){
        try{
            return CommonReturnType.create(queryService.queryFacility(facilityName));
        }
        catch (BusinessException e){
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_FACILITY);
        }
    }
}
