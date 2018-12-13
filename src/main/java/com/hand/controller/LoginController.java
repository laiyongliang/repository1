package com.hand.controller;

import com.hand.dao.UserMapper;
import com.hand.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @ResponseBody
    @RequestMapping("ll")
    public User login(){
        User user = userMapper.findById(1);
        return user;
    }

    @ResponseBody
    @RequestMapping("all")
    public List<User> findAll(){
        List<User> all = userMapper.findAll();
        return all;
    }
}
