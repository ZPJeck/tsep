package com.zpjeck.service.impl;

import com.zpjeck.Enum.ResultEnum;
import com.zpjeck.dao.UserMapper;
import com.zpjeck.entity.User;
import com.zpjeck.exception.SellException;
import com.zpjeck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/17 11:33
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;


    @Override
    public User login(String userId, String password) {
        User user = userDao.login(userId, password);
        if (user == null){
            throw new SellException(ResultEnum.LOGIN_ERROR);
        }
        System.out.println(user);
        return user;
    }

    @Override
    public Integer increaseUser(User user ) {
        // 生成 id主键
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(id);
        System.out.println(user);
        Integer result = userDao.increaseUser(user);

        if (result == null){
            throw new SellException(ResultEnum.INCREASE_USER);
        }
        return result;
    }

    @Override
    public Integer delectUser(User user) {

        Integer result = userDao.delectUser(user);
        if (result == null){
            throw new SellException(ResultEnum.DELETE_USER);
        }
        return result;
    }

    @Override
    public Integer updateUser(User user) {
        System.out.println("hajskdhksjahdk"+user);

        Integer result = userDao.updateUser(user);
        if (result == null){
            throw new SellException(ResultEnum.UPDATE_USER);
        }
        return result;
    }

    @Override
    public User selectUser(User user) {
        System.out.println("****************"+user);
        User result = userDao.selectUser(user);
        return result;
    }

    @Override
    public List<User> usersList(User user) {
        List<User> userList = userDao.usersList(user);
        return userList;
    }
}
