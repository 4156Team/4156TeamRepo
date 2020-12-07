package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.mashape.unirest.http.exceptions.UnirestException;


import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;

public interface AnnouncementService {
    List<Announcement> getAnnouncements();

    ErrorEnum pushAnnouncement(Announcement announcement)
            throws BusinessException, UnirestException,
            GeneralSecurityException, MessagingException;

    ErrorEnum deleteAnnouncement(Integer announcementId);
}
