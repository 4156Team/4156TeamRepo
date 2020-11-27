package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAnnouncements();

    ErrorEnum pushAnnouncement(Announcement announcement)
            throws BusinessException, UnirestException;

    ErrorEnum deleteAnnouncement(Integer announcementId);
}
