package com.example.endoscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class connectDB extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME="endoscopeTest.db";
    public static final int DATABASE_VERSION= 1;

    public connectDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE cred (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,passw TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS cred");

    }

    public boolean insertData(String uname, String passw){
        SQLiteDatabase myDB = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",uname);
        values.put("passw",passw);

        long result = myDB.insert("cred",null,values);
        if(result==1){return false;} return true;

    }
    public boolean userVerify(String uname){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM cred WHERE name=?",new String[]{uname});
        if(cursor.getCount()>0){return true;}
        return false;
    }

    public Boolean loginCheck(String uname, String passw){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM cred WHERE name=? and passw=?", new String[] {uname,passw});
        if(cursor.getCount()>0){return true;}
        return false;
    }

//    public Boolean addData(String uname, String media){
//        SQLiteDatabase myDB = this.getReadableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("docName",uname);
//        values.put("media",media);
//
//        long result = myDB.insert("patientData",null,values);
//        if(result==1){return false;} return true;
//    }
}
