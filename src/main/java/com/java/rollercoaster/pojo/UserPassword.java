package com.java.rollercoaster.pojo;

public class UserPassword {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column password.user_id
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column password.password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column password.user_id
     *
     * @return the value of password.user_id
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column password.user_id
     *
     * @param userId the value for password.user_id
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column password.password
     *
     * @return the value of password.password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column password.password
     *
     * @param password the value for password.password
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}