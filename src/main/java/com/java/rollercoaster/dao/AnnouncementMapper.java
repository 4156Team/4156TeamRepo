package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnouncementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int countByExample(AnnouncementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int deleteByExample(AnnouncementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int deleteByPrimaryKey(Integer announcementId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int insert(Announcement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int insertSelective(Announcement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    List<Announcement> selectByExample(AnnouncementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    Announcement selectByPrimaryKey(Integer announcementId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int updateByExampleSelective(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int updateByExample(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int updateByPrimaryKeySelective(Announcement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table announcement
     *
     * @mbggenerated Wed Nov 25 20:55:09 CST 2020
     */
    int updateByPrimaryKey(Announcement record);
}