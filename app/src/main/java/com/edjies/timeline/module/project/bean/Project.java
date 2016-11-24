package com.edjies.timeline.module.project.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;

/**
 * 项目信息
 * Created by hubble on 2016/11/19 0019.
 */
@Entity
public class Project {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String title = "";
    @NotNull
    private String createTime = ""; // yyyy-MM-dd HH:mm:ss
    @Transient
    private int total = 0;
    @Transient
    private int finished = 0;

    @Generated(hash = 2054968313)
    public Project(Long id, @NotNull String title, @NotNull String createTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
    }

    public Project(String title, String createTime) {
        this.title = title;
        this.createTime = createTime;
    }

    @Generated(hash = 1767516619)
    public Project() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getCreateTime() {

        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
