package com.example.demo.service.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns="/*", filterName="loginFilter")
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public static final String LOGINSOURCE = "loginSource";
    public static final String USERID = "userId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {

            HttpSession session = ((HttpServletRequest) request).getSession();
            String requestURI = ((HttpServletRequest) request).getRequestURI();
            String queryString = ((HttpServletRequest) request).getQueryString();
            String url = ((HttpServletRequest) request).getRequestURL().toString();
            //Object token = session.getAttribute("tokenId");
            Object token = ((HttpServletRequest) request).getSession().getAttribute("userId");
            //logger.info("admin url=【{}】,token=【{}】,requestURI={},queryString={}", url, token, requestURI, queryString);

            if (requestURI.contains("/manager/doLogin") //登录action
                    || requestURI.matches("^.*\\.(js|css|html|map|png|jpg|ico)")//静态资源
            ) {//登录页不能拦截
                chain.doFilter(request, response);
                return;
            }
            if (token == null) {
                //logger.info("未登录--" + requestURI);
                //跳转到未登录接口
                ((HttpServletResponse)response).sendRedirect("/login.html");
                //noLogin(response, requestURI);
                return;
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void destroy() {

    }

}