package com.kdb2018.sasa.percent.veriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeriTabani extends SQLiteOpenHelper {
    public VeriTabani(@Nullable Context context) {
        super(context, "percent.sqlite", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"kripto\" (\n" +
                "\t\"coin_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"coin_adi\"\tTEXT,\n" +
                "\t\"coin_alis_fiyati\"\tREAL,\n" +
                "\t\"coin_alis_adedi\"\tREAL\n" +
                ")");

        db.execSQL("CREATE TABLE \"hisse\" (\n" +
                "\t\"hisse_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"hisse_adi\"\tTEXT,\n" +
                "\t\"hisse_alis_fiyati\"\tREAL,\n" +
                "\t\"hisse_alis_adedi\"\tREAL\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kripto");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS hisse");
        onCreate(db);

    }
}
