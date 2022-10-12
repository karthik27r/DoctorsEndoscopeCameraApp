package com.example.endoscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mediaDB extends SQLiteOpenHelper{

    private Context context;
    public static final String DATABASE_NAME = "endoscopeTest.db";
    public static final int DATABASE_VERSION= 1;

    public mediaDB(@Nullable Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE info (id INTEGER PRIMARY KEY AUTOINCREMENT, docId INTEGER NOT NULL,docName TEXT NOT NULL, patientName TEXT,media TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS info");

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
}
