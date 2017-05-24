package com.xiaobitipao.sample.spring4.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    /**
     * <pre>
     * 用户登录处理。
     * 
     * 本系统使用Spring的Security对登录进行认证处理，所以以下几点需要注意：
     * 1.LoginController中只处理Get请求，不处理Post请求，Post请求由Spring Security进行处理
     * 2.不需要提供LoginForm类
     * 3.login.jsp中需要两个input标签分别用于username和password，且必须指定name属性
     * </pre>
     * 
     * @return login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
