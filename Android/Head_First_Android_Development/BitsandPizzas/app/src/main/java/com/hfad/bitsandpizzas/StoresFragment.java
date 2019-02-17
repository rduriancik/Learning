package com.hfad.bitsandpizzas;

import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StoresFragment extends ListFragment {

    public StoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (inflater.getContext(), android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.stores));
        setListAdapter(arrayAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
