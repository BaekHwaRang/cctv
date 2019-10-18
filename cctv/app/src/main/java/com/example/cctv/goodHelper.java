package com.example.cctv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class goodHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WriterID.db";
    public static final String TABLE_NAME = "writer";
    public static final String COLUMN_ID = "id";
    public goodHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE good" + "(id text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS good");
        onCreate(db);
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from good where id=" + id + "", null);
        return res;
    }

    public void insert(String id) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        // DB에 입력한 값으로 행 추가
        db.insert("good",null,contentValues);
    }

    public void update(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        db.update("good",contentValues,"id = ? ",new String[]{id});
    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM good WHERE id= \"" + id + "\"");
    }

    public ArrayList getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor res = db.rawQuery("select * from good", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            arrayList.add(res.getString(res.getColumnIndex(COLUMN_ID)));
            res.moveToNext();
        }
        return arrayList;
    }
}
