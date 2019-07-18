package com.example.demo.mybatis.my_mapper;

import java.util.Map;

public class MyUserProvider {

    public String queryUser(Map<String, Object> param) {
        String whereCondition = this.getWhereCondition(param);

        return "SELECT u.id id,u.user_name userName, u.nickname nickName,u.phone phone,u.real_name realName,u.gander gander," +
                "u.birthday birthday, uci.times times , uci.left_money leftMoney,c.id cardId, " +
                " u.update_time update_time, u.create_time createTime,c.card_name cardName " +
                " from user u " +
                " left join user_card_info uci on u.id = uci.user_id " +
                " left join card c on c.id = uci.card_id " +
                "where " + whereCondition;
    }

    private String getWhereCondition(Map<String,Object> param) {
        StringBuilder condition = new StringBuilder(" 1=1 ");

        if (param.get("userName") != null && !String.valueOf(param.get("userName")).equals("")) {
            condition.append(" and u.user_name like concat('%',#{userName},'%') ");
        }
        if (param.get("startDate") != null ) {
            condition.append(" and u.create_time >= #{startDate} ");
        }
        if (param.get("endDate") != null ) {
            condition.append(" and u.create_time <= #{endDate} ");
        }
        if (param.get("phone") != null && !String.valueOf(param.get("phone")).equals("")) {
            condition.append(" and u.phone like concat('%',#{phone},'%') ");
        }
        return condition.append(" order by u.create_time desc ").toString();
    }
}
