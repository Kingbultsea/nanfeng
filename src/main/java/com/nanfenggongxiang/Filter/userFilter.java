package com.nanfenggongxiang.Filter;

import com.nanfenggongxiang.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by skyisbule on 2018/4/10.
 * 用户登录的拦截器
 * 检测用户cookie的有效性
 */
//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/private/**",filterName = "userFilter")
public class userFilter implements Filter{

    @Autowired
    loginService loginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return;
        int uid = 1;
        String session = "session";
        //从cookie里拿到用户的uid和session
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("uid")){
                uid = Integer.parseInt(cookie.getValue());
            }
            if (cookie.getName().equals("session"))
                session = cookie.getValue();
        }
        if (loginService.auth(uid,session)){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }


}
