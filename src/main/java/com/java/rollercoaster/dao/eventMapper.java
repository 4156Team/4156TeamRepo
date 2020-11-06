package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.event;
import com.java.rollercoaster.pojo.eventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface eventMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int countByExample(eventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int deleteByExample(eventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int deleteByPrimaryKey(String eventName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int insert(event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int insertSelective(event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    List<event> selectByExample(eventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    event selectByPrimaryKey(String eventName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int updateByExampleSelective(@Param("record") event record, @Param("example") eventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int updateByExample(@Param("record") event record, @Param("example") eventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int updateByPrimaryKeySelective(event record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table event
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    int updateByPrimaryKey(event record);
}