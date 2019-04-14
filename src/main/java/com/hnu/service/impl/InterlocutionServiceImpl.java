package com.hnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnu.Enum.Comment;
import com.hnu.dao.InterlocutionMapper;
import com.hnu.dao.TeacherClassMapper;
import com.hnu.model.Clazz;
import com.hnu.model.Interlocution;
import com.hnu.model.Teacher;
import com.hnu.pojo.StudentClass;
import com.hnu.service.InterlocutionService;
import com.hnu.service.StudentService;
import com.hnu.util.Result;
import com.hnu.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpSession session;

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
    public Interlocution findByIdTeacher(String id) {
        Interlocution interlocution = interlocutionMapper.selectByPrimaryKey(id);
        if (interlocution.getType().equals("0")){
            interlocution.setType("心得体会");
        }else {
            interlocution.setType("问答解疑");
        }
        try{
            Teacher teacher = teacherService.findByTeacher(interlocution.getUpdateby());
            interlocution.setUpdateby(teacher.getName());
        }catch (Exception e){
            return interlocution;
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
        PageInfo<Interlocution> list1 = new PageInfo<Interlocution>(list);
        int total = (int) list1.getTotal();

        return ResultUtil.success(list,total);
    }

    @Override
    public Result<Interlocution> listByClass(Integer  pageNum,Integer  pageSize,String teacherId) {

        List<Interlocution> list = new ArrayList<>();
        List<Clazz> clazzList = teacherClassMapper.findByTeacherId(teacherId);
        for (Clazz clazz : clazzList){
            String classId = clazz.getId();
            PageHelper.startPage(pageNum,pageSize);
             list = interlocutionMapper.listByClass(classId);
        }
        PageInfo<Interlocution> list1 = new PageInfo<Interlocution>(list);
        int total = (int) list1.getTotal();

        return ResultUtil.success(list,total);
    }

    @Override
    public int replyStudent(Interlocution interlocution) {
        if ("".equals(interlocution.getId())){
            return Comment.FAIL.getCode();
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        interlocution.setUpdateby(teacher.getId());
        interlocution.setUpdatetime(new Date());
        return interlocutionMapper.replyStudent(interlocution);
    }

    @Override
    public int deleteById(String id) {
        if ("".equals(id)){
            return Comment.FAIL.getCode();
        }
        return interlocutionMapper.deleteById(id);
    }

    @Override
    public Result interLnList(Integer page, Integer limit, String type, String classId) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        PageHelper.startPage(page,limit,true);
        List<Interlocution> interlocutionList = new ArrayList<>();
        if (classId == null){
            interlocutionList = interlocutionMapper.findListByType(type, teacher.getId());
        }else{
            interlocutionList = interlocutionMapper.findListByClass(type,teacher.getId(),classId);
        }
        // 把回复人的id转换成姓名
        for (Interlocution interlocution : interlocutionList){
            StudentClass studentClass = studentService.selectByPrimaryKey(interlocution.getStudentId());
            interlocution.setStudentId(studentClass.getName() );
            if (interlocution.getUpdateby() != null){
                interlocution.setUpdateby(teacherService.findByTeacher(interlocution.getUpdateby()).getName());
            }
        }
        PageInfo<Interlocution> list = new PageInfo<Interlocution>(interlocutionList);
        int total = (int) list.getTotal();
        return ResultUtil.success(interlocutionList,total);
    }








}
