package com.hnu.service;

import com.github.pagehelper.PageInfo;
import com.hnu.model.Interlocution;
import com.hnu.util.Result;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 09:15
 * @Description:
 */
public interface InterlocutionService {

    /*
     *  添加问答或者心得
     */
    int add(Interlocution interlocution);

    /*
     *  根据id 查看心得或者问答
     */
    Interlocution findById(String id);

    /*
     *  显示分页列表
     */
    Result<Interlocution> list(Integer  pageNum, Integer  pageSize, String studentId);

    /*
     *  老师根据班级显示分页列表
     */
    PageInfo<Interlocution> listByClass(Integer  pageNum,Integer  pageSize,String teacherId);

    /*
     *  老师回复学生，反馈
     */
    int replyStudent(Interlocution interlocution);

    /*
     *  删除心得或者问答
     */
    int deleteById(String id);
}
