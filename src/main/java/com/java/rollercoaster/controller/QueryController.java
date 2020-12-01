package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
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

    /**
     * End point to query an event according to event name.
     *
     *
     * @param  eventName        name of the event
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/Event", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType queryEvent(@RequestBody String eventName) {
        try {
            return CommonReturnType.create(queryService.queryEvent(eventName));
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_EVENT);
        }
    }

    /**
     * End point to query all events.
     *
     * @return                 all events
     */
    @RequestMapping(value = "/AllEvent", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryAllEvent() {
        try {
            return CommonReturnType.create(queryService.queryAllEvents());
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_EVENT);
        }
    }

    /**
     * End point to query all facilities.
     *
     * @return                 all facilities
     */
    @RequestMapping(value = "/AllFacility", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryAllFacility() {
        try {
            return CommonReturnType.create(queryService.queryAllFacilities());
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_FACILITY);
        }
    }

    /**
     * End point to query an facility according to facility name.
     *
     *
     * @param  facilityName    name of the facility
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/Facility", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType queryFacility(@RequestBody String facilityName) {
        try {
            return CommonReturnType.create(queryService.queryFacility(facilityName));
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate(ErrorEnum.NO_SUCH_FACILITY);
        }
    }
}
