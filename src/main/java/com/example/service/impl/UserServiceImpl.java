package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.pojo.User;
import com.example.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 用户业务逻辑实现类
 */
public class UserServiceImpl implements UserService {

    // 先不用IOC/DI，直接通过new创建Dao实现类
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> list() {
        //1. 调用dao获取数据
        List<String> lines = userDao.list();

        //2. 解析数据, 封装成对象 --> 集合
        List<User> userList = lines.stream()
                .filter(line -> line != null && !line.trim().isEmpty()) // 过滤空行
                .map(line -> {
                    String[] split = line.split(",");
                    // 验证数据格式：确保有6个字段
                    if (split.length != 6) {
                        throw new IllegalArgumentException("数据格式错误，行内容: " + line);
                    }
                    return new User(
                            Integer.parseInt(split[0].trim()),
                            split[1].trim(),
                            split[2].trim(),
                            split[3].trim(),
                            Integer.parseInt(split[4].trim()),
                            LocalDateTime.parse(split[5].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                })
                .toList();

        return userList;
    }
}

