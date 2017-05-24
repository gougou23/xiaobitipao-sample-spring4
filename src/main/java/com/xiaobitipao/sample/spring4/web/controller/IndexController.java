package com.xiaobitipao.sample.spring4.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("welcome", "Hi, Welcome to Xiaobitipao site");
        return "index";
    }
}
