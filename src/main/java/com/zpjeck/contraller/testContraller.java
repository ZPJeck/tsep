
package com.zpjeck.contraller;

import com.zpjeck.entity.User;
import com.zpjeck.service.UserService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;



@RequestMapping(value = "/test")
public class testContraller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ceshi")
    public String ceshi(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public User loginTest(HttpSession session){

        User user =(User) session.getAttribute("user");

        return user;

    }



    @RequestMapping("/deptImp")
    public String deptImport(@RequestParam MultipartFile mFile) throws IOException, BiffException {
        //获取文件流
        InputStream inputStream = mFile.getInputStream();

        //利用JAR提供的workbook类读取文件
        Workbook workbook = Workbook.getWorkbook(inputStream);

        //获取工作簿
        Sheet sheet = workbook.getSheet(0);
        // 获取总行数
        int rows = sheet.getRows();

        //获取总列数
        int cells = sheet.getColumns();

        User user = null;
        for (int i = 1; i<rows ; i++){
            user = new User();
            Cell[] cell = sheet.getRow(i);
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            user.setId(id);
            String password = "admin";
            user.setPassword(password);
            user.setClassName(cell[0].getContents());
            user.setUserId(cell[1].getContents());
            user.setClassNum(cell[2].getContents());
            user.setULimit(cell[3].getContents());
            userService.increaseUser(user);
        }
        return "redirect:list";
    }


}

