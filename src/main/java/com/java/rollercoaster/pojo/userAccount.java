package com.java.rollercoaster.pojo;

public class userAccount {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone_number
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_name
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_gender
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    private String userGender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_age
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    private Integer userAge;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.role
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    private String role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone_number
     *
     * @return the value of user.phone_number
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone_number
     *
     * @param phoneNumber the value for user.phone_number
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_name
     *
     * @return the value of user.user_name
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_name
     *
     * @param userName the value for user.user_name
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_gender
     *
     * @return the value of user.user_gender
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public String getUserGender() {
        return userGender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_gender
     *
     * @param userGender the value for user.user_gender
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_age
     *
     * @return the value of user.user_age
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public Integer getUserAge() {
        return userAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_age
     *
     * @param userAge the value for user.user_age
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.role
     *
     * @return the value of user.role
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.role
     *
     * @param role the value for user.role
     *
     * @mbggenerated Fri Nov 06 12:17:04 CST 2020
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}