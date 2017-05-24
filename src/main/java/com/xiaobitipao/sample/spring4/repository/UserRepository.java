package com.xiaobitipao.sample.spring4.repository;

import java.util.List;

import com.xiaobitipao.sample.spring4.model.User;

public interface UserRepository {

    User findUser(String userid);

    List<User> findUsers();

    long saveUser(User user);
}
