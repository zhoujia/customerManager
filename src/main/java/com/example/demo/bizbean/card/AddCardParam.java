package com.example.demo.bizbean.card;

import com.example.demo.mybatis.model.Card;
import com.example.demo.utils.DateUtil;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddCardParam {
    @NotNull
    private String cardName;
    private Integer cardTimes;
    private Double discount;
    private String description;
    private String startDate;
    private String endDate;

    public Card getCard() {
        Card card = new Card();
        card.setCardName(cardName);
        card.setCardTimes(cardTimes);
        card.setDiscount(discount);
        card.setDescription(description);
        card.setStartDate(DateUtil.StringToDate(startDate,"MM/dd/yyyy"));
        card.setEndDate(DateUtil.StringToDate(endDate,"MM/dd/yyyy"));
        Date date = new Date();
        card.setCreateTime(date);
        card.setUpdateTime(date);
        return card;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
