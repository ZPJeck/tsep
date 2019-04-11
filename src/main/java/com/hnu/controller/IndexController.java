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
        if (session.getAttribute("admin") != null){
            return "redirect:/frond/admin/views/admin/index.html";
        }
        return "redirect:/frond/admin/login.html";
    }

    @RequestMapping(value = {"/studentLogin","/"})
    public String login2(){
        if (session.getAttribute("student") != null){
            return "redirect:/frond/admin/views/student/index.html";
        }
        return "redirect:/frond/admin/views/student/login.html";
    }
    @RequestMapping(value = "/tLogin")
    public String login3(){
        if (session.getAttribute("teacher") != null){
            return "redirect:/frond/admin/views/teacher/index.html";
        }
        return "redirect:/frond/admin/views/teacher/login.html";
    }


    @RequestMapping(value = "/logout")
    public String logout(){
        session.removeAttribute("student");
        session.removeAttribute("teacher");
        session.removeAttribute("admin");
        return "forward:/studentLogin";
    }
    /*
     *  返回错误页面500
     */
    @RequestMapping(value = "/five")
    public String five(){

        return "redirect:/frond/admin/error.html";
    }
    /*
     *  返回错误页面404
     */
    @RequestMapping(value = "/four0four")
    public String four0four(){

        return "redirect:/frond/admin/404.html";
    }


}
