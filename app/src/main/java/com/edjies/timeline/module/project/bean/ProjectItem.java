package com.edjies.timeline.module.project.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 *
 * Created by hubble on 2016/11/19 0019.
 */
@Entity
public class ProjectItem implements Serializable{
    public static final long serialVersionUID = 0L;
    @Id(autoincrement = true)   /**作为主键的id的类型必须为Long， 而不是long， 否则id不会自增。*/
    private Long id;
    @NotNull
    private Long projectId;
    @NotNull
    private String title = "";
    @NotNull
    private String createTime = "";
    @NotNull
    private int status = ProjectStatus.STATUS_TOD;

    private int et = 30; // 已分钟作为单位

    private String details = "";

    private String endTime = "";

    @Generated(hash = 1420592165)
    public ProjectItem(Long id, @NotNull Long projectId, @NotNull String title,
            @NotNull String createTime, int status, int et, String details,
            String endTime) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.createTime = createTime;
        this.status = status;
        this.et = et;
        this.details = details;
        this.endTime = endTime;
    }

    public ProjectItem(Long projectId, String title, String createTime, int et) {
        this.projectId = projectId;
        this.title = title;
        this.createTime = createTime;
        this.et = et;
    }

    public ProjectItem(ProjectItem item) {
        this.id = item.id;
        this.projectId = item.projectId;
        this.title = item.title;
        this.createTime = item.createTime;
        this.status = item.status;
        this.et = item.et;
        this.details = item.details;
        this.endTime = item.endTime;
    }

    @Generated(hash = 554209198)
    public ProjectItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProjectId() {
        return this.projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getEt() {
        return this.et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
