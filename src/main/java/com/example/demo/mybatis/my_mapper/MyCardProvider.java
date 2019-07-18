package com.example.demo.mybatis.my_mapper;

import java.util.Map;

public class MyCardProvider {

    public String queryCard(Map<String, Object> param) {
        String whereCondition = this.getWhereCondition(param);
        return "SELECT c.id id,c.card_name cardName, c.description description,c.card_times cardTimes,c.discount discount," +
                "c.start_date startDate, c.end_date endDate,c.create_time createTime, c.update_time updateTime " +
                "from card c  " +
                "where " + whereCondition;
    }

    private String getWhereCondition(Map<String,Object> param) {
        StringBuilder condition = new StringBuilder(" 1=1 ");
        if (param.get("cardName") != null && !String.valueOf(param.get("cardName")).equals("")) {
            condition.append(" and c.card_name like concat('%',#{cardName},'%') ");
        }
        return condition.append(" order by c.create_time desc ").toString();
    }
}
