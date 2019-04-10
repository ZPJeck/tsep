package com.hnu.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/10 16:40
 * @Description:
 */
public class DateUtil {

    public static Date date(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);
        return c.getTime();
    }
}
