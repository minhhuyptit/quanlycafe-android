package com.example.exemple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static  String DB_NAME = "dbMonHoc.db";
    private static int DB_VERSION = 1;
    private static final String TB_MONHOCS = "tbMonHoc";
    private static final String COL_MONHOC_ID = "monhoc_id";
    private static final String COL_MONHOC_MA = "monhoc_ma";
    private static final String COL_MONHOC_TEN = "monhoc_ten";
    private static final String COL_MONHOC_SOTIET = "monhoc_sotiet";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptTBMonHocs = "CREATE TABLE " + TB_MONHOCS + "(" +
                COL_MONHOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COL_MONHOC_MA + " TEXT," +
                COL_MONHOC_TEN + " TEXT," +
                COL_MONHOC_SOTIET + " TEXT)";

        db.execSQL(scriptTBMonHocs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_MONHOCS);
        onCreate(db);
    }

    public void getMonHocS(ArrayList<MonHoc> monHocs){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_MONHOCS, new String[]{COL_MONHOC_MA, COL_MONHOC_TEN, COL_MONHOC_SOTIET}, null
        , null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                MonHoc monHoc = new MonHoc();
                monHoc.setId(cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA)));
                monHoc.setId(cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN)));
                monHoc.setId(cursor.getString(cursor.getColumnIndex(COL_MONHOC_SOTIET)));
                monHocs.add(monHoc);
            }while (cursor.moveToNext());
        }
    }

    public  void  saveMonHocs(MonHoc monHoc){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_MONHOC_MA, monHoc.getId());
        values.put(COL_MONHOC_TEN, monHoc.getName());
        values.put(COL_MONHOC_SOTIET, monHoc.getSoTiet());
        db.insert(TB_MONHOCS, null, values);

        db.close();
    }
}
