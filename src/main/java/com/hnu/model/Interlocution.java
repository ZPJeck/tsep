package com.hnu.model;

import java.io.Serializable;
import java.util.Date;

public class Interlocution implements Serializable {
    private String id;

    private String studentId;

    private String classId;

    private String content;

    private String reply;

    private String type;

    private Byte flag;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    private Integer score;

    private static final long serialVersionUID = 1L;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Interlocution{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", classId='" + classId + '\'' +
                ", content='" + content + '\'' +
                ", reply='" + reply + '\'' +
                ", type='" + type + '\'' +
                ", flag=" + flag +
                ", createtime=" + createtime +
                ", createby='" + createby + '\'' +
                ", updatetime=" + updatetime +
                ", updateby='" + updateby + '\'' +
                ", score=" + score +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }
}