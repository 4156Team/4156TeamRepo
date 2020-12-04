package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.service.AnnouncementService;
import com.java.rollercoaster.service.MailService;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public ErrorEnum pushAnnouncement(Announcement announcement)
            throws BusinessException, UnirestException {
        if (null == announcement.getText()) {
            return ErrorEnum.EMPTY_ANNOUNCEMENT_ATTRIBUTE;
        }
        announcement.setDate(new Date());
        announcementMapper.insert(announcement);
        UserAccountExample userAccountExample = new UserAccountExample();
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getRole() == Role.visitor && null != userAccount.getEmail()) {
                mailService.sendAnnouncementMessage(userAccount.getEmail(), announcement.getText());
            }
        }
        return ErrorEnum.OK;
    }

    @Override
    public List<Announcement> getAnnouncements() {
        return announcementMapper.selectByExample(new AnnouncementExample());
    }

    @Override
    public ErrorEnum deleteAnnouncement(Integer announcementId) {
        if (null == announcementId) {
            return ErrorEnum.EMPTY_ANNOUNCEMENT_ID;
        } else if (null == announcementMapper.selectByPrimaryKey(announcementId)) {
            return ErrorEnum.WRONG_ANNOUNCEMENT_ID;
        } else {
            announcementMapper.deleteByPrimaryKey(announcementId);
            return ErrorEnum.OK;
        }
    }
}
