package com.xiaobitipao.sample.spring4.service;

import java.util.List;

import com.xiaobitipao.sample.spring4.model.User;

public interface UserService {

    User findUser(String userid);

    List<User> findUsers();

    long saveUser(User user);
}
