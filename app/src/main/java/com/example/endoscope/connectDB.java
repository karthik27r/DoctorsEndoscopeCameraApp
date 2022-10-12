package com.example.endoscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class connectDB extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME="newEndo.db";
    public static final int DATABASE_VERSION= 1;

    public connectDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE cred (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,passw TEXT NOT NULL)");
        myDB.execSQL("CREATE TABLE info (id INTEGER PRIMARY KEY AUTOINCREMENT, docId INTEGER NOT NULL,docName TEXT NOT NULL, patientName TEXT,media TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS cred");
        myDB.execSQL("DROP TABLE IF EXISTS info");

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

    public int loginCheck(String uname, String passw){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM cred WHERE name=? and passw=?", new String[] {uname,passw});

        if(cursor.getCount()>0){
            Cursor retId = myDB.rawQuery("SELECT * FROM cred WHERE name=? and passw=?", new String[] {uname,passw});
            cursor.moveToFirst();
            int idVal=cursor.getInt(0);
            return idVal;}

        return -404;
    }

    public Boolean addData(int docId,String uname,String pname, String media){
        SQLiteDatabase myDB = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("docId",docId);
        values.put("docName",uname);
        values.put("patientName",pname);
        values.put("media",media);


        long result = myDB.insert("info",null,values);
        if(result==1){return false;} return true;
    }
    public Cursor getUserData(String auth){
        SQLiteDatabase myDb = this.getWritableDatabase();

        Cursor cursor = myDb.rawQuery("SELECT patientName,media FROM info WHERE docId=?",new String[]{auth});
        return cursor;
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
