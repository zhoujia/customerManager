package com.example.demo.service;

import com.example.demo.bizbean.ResponseBean;
import com.example.demo.mybatis.mapper.ManagerMapper;
import com.example.demo.mybatis.model.Manager;
import com.example.demo.mybatis.model.ManagerExample;
import com.example.demo.utils.DataErrorCode;
import com.example.demo.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    public ResponseBean<Manager> login(String userName, String pwd) {
        ResponseBean<Manager> responseBean = new ResponseBean<>();
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andAccountEqualTo(userName);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if (managers == null || managers.size() == 0) {
            responseBean.setCodeAndMsg(DataErrorCode.CODE_200103.getCode(),DataErrorCode.CODE_200103.getMsg());
            return responseBean;
        }
        Manager manager = managers.get(0);
        if (manager.getPasswd().equals(MD5.encode(pwd))) {
            manager.setLoginTime(new Date());
            managerMapper.updateByPrimaryKey(manager);
            responseBean.setData(manager);
        }
        return responseBean;
    }
}
