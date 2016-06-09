package com.miguelcr.navigationdrawer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import de.greenrobot.daoexample.DaoMaster;
import de.greenrobot.daoexample.DaoSession;


/**
 * Created by miguelcampos on 10/3/16.
 */
public class DatabaseConnection {
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoSession getConnection(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx,
                "duckhunt-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }
}
