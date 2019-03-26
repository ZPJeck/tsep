package com.zpjeck.contraller;

import com.zpjeck.Enum.Comment;
import com.zpjeck.Enum.ResultEnum;
import com.zpjeck.entity.User;
import com.zpjeck.exception.SellException;
import com.zpjeck.pojo.Result;
import com.zpjeck.service.UserService;
import com.zpjeck.util.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2018/12/17 17:12
 * @Description:
 */
@RestController
@ResponseBody
public class UserContraller {

    @Autowired
    private UserService userService;
    /*
       用户登录
     */
    @RequestMapping(value = "/login")
    public Result login(@Param("userId") String userId, @Param("password")String password, HttpSession session){

        User user = userService.login(userId, password);
        if (user != null){
            session.setAttribute("user",user);
        }
        return ResultUtil.success(user);
    }
    /*
        添加用户
     */
    @RequestMapping(value = "/insertUser")
    public Result increaseUser(User user,HttpSession session){
        // 验证用户是否登录
        User userLogin =(User) session.getAttribute("user");
        if(userLogin == null){
            return ResultUtil.error(1,"用户未登录");
        }
        if (userLogin.getULimit() == Comment.CLASSMATE.getCode()){
            return ResultUtil.error(2,"无权限操作");
        }
        Integer result = userService.increaseUser(user);
        if (result == null){
            throw new SellException(ResultEnum.INCREASE_USER);
        }
        return ResultUtil.success();
    }
    /*
        用户登出
     */
    @RequestMapping(value = "/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("user");
        return ResultUtil.success();
    }
    /*
        删除用户
     */
    @RequestMapping(value = "/deleteUser")
    public Result deleteUser(User user,HttpSession session){
        // 验证用户是否登录
        User userLogin =(User) session.getAttribute("user");
        if(userLogin == null){
            return ResultUtil.error(1,"用户未登录");
        }
        // 判断用户权限
        if(user.getULimit() == Comment.CLASSMATE.getCode()){
            return ResultUtil.error(2,"无权限操作");
        }
        Integer result = userService.delectUser(user);
        if(result == null){
            throw new SellException(ResultEnum.DELETE_USER);
        }
        return ResultUtil.success();
    }
    /*
        用户更新数据
     */
    @RequestMapping(value = "/updateUser")
    public Result updateUser(User user,HttpSession session){
        // 验证用户是否登录
        User userLogin =(User) session.getAttribute("user");
        if(userLogin == null){
            return ResultUtil.error(1,"用户未登录");
        }
        // 判断用户权限
        if(userLogin.getULimit() == Comment.CLASSMATE.getCode()){
            return ResultUtil.error(2,"无权限操作");
        }else if (userLogin.getULimit() == Comment.MONITOR.getCode()){
            if (user.getULimit() != Comment.CLASSMATE.getCode()&& user.getClassNum() == userLogin.getClassNum()){
                return ResultUtil.error(2,"更新失败");
            }
        }
        Integer result = userService.updateUser(user);
        if (result == null){
            return ResultUtil.error(1,"更新失败");
        }

        return ResultUtil.success();
    }

    /*
        用户查询
     */
    @RequestMapping(value = "/selectUser")
    public Result<User> selectUser(User user , HttpSession session){

        User userLogin =(User) session.getAttribute("user");
        if(userLogin == null){
            return ResultUtil.error(1,"用户未登录");
        }
        // 判断用户权限
        if(user.getULimit() == Comment.CLASSMATE.getCode()){
            return ResultUtil.error(2,"无权限操作");
        }

        User result = userService.selectUser(user);
        if (result == null){
            return ResultUtil.error(1,"查询结果为空");
        }
        return ResultUtil.success(result);
    }
    /*
          查询所有用户
     */
    @RequestMapping(value = "/list")
    public Result<User> list(User user){

        List<User> users = userService.usersList(user);
        if (StringUtils.isEmpty(users)){
            return ResultUtil.error(1,"查询信息为空");
        }
        return ResultUtil.success(users);
    }

    /*
        用户批量导入
     */
    @RequestMapping(value = "/import")
    public String importUser(MultipartFile file ,HttpSession session){

        int successNum = 0;
        int failureNum = 0;
        StringBuilder failureMsg = new StringBuilder();
        


        return null;
    }




}


