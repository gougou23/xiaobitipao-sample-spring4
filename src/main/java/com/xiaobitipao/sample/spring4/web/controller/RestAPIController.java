package com.xiaobitipao.sample.spring4.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaobitipao.sample.spring4.model.User;
import com.xiaobitipao.sample.spring4.service.UserService;

@RestController
@RequestMapping("/json")
public class RestAPIController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User showProfile(@PathVariable("userid") String userid, Model model) {
        return userService.findUser(userid);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> showList(Model model) {
        return userService.findUsers();
    }
}
