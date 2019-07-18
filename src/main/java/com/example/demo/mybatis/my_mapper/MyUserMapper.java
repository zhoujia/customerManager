package com.example.demo.mybatis.my_mapper;

import com.example.demo.bizbean.user.BizUser;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MyUserMapper {

    @SelectProvider(type = MyUserProvider.class, method = "queryUser")
    List<BizUser> queryUser(Map<String, Object> param);
}
