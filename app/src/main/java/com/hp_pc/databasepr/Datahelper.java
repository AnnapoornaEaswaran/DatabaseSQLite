package com.hp_pc.databasepr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hp-pc on 30-09-2015.
 */
public class Datahelper {
    public static final String TABLE_NAME = "emailaccount";
    public static final String COLUMN_EMAILID = "emailid";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAILTYPE = "emailtype";
    public static final String DATABASE_NAME="email";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME1 = "personalinfo";
    public static final String COLUMN_FNAME1 = "fname";
    public static final String COLUMN_LNAME1 = "lname";
    public static final String COLUMN_USERNAME1 = "username";
    public static final String COLUMN_PASSWORD1 = "password";
    //public static final String COLUMN_PHONE = "phone";
    public static final String Create_table="Create table personalinfo ( username text not null primary key, fname text not null, "+
            "lname text not null, password text not null);";
    public static final String Createtable="Create table emailaccount ( emailid text not null primary key,password text not null,"+
            "emailtype text not null);";
    Emailhelper Ehelp;
    Context ctx;
    SQLiteDatabase db;
    public Datahelper(Context ctx)
    {
        this.ctx=ctx;
        Ehelp=new Emailhelper(ctx);
    }
    private static class Emailhelper extends SQLiteOpenHelper{
        public Emailhelper(Context ctx)
        {
            super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(Createtable);
                    db.execSQL(Create_table);
                }
               catch(SQLException e)
               {
                   e.printStackTrace();
               }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql="DROP TABLE IF EXISTS emailaccount";
            String sql1= "DROP TABLE IF EXISTS personalinfo";
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql1);
        }
    }
    public Datahelper open()
    {
        db=Ehelp.getWritableDatabase();
        return this;
    }
    public void close()
    {
        Ehelp.close();
    }
    public long insertData(String email,String pass,String type)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_EMAILID,email);
        values.put(COLUMN_PASSWORD,pass);
        values.put(COLUMN_EMAILTYPE,type);
        return db.insertOrThrow(TABLE_NAME,null,values);
    }
    public Cursor returnData()
    {
        return db.query(TABLE_NAME,new String []{COLUMN_EMAILID,COLUMN_PASSWORD},null,null,null,null,null);
    }
    public long insertPersonalData(String fname,String lname,String user, String pass)
    {  long r=0;
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME1, user);
            values.put(COLUMN_FNAME1, fname);
            values.put(COLUMN_LNAME1, lname);
            values.put(COLUMN_PASSWORD1, pass);
            r = db.insertOrThrow(TABLE_NAME1, null, values);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return r;
    }
    public Cursor returnPersonalData()
    {
        return db.query(TABLE_NAME1,new String []{COLUMN_USERNAME1,COLUMN_PASSWORD1},null,null,null,null,null);
    }
}
