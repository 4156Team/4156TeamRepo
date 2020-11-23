package com.java.rollercoaster.dao;

import com.java.rollercoaster.pojo.TicketPrice;
import com.java.rollercoaster.pojo.TicketPriceExample;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketPriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int countByExample(TicketPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int deleteByExample(TicketPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int deleteByPrimaryKey(TicketType TicketType);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int insert(TicketPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int insertSelective(TicketPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    List<TicketPrice> selectByExample(TicketPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    TicketPrice selectByPrimaryKey(TicketType TicketType);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int updateByExampleSelective(@Param("record") TicketPrice record, @Param("example") TicketPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int updateByExample(@Param("record") TicketPrice record, @Param("example") TicketPriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int updateByPrimaryKeySelective(TicketPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticketPrice
     *
     * @mbggenerated Sun Nov 22 15:26:01 CST 2020
     */
    int updateByPrimaryKey(TicketPrice record);
}