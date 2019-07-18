package com.example.demo.service;

import com.example.demo.bizbean.ResponseBean;
import com.example.demo.bizbean.user.BizUser;
import com.example.demo.mybatis.mapper.CardMapper;
import com.example.demo.mybatis.mapper.UserCardInfoMapper;
import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.mybatis.model.Card;
import com.example.demo.mybatis.model.User;
import com.example.demo.mybatis.model.UserCardInfo;
import com.example.demo.mybatis.model.UserCardInfoExample;
import com.example.demo.mybatis.my_mapper.MyUserMapper;
import com.example.demo.utils.DataErrorCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final MyUserMapper myUserMapper;

    private final UserMapper userMapper;

    private final CardMapper cardMapper;

    private final UserCardInfoMapper userCardInfoMapper;

    public UserService(
            MyUserMapper myUserMapper, UserMapper userMapper,
            CardMapper cardMapper, UserCardInfoMapper userCardInfoMapper) {
        this.myUserMapper = myUserMapper;
        this.userMapper = userMapper;
        this.cardMapper = cardMapper;
        this.userCardInfoMapper = userCardInfoMapper;
    }

    /**
     * 查询用户信息
     * @param userName  用户名
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param phone     手机
     * @param page      页码
     * @param pageSize  每页数量
     * @return ResponseBean
     */
    public ResponseBean<PageInfo<BizUser>> queryUser(
            String userName, String phone,
            String startDate, String endDate,
            int page, int pageSize) {
        Map<String, Object> selectParam = new HashMap<>();
        if (userName != null && !userName.isEmpty())
            selectParam.put("userName", userName);
        if (startDate != null && !startDate.isEmpty())
            selectParam.put("startDate", startDate);
        if (endDate != null && !endDate.isEmpty())
            selectParam.put("endDate", endDate);
        if (phone != null && !phone.isEmpty())
            selectParam.put("phone", phone);
        PageHelper.startPage(page, pageSize);
        List<BizUser> userInfos = myUserMapper.queryUser(selectParam);
        PageInfo<BizUser> date = new PageInfo<>(userInfos);
        ResponseBean<PageInfo<BizUser>> responseBean = new ResponseBean<>();
        responseBean.setCodeAndMsg(DataErrorCode.SUCCESS.getCode(), DataErrorCode.SUCCESS.getMsg());
        responseBean.setData(date);
        return responseBean;
    }

    /**
     * 添加用户
     * @param userName
     * @param phone
     * @return
     */
    public ResponseBean<Boolean> addUser(String userName,String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setUserName(userName);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return new ResponseBean<>();

    }

    /**
     * 绑定卡片和用户
     * @param userId
     * @param cardId
     * @return
     */
    public ResponseBean<Boolean> bindUserCard(Integer userId,Integer cardId) {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        Card card = cardMapper.selectByPrimaryKey(cardId);
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            responseBean.setCodeAndMsg(DataErrorCode.CODE_200103.getCode(),DataErrorCode.CODE_200103.getMsg());
            return responseBean;
        }
        if (card == null) {
            responseBean.setCodeAndMsg(DataErrorCode.CODE_300001.getCode(),DataErrorCode.CODE_300001.getMsg());
            return responseBean;
        }
        UserCardInfo userCardInfo = new UserCardInfo();
        userCardInfo.setUserId(userId);
        userCardInfo.setCardId(cardId);
        userCardInfo.setLeftMoney(0.0);
        userCardInfo.setTimes(card.getCardTimes());
        Date nowDate = new Date();
        userCardInfo.setCreateTime(nowDate);
        userCardInfo.setUpdateTime(nowDate);
        int insert = userCardInfoMapper.insert(userCardInfo);
        if (insert == 1) {
            return responseBean;
        }else {
            responseBean.setCodeAndMsg(DataErrorCode.CODE_200101.getCode(),DataErrorCode.CODE_200101.getMsg());
            return responseBean;
        }
    }

    /**
     * 理发次数减一操作
     * @param userId
     * @param cardId
     * @return
     */
    public ResponseBean<Integer> minusOne(Integer userId,Integer cardId) {
        ResponseBean<Integer> responseBean = new ResponseBean<>();
        UserCardInfoExample userCardInfoExample = new UserCardInfoExample();
        userCardInfoExample.createCriteria().andCardIdEqualTo(cardId).andUserIdEqualTo(userId);
        List<UserCardInfo> userCardInfos = userCardInfoMapper.selectByExample(userCardInfoExample);
        if (userCardInfos != null && userCardInfos.size() > 0) {
            UserCardInfo userCardInfo = userCardInfos.get(0);
            if (userCardInfo.getTimes() > 1) {
                userCardInfo.setTimes(userCardInfo.getTimes()-1);
                int i = userCardInfoMapper.updateByPrimaryKey(userCardInfo);
                if (i == 1) {
                    return new ResponseBean<>(userCardInfo.getTimes());
                }
            }else {
                responseBean.setCodeAndMsg(DataErrorCode.CODE_300002.getCode(),DataErrorCode.CODE_300002.getMsg());
                return responseBean;
            }
        }
        responseBean.setCodeAndMsg(DataErrorCode.CODE_200101.getCode(),DataErrorCode.CODE_200101.getMsg());
        return responseBean;

    }
}
