package com.aqiang.day0714_gisim.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MsgSql extends SQLiteOpenHelper {
    public MsgSql(Context context) {
        super(context, "msg_sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table msg(user varchar(20),msg varchar(100),times varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
