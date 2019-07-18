package com.example.demo.service;

import com.example.demo.bizbean.ResponseBean;
import com.example.demo.bizbean.card.AddCardParam;
import com.example.demo.mybatis.mapper.CardMapper;
import com.example.demo.mybatis.model.Card;
import com.example.demo.mybatis.model.CardExample;
import com.example.demo.mybatis.my_mapper.MyCardMapper;
import com.example.demo.utils.DataErrorCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService {

    @Autowired
    private MyCardMapper myCardMapper;

    @Autowired
    private CardMapper cardMapper;

    /**
     * 查询用户信息
     * @param cardName  用户名
     * @param page      页码
     * @param pageSize  每页数量
     * @return ResponseBean
     */
    public ResponseBean<PageInfo<Card>> queryCard(
            String cardName, int page, int pageSize) {
        Map<String, Object> selectParam = new HashMap<>();
        if (cardName != null && !cardName.isEmpty())
            selectParam.put("cardName", cardName);

        PageHelper.startPage(page, pageSize);
        List<Card> userInfos = myCardMapper.queryCard(selectParam);
        PageInfo<Card> date = new PageInfo<>(userInfos);
        ResponseBean<PageInfo<Card>> responseBean = new ResponseBean<>();
        responseBean.setCodeAndMsg(DataErrorCode.SUCCESS.getCode(), DataErrorCode.SUCCESS.getMsg());
        responseBean.setData(date);
        return responseBean;
    }

    public ResponseBean<Boolean> addCard(AddCardParam param) {
        cardMapper.insert(param.getCard());
        return new ResponseBean<>(true);
    }

    public ResponseBean<Boolean> delCard(Integer cardId) {
        int i = cardMapper.deleteByPrimaryKey(cardId);
        if(i==1)
        return new ResponseBean<>(true);
        else return new ResponseBean<>(false);
    }

    public ResponseBean<List<Card>> getAllCards() {
        List<Card> cards = cardMapper.selectByExample(new CardExample());
        return new ResponseBean<>(cards);
    }


}
