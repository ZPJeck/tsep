package com.hnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/26 20:10
 * @Description:
 */
@Controller
public class testController {

    @RequestMapping(value = "/login")
    public String test(){

        return "redirect:/frond/admin/login.html";
    }
}
