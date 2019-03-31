package com.hnu.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String id;

    private String teacherId;

    private String title;

    private String content;

    private Byte flag;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", createtime=" + createtime +
                ", createby='" + createby + '\'' +
                ", updatetime=" + updatetime +
                ", updateby='" + updateby + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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