package com.rfid.netty.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LabelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LabelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andLabelIdIsNull() {
            addCriterion("label_id is null");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNotNull() {
            addCriterion("label_id is not null");
            return (Criteria) this;
        }

        public Criteria andLabelIdEqualTo(Integer value) {
            addCriterion("label_id =", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotEqualTo(Integer value) {
            addCriterion("label_id <>", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThan(Integer value) {
            addCriterion("label_id >", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("label_id >=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThan(Integer value) {
            addCriterion("label_id <", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThanOrEqualTo(Integer value) {
            addCriterion("label_id <=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdIn(List<Integer> values) {
            addCriterion("label_id in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotIn(List<Integer> values) {
            addCriterion("label_id not in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdBetween(Integer value1, Integer value2) {
            addCriterion("label_id between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("label_id not between", value1, value2, "labelId");
            return (Criteria) this;
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

        public Criteria andLabelCodeIsNull() {
            addCriterion("label_code is null");
            return (Criteria) this;
        }

        public Criteria andLabelCodeIsNotNull() {
            addCriterion("label_code is not null");
            return (Criteria) this;
        }

        public Criteria andLabelCodeEqualTo(String value) {
            addCriterion("label_code =", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeNotEqualTo(String value) {
            addCriterion("label_code <>", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeGreaterThan(String value) {
            addCriterion("label_code >", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("label_code >=", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeLessThan(String value) {
            addCriterion("label_code <", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeLessThanOrEqualTo(String value) {
            addCriterion("label_code <=", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeLike(String value) {
            addCriterion("label_code like", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeNotLike(String value) {
            addCriterion("label_code not like", value, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeIn(List<String> values) {
            addCriterion("label_code in", values, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeNotIn(List<String> values) {
            addCriterion("label_code not in", values, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeBetween(String value1, String value2) {
            addCriterion("label_code between", value1, value2, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelCodeNotBetween(String value1, String value2) {
            addCriterion("label_code not between", value1, value2, "labelCode");
            return (Criteria) this;
        }

        public Criteria andLabelNameIsNull() {
            addCriterion("label_name is null");
            return (Criteria) this;
        }

        public Criteria andLabelNameIsNotNull() {
            addCriterion("label_name is not null");
            return (Criteria) this;
        }

        public Criteria andLabelNameEqualTo(String value) {
            addCriterion("label_name =", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotEqualTo(String value) {
            addCriterion("label_name <>", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThan(String value) {
            addCriterion("label_name >", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThanOrEqualTo(String value) {
            addCriterion("label_name >=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThan(String value) {
            addCriterion("label_name <", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThanOrEqualTo(String value) {
            addCriterion("label_name <=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLike(String value) {
            addCriterion("label_name like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotLike(String value) {
            addCriterion("label_name not like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameIn(List<String> values) {
            addCriterion("label_name in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotIn(List<String> values) {
            addCriterion("label_name not in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameBetween(String value1, String value2) {
            addCriterion("label_name between", value1, value2, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotBetween(String value1, String value2) {
            addCriterion("label_name not between", value1, value2, "labelName");
            return (Criteria) this;
        }

        public Criteria andLastLocalIsNull() {
            addCriterion("last_local is null");
            return (Criteria) this;
        }

        public Criteria andLastLocalIsNotNull() {
            addCriterion("last_local is not null");
            return (Criteria) this;
        }

        public Criteria andLastLocalEqualTo(String value) {
            addCriterion("last_local =", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalNotEqualTo(String value) {
            addCriterion("last_local <>", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalGreaterThan(String value) {
            addCriterion("last_local >", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalGreaterThanOrEqualTo(String value) {
            addCriterion("last_local >=", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalLessThan(String value) {
            addCriterion("last_local <", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalLessThanOrEqualTo(String value) {
            addCriterion("last_local <=", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalLike(String value) {
            addCriterion("last_local like", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalNotLike(String value) {
            addCriterion("last_local not like", value, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalIn(List<String> values) {
            addCriterion("last_local in", values, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalNotIn(List<String> values) {
            addCriterion("last_local not in", values, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalBetween(String value1, String value2) {
            addCriterion("last_local between", value1, value2, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastLocalNotBetween(String value1, String value2) {
            addCriterion("last_local not between", value1, value2, "lastLocal");
            return (Criteria) this;
        }

        public Criteria andLastImageIsNull() {
            addCriterion("last_image is null");
            return (Criteria) this;
        }

        public Criteria andLastImageIsNotNull() {
            addCriterion("last_image is not null");
            return (Criteria) this;
        }

        public Criteria andLastImageEqualTo(String value) {
            addCriterion("last_image =", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageNotEqualTo(String value) {
            addCriterion("last_image <>", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageGreaterThan(String value) {
            addCriterion("last_image >", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageGreaterThanOrEqualTo(String value) {
            addCriterion("last_image >=", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageLessThan(String value) {
            addCriterion("last_image <", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageLessThanOrEqualTo(String value) {
            addCriterion("last_image <=", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageLike(String value) {
            addCriterion("last_image like", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageNotLike(String value) {
            addCriterion("last_image not like", value, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageIn(List<String> values) {
            addCriterion("last_image in", values, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageNotIn(List<String> values) {
            addCriterion("last_image not in", values, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageBetween(String value1, String value2) {
            addCriterion("last_image between", value1, value2, "lastImage");
            return (Criteria) this;
        }

        public Criteria andLastImageNotBetween(String value1, String value2) {
            addCriterion("last_image not between", value1, value2, "lastImage");
            return (Criteria) this;
        }

        public Criteria andNewImgIsNull() {
            addCriterion("new_img is null");
            return (Criteria) this;
        }

        public Criteria andNewImgIsNotNull() {
            addCriterion("new_img is not null");
            return (Criteria) this;
        }

        public Criteria andNewImgEqualTo(Short value) {
            addCriterion("new_img =", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgNotEqualTo(Short value) {
            addCriterion("new_img <>", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgGreaterThan(Short value) {
            addCriterion("new_img >", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgGreaterThanOrEqualTo(Short value) {
            addCriterion("new_img >=", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgLessThan(Short value) {
            addCriterion("new_img <", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgLessThanOrEqualTo(Short value) {
            addCriterion("new_img <=", value, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgIn(List<Short> values) {
            addCriterion("new_img in", values, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgNotIn(List<Short> values) {
            addCriterion("new_img not in", values, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgBetween(Short value1, Short value2) {
            addCriterion("new_img between", value1, value2, "newImg");
            return (Criteria) this;
        }

        public Criteria andNewImgNotBetween(Short value1, Short value2) {
            addCriterion("new_img not between", value1, value2, "newImg");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNull() {
            addCriterion("last_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("last_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andXIsNull() {
            addCriterion("X is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("X is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Integer value) {
            addCriterion("X =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Integer value) {
            addCriterion("X <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Integer value) {
            addCriterion("X >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Integer value) {
            addCriterion("X >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Integer value) {
            addCriterion("X <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Integer value) {
            addCriterion("X <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Integer> values) {
            addCriterion("X in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Integer> values) {
            addCriterion("X not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Integer value1, Integer value2) {
            addCriterion("X between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Integer value1, Integer value2) {
            addCriterion("X not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("Y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("Y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Integer value) {
            addCriterion("Y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Integer value) {
            addCriterion("Y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Integer value) {
            addCriterion("Y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Integer value) {
            addCriterion("Y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Integer value) {
            addCriterion("Y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Integer value) {
            addCriterion("Y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Integer> values) {
            addCriterion("Y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Integer> values) {
            addCriterion("Y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Integer value1, Integer value2) {
            addCriterion("Y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Integer value1, Integer value2) {
            addCriterion("Y not between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andWantedIsNull() {
            addCriterion("wanted is null");
            return (Criteria) this;
        }

        public Criteria andWantedIsNotNull() {
            addCriterion("wanted is not null");
            return (Criteria) this;
        }

        public Criteria andWantedEqualTo(Short value) {
            addCriterion("wanted =", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedNotEqualTo(Short value) {
            addCriterion("wanted <>", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedGreaterThan(Short value) {
            addCriterion("wanted >", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedGreaterThanOrEqualTo(Short value) {
            addCriterion("wanted >=", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedLessThan(Short value) {
            addCriterion("wanted <", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedLessThanOrEqualTo(Short value) {
            addCriterion("wanted <=", value, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedIn(List<Short> values) {
            addCriterion("wanted in", values, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedNotIn(List<Short> values) {
            addCriterion("wanted not in", values, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedBetween(Short value1, Short value2) {
            addCriterion("wanted between", value1, value2, "wanted");
            return (Criteria) this;
        }

        public Criteria andWantedNotBetween(Short value1, Short value2) {
            addCriterion("wanted not between", value1, value2, "wanted");
            return (Criteria) this;
        }

        public Criteria andFoundIsNull() {
            addCriterion("found is null");
            return (Criteria) this;
        }

        public Criteria andFoundIsNotNull() {
            addCriterion("found is not null");
            return (Criteria) this;
        }

        public Criteria andFoundEqualTo(Short value) {
            addCriterion("found =", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundNotEqualTo(Short value) {
            addCriterion("found <>", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundGreaterThan(Short value) {
            addCriterion("found >", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundGreaterThanOrEqualTo(Short value) {
            addCriterion("found >=", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundLessThan(Short value) {
            addCriterion("found <", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundLessThanOrEqualTo(Short value) {
            addCriterion("found <=", value, "found");
            return (Criteria) this;
        }

        public Criteria andFoundIn(List<Short> values) {
            addCriterion("found in", values, "found");
            return (Criteria) this;
        }

        public Criteria andFoundNotIn(List<Short> values) {
            addCriterion("found not in", values, "found");
            return (Criteria) this;
        }

        public Criteria andFoundBetween(Short value1, Short value2) {
            addCriterion("found between", value1, value2, "found");
            return (Criteria) this;
        }

        public Criteria andFoundNotBetween(Short value1, Short value2) {
            addCriterion("found not between", value1, value2, "found");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Short value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Short value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Short value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Short value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Short value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Short value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Short> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Short> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Short value1, Short value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Short value1, Short value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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