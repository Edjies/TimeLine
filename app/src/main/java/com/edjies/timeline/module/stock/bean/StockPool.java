package com.edjies.timeline.module.stock.bean;

import com.edjies.timeline.utils.UDate;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hubble on 2016/11/25 0025.
 */
@Entity
public class StockPool {
    @Id(autoincrement = true) /**作为主键的id的类型必须为Long， 而不是long， 否则id不会自增。*/
    private Long id;

    @NotNull
    private String name;

    @NotNull /**yyyy-MM-dd HH:mm:ss*/
    private String createTime;

    @Generated(hash = 2045895282)
    public StockPool(Long id, @NotNull String name, @NotNull String createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    @Generated(hash = 305907215)
    public StockPool() {
    }

    public StockPool(String name) {
        this.name = name;
        this.createTime = UDate.getFormatDate(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
    }

    public StockPool(StockPool stockPool) {
        this(stockPool.id, stockPool.name, stockPool.createTime);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
