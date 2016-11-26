package com.edjies.timeline.module.stock.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 *作为主键的id的类型必须为Long， 而不是long， 否则id不会自增。
 * Created by hubble on 2016/11/25 0025.
 */
@Entity
public class StockTrack {
    @Id(autoincrement = true)  /**作为主键的id的类型必须为Long， 而不是long， 否则id不会自增。*/
    private Long id;

    private Long stockTrackId;
    @NotNull
    private String code;

    private String name;

    @NotNull
    private float high;

    @NotNull
    private float low;

    private String desc;

    @NotNull
    /**yyyy-MM-dd*/
    private String start;

    @NotNull
    /**yyyy-MM-dd*/
    private String end;

    @Generated(hash = 1984014761)
    public StockTrack() {
    }

    public StockTrack(long stockTrackId, @NotNull String code, String name, float high, float low, String desc, @NotNull String start, @NotNull String end) {
        this.stockTrackId =stockTrackId;
        this.code = code;
        this.name = name;
        this.high = high;
        this.low = low;
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    public StockTrack(StockTrack stockTrack) {
        this(stockTrack.id, stockTrack.stockTrackId, stockTrack.code, stockTrack.name, stockTrack.high, stockTrack.low, stockTrack.desc,stockTrack.start, stockTrack.end);
    }

    @Generated(hash = 1366980503)
    public StockTrack(Long id, Long stockTrackId, @NotNull String code, String name, float high, float low, String desc, @NotNull String start, @NotNull String end) {
        this.id = id;
        this.stockTrackId = stockTrackId;
        this.code = code;
        this.name = name;
        this.high = high;
        this.low = low;
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

   

    public float getHigh() {
        return this.high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return this.low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public long getStockTrackId() {
        return this.stockTrackId;
    }

    public void setStockTrackId(long stockTrackId) {
        this.stockTrackId = stockTrackId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStockTrackId(Long stockTrackId) {
        this.stockTrackId = stockTrackId;
    }

    public Long getId() {
        return this.id;
    }


}
