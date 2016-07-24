package com.hp_pc.databasepr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class Data2Fragment extends Fragment {

    ArrayAdapter arrayAdapter;
    public Data2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview= inflater.inflate(R.layout.data_view, container, false);
        ListView listView= (ListView)rootview.findViewById(R.id.list_fragment);
        arrayAdapter=new ArrayAdapter(Data2.context,R.layout.fragment_data2,R.id.list_text_view_id,MainActivity.Info);
        listView.setAdapter(arrayAdapter);
        return rootview;
    }

}
