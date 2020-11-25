package com.java.rollercoaster.controller;

import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/getAnnouncements")
    @ResponseBody
    public CommonReturnType getAnnouncements() {
        return CommonReturnType.create(announcementService.getAnnouncements(), "success");
    }
}
