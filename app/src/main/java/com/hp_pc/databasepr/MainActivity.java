package com.hp_pc.databasepr;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        Button enter,load;
     Context context=this;
    static Context ctx;
        EditText emailid,pass;
    String email,pas,type;
   public  Datahelper handler;
   public static ArrayList<String> Info;

    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getBaseContext();
        setContentView(R.layout.activity_main);
        enter = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
        emailid = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        recacc();
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailid.getText().toString();
                pas = pass.getText().toString();
                if(check()) {
                    if (email.contains("yahoo"))
                        type = "yahoo";
                    else if (email.contains("gmail"))
                        type = "gmail";
                    else
                        type = "others";
                    handler = new Datahelper(getBaseContext());
                    handler.open();
                    long ret = handler.insertData(email, pas, type);
                    Toast.makeText(getBaseContext(), "Data inserted!", Toast.LENGTH_LONG).show();
                    handler.close();
                }

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                            recacc();
                                        Intent intent= new Intent(context,Data2.class);
                                        startActivity(intent);
                                    }
                                }


        );
    }
    public boolean check()
    {
        if(!email.contains("@")||!email.contains(".com")) {
            Toast.makeText(getBaseContext(), "Wrong email id!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public void recacc()
    {
        Info=new ArrayList<String>();
        String email,pass;
        int i=0;
        email="";
        pass="";
        handler= new Datahelper(ctx);
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
        //adapter = new ArrayAdapter(getBaseContext(),R.layout.data_view,R.id.list_text_view_id,Info);
        //Intent intent= new Intent(context,Data2.class);
        //startActivity(intent);
    }
    /*public void sendInfo(View view)
    {
       // DataView.getInfo(getBaseContext());
        String email,pass;
        int i=0;
        email="";
        pass="";
        handler= new Datahelper(getBaseContext());
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
        setContentView(R.layout.data_view);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.data_view,R.id.list_text_view_id,MainActivity.Info);
        ListView listView = (ListView)findViewById(R.id.list_fragment);
        listView.setAdapter(adapter);
        //Intent intent= new Intent(this,DataView.class);
        //startActivity(intent);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
