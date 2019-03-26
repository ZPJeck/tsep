package com.zpjeck.service;

import com.zpjeck.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/17 11:30
 * @Description:
 */
public interface UserService {
    /**
     * 用户登录
     */
    User login(@Param("userId") String userId, @Param("password")String password);

    /**
     * 管理用户
     */
    Integer increaseUser(User user);
    Integer delectUser(User user);
    Integer updateUser(User user);
    User selectUser(User user);

    /**
     * 根据班级查询班级人员
     */
    List<User> usersList(User user);
}
