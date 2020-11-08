package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.FacilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int countByExample(FacilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int deleteByExample(FacilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int deleteByPrimaryKey(String facilityName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int insert(Facility record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int insertSelective(Facility record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    List<Facility> selectByExample(FacilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    Facility selectByPrimaryKey(String facilityName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") Facility record, @Param("example") FacilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int updateByExample(@Param("record") Facility record, @Param("example") FacilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int updateByPrimaryKeySelective(Facility record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table facility
     *
     * @mbggenerated Sun Nov 08 20:55:14 CST 2020
     */
    int updateByPrimaryKey(Facility record);
}