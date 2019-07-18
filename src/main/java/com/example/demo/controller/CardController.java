package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bizbean.ResponseBean;
import com.example.demo.bizbean.card.AddCardParam;
import com.example.demo.bizbean.card.DropListDate;
import com.example.demo.mybatis.model.Card;
import com.example.demo.service.CardService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @RequestMapping(value="/card/list",method= RequestMethod.GET)
    public ModelAndView userList(Model model) {
        logger.info("/card/list........");
        return new ModelAndView("/card/card_list.html");
    }

    /**
     * 查询 用户数据
     * @param cardName 卡片名称
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/card/list_data",method= RequestMethod.GET)
    public String cardListData(
            String cardName,
            int page, int rows
    ) {
        ResponseBean<PageInfo<Card>> pageInfoResponseBean = cardService.queryCard(cardName, page, rows);
        Map<String, Object> map = new HashMap<>();
        //easyUI 分页返回的数据格式为 {total:1,tow{xx}},做格式转换
        map.put("rows", pageInfoResponseBean.getData().getList());
        map.put("total",pageInfoResponseBean.getData().getTotal());
        return JSONObject.toJSONString(map);
    }

    /**
     * 添加卡片
     * @param addCardParam 卡片
     * @return
     */
    @RequestMapping(value="/card/add",method= RequestMethod.POST)
    public ResponseBean<Boolean> addCard(@Valid AddCardParam addCardParam) {
        return cardService.addCard(addCardParam);
    }

    /**
     * 删除卡片
     * @param cardId  卡片id
     * @return
     */
    @RequestMapping(value="/card/del",method= RequestMethod.GET)
    public ResponseBean<Boolean> addCard(@Valid Integer  cardId) {
        return cardService.delCard(cardId);
    }

    /**
     * 查询所有的卡片
     * @return
     */
    @RequestMapping(value="/card/getAllCards",method= RequestMethod.GET)
    public String getAllCards() {
        ResponseBean<List<Card>> allCards = cardService.getAllCards();
        List<Card> data = allCards.getData();
        List<DropListDate> listDate = new ArrayList<>();
        for (Card card : data) {
            DropListDate date = new DropListDate();
            date.setId(card.getId());
            date.setText(card.getCardName());
            listDate.add(date);
        }
        return JSONObject.toJSONString(listDate);
    }



}
