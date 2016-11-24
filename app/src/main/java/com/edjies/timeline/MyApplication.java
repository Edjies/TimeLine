package com.edjies.timeline;

import android.app.Application;

import com.edjies.timeline.module.project.bean.DaoMaster;
import com.edjies.timeline.module.project.bean.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

/**
 *
 * Created by hubble on 2016/10/29 0029.
 */

public class MyApplication extends Application {
    private DaoMaster.DevOpenHelper helper;
    private Database db;
    private DaoSession daoSession;
    private static MyApplication myApp;

    public static MyApplication getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        Fresco.initialize(this);
        helper = new MyDaoOpenHelper(this, "notes-db");
        db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
