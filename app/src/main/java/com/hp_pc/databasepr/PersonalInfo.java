package com.hp_pc.databasepr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Hp-pc on 07-10-2015.
 */
public class PersonalInfo {
    public static final String TABLE_NAME1 = "personalinfo";
    public static final String COLUMN_FNAME1 = "fname";
    public static final String COLUMN_LNAME1 = "lname";
    public static final String COLUMN_USERNAME1 = "username";
    public static final String COLUMN_PASSWORD1 = "password";
    //public static final String COLUMN_PHONE = "phone";
    public static final String DATABASE_NAME="email";
    public static final int DATABASE_VERSION=1;
    public static final String Create_table="Create table personalinfo ( username text not null primary key, fname text not null, "+
    "lname text not null, password text not null);";
    Phelper Phelp;
    public boolean t1=false;
    Context ctx;
    SQLiteDatabase db;
    public PersonalInfo(Context ctx)
    {
        this.ctx=ctx;
        Phelp=new Phelper(ctx);
        Toast.makeText(ctx, "Table created", Toast.LENGTH_LONG).show();
    }
    private static class Phelper extends SQLiteOpenHelper {

        public Phelper(Context ctx)
        {
            super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        }
    public static boolean table=false;
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(Create_table);

                table=true;
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql= "DROP TABLE IF EXISTS personalinfo";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }
    }
    public boolean isPresent()
    {
        if(Phelp.table)
            return true;
        else
            return false;
    }
    public PersonalInfo open()
    {
        db=Phelp.getWritableDatabase();
        if(Phelp.table==false)
        t1=true;
        return this;
    }
    public void close()
    {
        Phelp.close();
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
    public Cursor returnData()
    {
        return db.query(TABLE_NAME1,new String []{COLUMN_USERNAME1,COLUMN_PASSWORD1},null,null,null,null,null);
    }
}
