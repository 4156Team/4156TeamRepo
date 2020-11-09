package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    UserAccount selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 18:34:28 CST 2020
     */
    int updateByPrimaryKey(UserAccount record);
}