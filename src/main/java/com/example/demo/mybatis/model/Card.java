package com.example.demo.mybatis.model;

import java.util.Date;

public class Card {
    private Integer id;

    private String cardName;

    private Byte cardLevel;

    private String description;

    private Integer cardTimes;

    private Double discount;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Byte getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(Byte cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCardTimes() {
        return cardTimes;
    }

    public void setCardTimes(Integer cardTimes) {
        this.cardTimes = cardTimes;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}