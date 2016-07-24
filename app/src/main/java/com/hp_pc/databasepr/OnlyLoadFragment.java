package com.hp_pc.databasepr;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnlyLoadFragment extends Fragment {
 ArrayAdapter arrayAdapter;
    public OnlyLoadFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.data_view, container, false);
        ListView listView= (ListView)rootview.findViewById(R.id.list_fragment);
        arrayAdapter=new ArrayAdapter(OnlyLoad.context,R.layout.fragment_only_load,R.id.list_text_view_id1,OnlyLoad.Info);
        listView.setAdapter(arrayAdapter);
        return rootview;
    }
}
