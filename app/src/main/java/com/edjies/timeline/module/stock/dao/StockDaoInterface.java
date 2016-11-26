package com.edjies.timeline.module.stock.dao;

import com.edjies.timeline.MyApplication;
import com.edjies.timeline.module.project.bean.DaoSession;
import com.edjies.timeline.module.stock.bean.StockPool;
import com.edjies.timeline.module.stock.bean.StockPoolDao;
import com.edjies.timeline.module.stock.bean.StockTrack;
import com.edjies.timeline.module.stock.bean.StockTrackDao;

import java.util.ArrayList;

/**
 * 项目模块数据访问接口
 * Created by hubble on 2016/11/21 0021.
 */

public class StockDaoInterface {
    private static StockDaoInterface mDao = new StockDaoInterface();
    private StockDaoInterface() {

    }
    public static StockDaoInterface getInstance() {
        return mDao;
    }


    /** 获取我的StockPool列表*/
    public ArrayList<StockPool> getStockPool() {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockPoolDao dao = session.getStockPoolDao();
        ArrayList<StockPool> stockPools = (ArrayList<StockPool>) dao.queryBuilder().list();
        ArrayList<StockPool> result = new ArrayList<>();
        for(StockPool stockPool : stockPools) {
            result.add(new StockPool(stockPool));
        }
        return result;
    }

    /** 获取我的StockTrack列表*/
    public ArrayList<StockTrack> getStockTrack(long stockPoolId) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao dao = session.getStockTrackDao();
        ArrayList<StockTrack> stockTracks = (ArrayList<StockTrack>) dao.queryBuilder().where(StockTrackDao.Properties.StockTrackId.eq(stockPoolId)).list();
        ArrayList<StockTrack> result = new ArrayList<>();
        for(StockTrack stockTrack : stockTracks) {
            result.add(new StockTrack(stockTrack));
        }
        return result;
    }

    /** 添加StockPool*/
    public void addStockPool(StockPool stockPool) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockPoolDao dao = session.getStockPoolDao();
        dao.insert(stockPool);
    }

    /** 添加StockTrack*/
    public void addStockTrack(StockTrack stockTrack) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao dao = session.getStockTrackDao();
        dao.insert(stockTrack);
    }

    /** 更新StockTrack*/
    public void upateStockTrack(ArrayList<StockTrack> datas) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao dao = session.getStockTrackDao();
        dao.updateInTx(datas);
    }

    /** 更新StockTrack*/
    public void updateStockTrack(StockTrack data) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao dao = session.getStockTrackDao();
        dao.update(data);
    }

    /** 删除StockPool, 这可能会花费一些时间*/
    public void deleteStockPool(StockPool stockPool) {
        // 先删除关联的StockTrack
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao stockTrackDao = session.getStockTrackDao();
        String sql = "delete from "+ StockTrackDao.TABLENAME +" where "+ StockTrackDao.Properties.StockTrackId +"="+ stockPool.getId() +";";
        stockTrackDao.getDatabase().execSQL(sql);
        // 再删除StockPool
        StockPoolDao stockPoolDao = session.getStockPoolDao();
        stockPoolDao.delete(stockPool);
    }

    /** 删除StockTrack*/
    public void deleteStockTrack(StockTrack item) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        StockTrackDao dao = session.getStockTrackDao();
        dao.delete(item);
    }


}
