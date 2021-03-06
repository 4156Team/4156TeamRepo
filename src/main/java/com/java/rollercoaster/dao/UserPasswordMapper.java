package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.pojo.UserPasswordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPasswordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int countByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int deleteByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int insert(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int insertSelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    List<UserPassword> selectByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    UserPassword selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserPassword record, @Param("example") UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int updateByExample(@Param("record") UserPassword record, @Param("example") UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int updateByPrimaryKeySelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    int updateByPrimaryKey(UserPassword record);
}