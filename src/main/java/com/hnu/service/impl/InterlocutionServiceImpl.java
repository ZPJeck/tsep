package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.dao.InterlocutionMapper;
import com.hnu.dao.TeacherClassMapper;
import com.hnu.model.Clazz;
import com.hnu.model.Interlocution;
import com.hnu.model.Teacher;
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

    @Autowired
    private TeacherServiceImpl teacherService;

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
        Interlocution interlocution = interlocutionMapper.selectByPrimaryKey(id);
        try{
            Teacher teacher = teacherService.findByTeacher(interlocution.getUpdateby());
            interlocution.setUpdateby(teacher.getName());
        }catch (Exception e){
            return null;
        }


        if (interlocution.getType().equals("0")){
            interlocution.setType("心得体会");
        }else {
            interlocution.setType("问答解疑");
        }


        return interlocution;
    }

    @Override
    public Result<Interlocution> list(Integer  pageNum, Integer  pageSize, String studentId,String type) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<Interlocution> list = interlocutionMapper.list(studentId,type);
        for (Interlocution interlocution : list){
            if (interlocution.getReply() != null){
                Teacher teacher = teacherService.findByTeacher(interlocution.getUpdateby());
                interlocution.setUpdateby(teacher.getName());
            }
            if (type.equals("0")){
                interlocution.setType("心得体会");
            }else {
                interlocution.setType("问答解疑");
            }
        }
        return ResultUtil.success(list,list.size());
    }

    @Override
    public Result<Interlocution> listByClass(Integer  pageNum,Integer  pageSize,String teacherId) {
        List<Clazz> clazz = teacherClassMapper.findByTeacherId(teacherId);
        String classId = clazz.get(0).getId();
        PageHelper.startPage(pageNum,pageSize);
        List<Interlocution> list = interlocutionMapper.listByClass(classId);
        return ResultUtil.success(list,list.size());
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
