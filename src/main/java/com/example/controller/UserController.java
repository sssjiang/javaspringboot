package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息Controller
 * 接收请求,响应数据
 */
@RestController //@Controller + @ResponseBody -----> 如果返回的是一个对象/集合 --> 转json --> 响应
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        //1. 调用service,查询用户信息
        List<User> userList = userService.list();

        //2. 响应数据
        return userList;
    }
}
