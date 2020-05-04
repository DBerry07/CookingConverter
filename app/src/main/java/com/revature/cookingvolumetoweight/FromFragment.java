package com.revature.cookingvolumetoweight;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FromFragment extends Fragment {

    public FromFragment() {
        // Required empty public constructor
    }

    private ArrayList<String> makeData(){
        ArrayList<String> list = new ArrayList<>();
        TypedArray typedArray;

        typedArray = getResources().obtainTypedArray(R.array.units_weights);
        for (int i = 0; i < typedArray.length(); i++) {
            list.add(typedArray.getString(i).toUpperCase());
        }

        typedArray = getResources().obtainTypedArray(R.array.units_volume);
        for (int i = 0; i < typedArray.length(); i++) {
            list.add(typedArray.getString(i).toUpperCase());
        }

        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_from, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec_from);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        FromAdapter adapter = new FromAdapter((MainActivity) getActivity(), this.makeData());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
