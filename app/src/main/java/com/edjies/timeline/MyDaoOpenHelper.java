package com.edjies.timeline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.edjies.timeline.module.project.bean.DaoMaster;
import com.edjies.timeline.module.project.bean.ProjectItemDao;
import com.edjies.timeline.utils.ULog;

/**
 * 1. 数据库的版本号位于DaoMaster的SCHEMA_VERSION字段。
 * Created by hubble on 2016/11/22 0022.
 */

public class MyDaoOpenHelper extends DaoMaster.DevOpenHelper {
    public MyDaoOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int migrateVersion = oldVersion + 1; migrateVersion <= newVersion; migrateVersion++) {
            ULog.e("onUpgrade", "oldVersion:" + oldVersion + ", newVersion:" + newVersion);
            upgrade(db, migrateVersion);
        }
    }

    private void upgrade(SQLiteDatabase db, int migrateVersion) {
        switch (migrateVersion) {
            case 2:
                db.execSQL("ALTER TABLE "+ ProjectItemDao.TABLENAME +" ADD COLUMN 'DETAILS' TEXT;");
                break;
            case 3:
                db.execSQL("ALTER TABLE "+ ProjectItemDao.TABLENAME +" ADD COLUMN 'END_TIME' TEXT;");
                break;
        }
    }
}
