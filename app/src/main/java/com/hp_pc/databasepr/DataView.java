package com.hp_pc.databasepr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Hp-pc on 05-10-2015.
 */
public class DataView extends AppCompatActivity{
   ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i= getIntent();
        setContentView(R.layout.data_view);

        adapter = new ArrayAdapter(this,R.layout.data_view,R.id.list_text_view_id,MainActivity.Info);
        ListView listView = (ListView)findViewById(R.id.list_fragment);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    //static Datahelper handler;
    /*public static void getInfo(Context ctx)
    {
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.data_view, container, false);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.data_view,MainActivity.Info);
               ListView listView = (ListView) rootView.findViewById(R.id.list_fragment);
        listView.setAdapter(adapter);
        return rootView;
    }*/
}
