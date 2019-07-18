package com.example.demo.mybatis.my_mapper;

import com.example.demo.mybatis.model.Card;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MyCardMapper {
    @SelectProvider(type = MyCardProvider.class, method = "queryCard")
    List<Card> queryCard(Map<String, Object> param);
}
