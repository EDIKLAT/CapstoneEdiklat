package com.example.mindha.ediklat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mindha on 25/02/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_NAME = "data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_ROLE = "role_id";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table user (id integer primary key not null , "+
            "username text not null, password text not null, role_id integer not null);";


    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db = sqLiteDatabase;

    }
    public void insertData(Data data){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query ="select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_USERNAME,data.getUsername());
        values.put(COLUMN_PASS,data.getPassword());
        values.put(COLUMN_ROLE,data.getRole_id());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);

    }

    public String searchPass(String user){
        db= this.getReadableDatabase();
        String query = "select username, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst()){
            do {
                System.out.println("CATCH!!!");
                a= cursor.getString(0);
                if (a.equals(user)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }else{
            a= cursor.getString(0);
            System.out.println(a);
            System.out.println(b);
        }
        return b;
    }
}
