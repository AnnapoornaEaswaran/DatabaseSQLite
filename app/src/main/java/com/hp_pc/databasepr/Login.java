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
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Datahelper pHandler;
    Context context;
    String user, pass;
    Button myButton;
    String bname;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();
        pHandler = new Datahelper(context);
        pHandler.open();
        Cursor C = pHandler.returnPersonalData();
         setContentView(R.layout.activity_login);
        textView=(TextView)findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        myButton = (Button) findViewById(R.id.optionButton);
        myButton.setVisibility(View.INVISIBLE);
        if (C.moveToFirst()) {
            bname = "Login";
            myButton.setText("Login");
            textView.setText("Login to your account");
            textView.setVisibility(View.VISIBLE);
            myButton.setVisibility(View.VISIBLE);

            if (C.moveToFirst()) {
                do {
                    user = C.getString(0);
                    pass = C.getString(1);
                    Toast.makeText(getBaseContext(), "Username: " + user + " Password: " + pass + " ", Toast.LENGTH_LONG).show();


                } while (C.moveToNext());
            }
        } else{
            bname = "Sign Up";
            myButton.setText(bname);
            textView.setText("Sign Up for Easy Mailing!");
            textView.setVisibility(View.VISIBLE);
            myButton.setVisibility(View.VISIBLE);

        }
        pHandler.close();
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bname == "Login") {
                    Intent i = new Intent(context, Firstpage.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(context, SignUp1.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
