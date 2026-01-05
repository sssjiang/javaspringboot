package com.example.service;

import com.example.pojo.User;

import java.util.List;

/**
 * 用户业务逻辑接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return 用户列表
     */
    List<User> list();
}

