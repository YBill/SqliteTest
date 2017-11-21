package com.example.yuanweibiao.sqlitetest.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.yuanweibiao.sqlitetest.MyApplication;

/**
 * Created by yuanweibiao on 2017/11/21.
 */

public class ImageDbManager {

    private static ImageDbManager imageDbManager;
    private ImageDbHelper imageDbHelper;

    private ImageDbManager(Context context) {
        if (imageDbHelper == null)
            imageDbHelper = ImageDbHelper.getInstance(context);
    }

    private SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase database = imageDbHelper.getReadableDatabase();
        return database;
    }

    private SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase database = imageDbHelper.getWritableDatabase();
        return database;
    }

    public static ImageDbManager getInstance() {
        if (imageDbManager == null) {
            synchronized (ImageDbManager.class) {
                if (imageDbManager == null)
                    imageDbManager = new ImageDbManager(MyApplication.context);
            }
        }
        return imageDbManager;
    }

    public boolean checkImageExist(String url) {
        SQLiteDatabase db = imageDbManager.getReadableDatabase();
        String sql = "SELECT * FROM " + ImageDbHelper.TABLE_NAME_URL
                + " WHERE " + ImageDbHelper.URL + " =?";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, new String[]{url});
            String selectUrl = "";
            if (cursor.moveToFirst()) {
                selectUrl = cursor.getString(cursor.getColumnIndex(ImageDbHelper.URL));
            }
            Log.e("Bill", "selectUrl:" + selectUrl);
            if (!TextUtils.isEmpty(selectUrl)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    synchronized public boolean insertImage(String url) {
        SQLiteDatabase db = imageDbManager.getWritableDatabase();
        String sql = "replace into " + ImageDbHelper.TABLE_NAME_URL +
                "(" + ImageDbHelper.URL + ") VALUES(?)";
        try {
            db.execSQL(sql, new Object[]{url});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

}
