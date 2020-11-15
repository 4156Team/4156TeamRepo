package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("query")
@RequestMapping("/query")
public class QueryController {

    @Autowired
    QueryService queryService;

    /**
     * End point to query an event according to event name.
     *
     *
     * @param  eventName        name of the event
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/Event", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryEvent(@RequestParam(name = "eventName") String eventName) {
        try {
            return CommonReturnType.create(queryService.queryEvent(eventName));
        } catch (BusinessException e) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_EVENT);
        }
    }

    /**
     * End point to query an facility according to facility name.
     *
     *
     * @param  facilityName    name of the facility
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/Facility", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryFacility(@RequestParam(name = "facilityName")
                                                      String facilityName) {
        try {
            return CommonReturnType.create(queryService.queryFacility(facilityName));
        } catch (BusinessException e) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_FACILITY);
        }
    }
}
