package com.hnu.pojo;

import com.hnu.model.Clazz;
import com.hnu.model.Student;
import lombok.Data;

/**
 * @Auther: Zpjeck
 * @Date: 2019/3/31 10:16
 * @Description:
 */
@Data
public class StudentClass {
    private Student student;
    private Clazz clazz;
}
