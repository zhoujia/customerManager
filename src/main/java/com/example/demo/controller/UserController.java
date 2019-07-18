package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bizbean.ResponseBean;
import com.example.demo.bizbean.user.BizUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

//@Controller
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/list",method= RequestMethod.GET)
    public ModelAndView userList(Model model) {
        logger.info("/user/list........");
        return new ModelAndView("/user/user_list.html");
    }


    /**
     * 查询 用户数据
     * @param userName
     * @param phone
     * @param startDate
     * @param endDate
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/user/list_data",method= RequestMethod.GET)
    public String userListData(
            String userName, String phone,
            String startDate, String endDate,
            int page, int rows
    ) {
        ResponseBean<PageInfo<BizUser>> pageInfoResponseBean = userService.queryUser(userName, phone, startDate, endDate, page, rows);
        Map<String, Object> map = new HashMap<>();
        //easyUI 分页返回的数据格式为 {total:1,tow{xx}},做格式转换
        map.put("rows", pageInfoResponseBean.getData().getList());
        map.put("total",pageInfoResponseBean.getData().getTotal());
        return JSONObject.toJSONString(map);
    }

    @RequestMapping(value="/user/add",method= RequestMethod.POST)
    public ResponseBean<Boolean> addUser(
            String userName, String phone
    ) {
        return userService.addUser(userName, phone);
    }

    @RequestMapping(value="/user/bindUserCard",method= RequestMethod.POST)
    public ResponseBean<Boolean> bindUserCard(
            Integer userId,Integer cardId
    ) {
        return userService.bindUserCard(userId, cardId);
    }

    @RequestMapping(value="/user/minusOne",method= RequestMethod.POST)
    public ResponseBean<Integer> minusOne(
            Integer userId,Integer cardId
    ) {
        return userService.minusOne(userId, cardId);
    }




}
