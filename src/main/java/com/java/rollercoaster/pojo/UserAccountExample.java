package com.java.rollercoaster.pojo;

import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import java.util.ArrayList;
import java.util.List;

public class UserAccountExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public UserAccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNull() {
            addCriterion("user_gender is null");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNotNull() {
            addCriterion("user_gender is not null");
            return (Criteria) this;
        }

        public Criteria andUserGenderEqualTo(UserGender value) {
            addCriterion("user_gender =", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotEqualTo(UserGender value) {
            addCriterion("user_gender <>", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThan(UserGender value) {
            addCriterion("user_gender >", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThanOrEqualTo(UserGender value) {
            addCriterion("user_gender >=", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThan(UserGender value) {
            addCriterion("user_gender <", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThanOrEqualTo(UserGender value) {
            addCriterion("user_gender <=", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLike(UserGender value) {
            addCriterion("user_gender like", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotLike(UserGender value) {
            addCriterion("user_gender not like", value, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderIn(List<UserGender> values) {
            addCriterion("user_gender in", values, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotIn(List<UserGender> values) {
            addCriterion("user_gender not in", values, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderBetween(UserGender value1, UserGender value2) {
            addCriterion("user_gender between", value1, value2, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotBetween(UserGender value1, UserGender value2) {
            addCriterion("user_gender not between", value1, value2, "UserGender");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNull() {
            addCriterion("user_age is null");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNotNull() {
            addCriterion("user_age is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgeEqualTo(Integer value) {
            addCriterion("user_age =", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotEqualTo(Integer value) {
            addCriterion("user_age <>", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThan(Integer value) {
            addCriterion("user_age >", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_age >=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThan(Integer value) {
            addCriterion("user_age <", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThanOrEqualTo(Integer value) {
            addCriterion("user_age <=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeIn(List<Integer> values) {
            addCriterion("user_age in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotIn(List<Integer> values) {
            addCriterion("user_age not in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeBetween(Integer value1, Integer value2) {
            addCriterion("user_age between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_age not between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(Role value) {
            addCriterion("role =", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(Role value) {
            addCriterion("role <>", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(Role value) {
            addCriterion("role >", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(Role value) {
            addCriterion("role >=", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(Role value) {
            addCriterion("role <", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(Role value) {
            addCriterion("role <=", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(Role value) {
            addCriterion("role like", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(Role value) {
            addCriterion("role not like", value, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<Role> values) {
            addCriterion("role in", values, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<Role> values) {
            addCriterion("role not in", values, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(Role value1, Role value2) {
            addCriterion("role between", value1, value2, "Role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(Role value1, Role value2) {
            addCriterion("role not between", value1, value2, "Role");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdIsNull() {
            addCriterion("third_party_id is null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdIsNotNull() {
            addCriterion("third_party_id is not null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdEqualTo(String value) {
            addCriterion("third_party_id =", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdNotEqualTo(String value) {
            addCriterion("third_party_id <>", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdGreaterThan(String value) {
            addCriterion("third_party_id >", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdGreaterThanOrEqualTo(String value) {
            addCriterion("third_party_id >=", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdLessThan(String value) {
            addCriterion("third_party_id <", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdLessThanOrEqualTo(String value) {
            addCriterion("third_party_id <=", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdLike(String value) {
            addCriterion("third_party_id like", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdNotLike(String value) {
            addCriterion("third_party_id not like", value, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdIn(List<String> values) {
            addCriterion("third_party_id in", values, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdNotIn(List<String> values) {
            addCriterion("third_party_id not in", values, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdBetween(String value1, String value2) {
            addCriterion("third_party_id between", value1, value2, "thirdPartyId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdNotBetween(String value1, String value2) {
            addCriterion("third_party_id not between", value1, value2, "thirdPartyId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userAccount
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 09 19:59:54 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userAccount
     *
     * @mbggenerated Mon Nov 09 19:59:54 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}