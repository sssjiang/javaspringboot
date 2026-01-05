package com.example.dao;

import java.util.List;

/**
 * 用户数据访问接口
 */
public interface UserDao {
    /**
     * 查询所有用户数据（原始字符串列表）
     * @return 用户数据行列表
     */
    List<String> list();
}

