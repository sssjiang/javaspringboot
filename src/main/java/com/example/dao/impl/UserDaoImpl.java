package com.example.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.example.dao.UserDao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问实现类
 */
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> list() {
        //1. 加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        List<String> lines = IoUtil.readUtf8Lines(in, new ArrayList<>());
        return lines;
    }
}

