package com.xiaobitipao.sample.spring4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobitipao.sample.spring4.model.User;
import com.xiaobitipao.sample.spring4.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(String userid) {
        return userRepository.findUser(userid);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    @Override
    public long saveUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }
}
