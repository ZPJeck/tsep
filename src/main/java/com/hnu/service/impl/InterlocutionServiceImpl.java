package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.dao.InterlocutionMapper;
import com.hnu.dao.TeacherClassMapper;
import com.hnu.model.Clazz;
import com.hnu.model.Interlocution;
import com.hnu.service.InterlocutionService;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zpjeck
 * @Date: 2019/4/1 09:16
 * @Description:
 */
@Service
public class InterlocutionServiceImpl implements InterlocutionService {

    @Autowired
    private InterlocutionMapper interlocutionMapper;

    @Autowired
    private TeacherClassMapper teacherClassMapper;

    @Override
    public int add(Interlocution interlocution) {
        if ("".equals(interlocution.getId()) ){
            // 添加失败
            return Comment.FAIL.getCode();
        }
        return interlocutionMapper.insert(interlocution);
    }

    @Override
    public Interlocution findById(String id) {
        return interlocutionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result<Interlocution> list(Integer  pageNum, Integer  pageSize, String studentId) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<Interlocution> list = interlocutionMapper.list(studentId);

        return ResultUtil.success(list,list.size());
    }

    @Override
    public PageInfo<Interlocution> listByClass(Integer  pageNum,Integer  pageSize,String teacherId) {
        Clazz clazz = teacherClassMapper.findByTeacherId(teacherId);
        String classId = clazz.getId();
        PageHelper.startPage(pageNum,pageSize);
        List<Interlocution> list = interlocutionMapper.listByClass(classId);
        return new PageInfo<>(list);
    }

    @Override
    public int replyStudent(Interlocution interlocution) {
        if ("".equals(interlocution.getId())){
            return Comment.FAIL.getCode();
        }
        return interlocutionMapper.replyStudent(interlocution);
    }

    @Override
    public int deleteById(String id) {
        if ("".equals(id)){
            return Comment.FAIL.getCode();
        }
        return interlocutionMapper.deleteById(id);
    }
}
