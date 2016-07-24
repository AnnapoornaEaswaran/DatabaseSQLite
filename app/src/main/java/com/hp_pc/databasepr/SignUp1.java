package com.hp_pc.databasepr;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp1 extends AppCompatActivity {

    Button SignUp;
    EditText fname1, lname1,user1,pass1,pass11;
    String fname,lname,pass,pass_1,user;
    // Long phone;
    Context context;
    Datahelper pHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        context=getBaseContext();

        SignUp=(Button)findViewById(R.id.signup);
        fname1=(EditText)findViewById(R.id.fname);
        lname1=(EditText)findViewById(R.id.lname);
        user1=(EditText)findViewById(R.id.username);
        pass1=(EditText)findViewById(R.id.pass);
        pass11=(EditText)findViewById(R.id.pass1);
        // phone1=(EditText)findViewById(R.id.ph);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = fname1.getText().toString();
                lname = lname1.getText().toString();
                user = user1.getText().toString();
                pass = pass1.getText().toString();
                pass_1 = pass11.getText().toString();
                // phone=Long.parseLong(phone1.getText().toString());
                pHandler = new Datahelper(context);
                pHandler.open();
                long ret = pHandler.insertPersonalData(fname, lname, user, pass);
                Toast.makeText(getBaseContext(), "Data inserted!", Toast.LENGTH_LONG).show();
                pHandler.close();
                pHandler.open();
                Cursor C = pHandler.returnPersonalData();

                if (C.moveToFirst()) {
                    do {
                        user = C.getString(0);
                        pass = C.getString(1);
                        Toast.makeText(getBaseContext(), "Username: " + user + " Password: " + pass + " ", Toast.LENGTH_LONG).show();


                    } while (C.moveToNext());
                }

                pHandler.close();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up1, menu);
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
