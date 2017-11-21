package com.example.yuanweibiao.sqlitetest.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yuanweibiao on 2017/11/21.
 */

public class ImageDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "rb_img_cache_db"; // 数据库名字
    public static String TABLE_NAME_URL = "url_cache_tb"; // 表名
    public static String URL = "url"; // 列名
    private static final int DB_VERSION = 1; // 数据库版本

    public ImageDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static ImageDbHelper imageDbHelper = null;

    public static ImageDbHelper getInstance(Context context) {
        if (imageDbHelper == null) {
            synchronized (ImageDbHelper.class) {
                if (imageDbHelper == null) {
                    imageDbHelper = new ImageDbHelper(context);
                }
            }
        }
        return imageDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(createUrlCacheTable());
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(TAG, "create table failed " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_URL);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            db.endTransaction();
        }
    }

    private String createUrlCacheTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_URL + "(" +
                URL + " VARCHAR PRIMARY KEY" +
                ")";
        return sql;
    }

}
