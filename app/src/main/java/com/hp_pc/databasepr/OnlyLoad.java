package com.hp_pc.databasepr;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class OnlyLoad extends AppCompatActivity {
 public static Context context;
    Datahelper handler;
    public static ArrayList<String> Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getBaseContext();
        Info=new ArrayList<String>();
        String email,pass;
        int i=0;
        email="";
        pass="";
        handler= new Datahelper(context);
        handler.open();
        Cursor C=handler.returnData();
        if(C.moveToFirst())
        {
            do {
                email=C.getString(0);
                pass=C.getString(1);
                Info.add("Email: "+email+" Password: "+pass);
                i++;
                //Toast.makeText(getBaseContext(),"Email id: "+email+" Password: "+pass+" ",Toast.LENGTH_LONG).show();
            }while(C.moveToNext());
        }
        handler.close();

        setContentView(R.layout.activity_only_load);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_only_load, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
