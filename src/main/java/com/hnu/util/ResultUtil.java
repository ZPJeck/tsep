package com.hnu.util;

import com.hnu.pojo.Result;

/**
 * @Auther: Zpjeck
 * @Date: 2018/11/27 08:48
 * @Description:  对返回对象的封装。
 */
public class ResultUtil {

    /*
        返回成功
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result success() {
        return success(null);
    }
    public static Result error(Integer code , String msg){
        Result result = new Result();
        result.setCode(code);
        result.setData(msg);
        return result;
    }


}
