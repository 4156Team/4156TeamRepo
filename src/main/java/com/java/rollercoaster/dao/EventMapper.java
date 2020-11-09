package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.EventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int countByExample(EventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int deleteByExample(EventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int deleteByPrimaryKey(String eventName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int insert(Event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int insertSelective(Event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    List<Event> selectByExample(EventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    Event selectByPrimaryKey(String eventName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByExampleSelective(@Param("record") Event record, @Param("example") EventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByExample(@Param("record") Event record, @Param("example") EventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByPrimaryKeySelective(Event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByPrimaryKey(Event record);
}