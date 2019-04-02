package com.hnu.controller;

import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/26 20:10
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login")
    public String login(){

        return "redirect:/frond/admin/login.html";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        session.removeAttribute("student");
        session.removeAttribute("teacher");
        session.removeAttribute("admin");
        return "redirect:/frond/admin/login.html";
    }
}
