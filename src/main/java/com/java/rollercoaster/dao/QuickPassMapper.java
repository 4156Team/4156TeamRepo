package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.QuickPassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickPassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int countByExample(QuickPassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int deleteByExample(QuickPassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int deleteByPrimaryKey(String quickpassId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int insert(QuickPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int insertSelective(QuickPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    List<QuickPass> selectByExample(QuickPassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    QuickPass selectByPrimaryKey(String quickpassId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int updateByExampleSelective(@Param("record") QuickPass record, @Param("example") QuickPassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int updateByExample(@Param("record") QuickPass record, @Param("example") QuickPassExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int updateByPrimaryKeySelective(QuickPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quickpass
     *
     * @mbggenerated Sat Dec 05 01:04:53 CST 2020
     */
    int updateByPrimaryKey(QuickPass record);
}