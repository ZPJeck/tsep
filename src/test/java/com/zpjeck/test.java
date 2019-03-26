package com.zpjeck;

import com.zpjeck.entity.User;
import com.zpjeck.service.UserService;
import com.zpjeck.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.UUID;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/12 11:25
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    private final static String userId = "1615925603";
    @Test
    public void simpleTest(){
        User user = new User();
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(id);
        user.setClassName("16云计算一班");
        System.out.println(user);
//        user.setId();

    }



}
