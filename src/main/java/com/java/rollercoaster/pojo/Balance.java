package com.java.rollercoaster.pojo;

public class Balance {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.user_id
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.balance
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    private Float balance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.quickPass
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    private Integer quickpass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.user_id
     *
     * @return the value of balance.user_id
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.user_id
     *
     * @param userId the value for balance.user_id
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.balance
     *
     * @return the value of balance.balance
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public Float getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.balance
     *
     * @param balance the value for balance.balance
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.quickPass
     *
     * @return the value of balance.quickPass
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public Integer getQuickpass() {
        return quickpass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.quickPass
     *
     * @param quickpass the value for balance.quickPass
     *
     * @mbggenerated Tue Dec 01 10:59:43 CST 2020
     */
    public void setQuickpass(Integer quickpass) {
        this.quickpass = quickpass;
    }
}