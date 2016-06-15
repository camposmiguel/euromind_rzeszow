package com.miguelcr.usercrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.miguelcr.usercrud.greendao.DaoMaster;
import com.miguelcr.usercrud.greendao.DaoSession;


public class DatabaseConnection {
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoSession getConnection(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx,
                "user-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }
}
