package com.example.demo.controller;

import com.example.demo.bizbean.ResponseBean;
import com.example.demo.mybatis.model.Manager;
import com.example.demo.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value="/manager/login",method= RequestMethod.GET)
    public ModelAndView userLogin(Model model) {
        return new ModelAndView("/login.html");
    }

    @RequestMapping(value="/manager/doLogin",method= RequestMethod.POST)
    public void login(String userName, String pwd,
                      HttpServletResponse response,
                      ServletRequest request) {
        ResponseBean<Manager> login = managerService.login(userName, pwd);
        try {
            if(login.getRetCode().equals("000000")) {
                ((HttpServletRequest)request).getSession().setAttribute("userId",login.getData().getId());
                response.sendRedirect("/index.html");
            }else {
                response.sendRedirect("/login.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/manager/logout",method= RequestMethod.GET)
    public void logout(
                      HttpServletResponse response,
                      ServletRequest request) {
        ((HttpServletRequest)request).getSession().removeAttribute("userId");
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
