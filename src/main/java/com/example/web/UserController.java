package com.example.web;

import com.example.mapper.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    @ResponseBody
    String getStr(){
        return "hello";
    }

    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    @ResponseBody
    public User getUser(){
        return userServiceImpl.getUser();
    }

}
