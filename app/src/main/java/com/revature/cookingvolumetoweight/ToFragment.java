package com.revature.cookingvolumetoweight;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToFragment extends Fragment {

    public ToFragment() {
        // Required empty public constructor
    }

    private ArrayList<String> makeData(){
        TypedArray typedArray = getResources().obtainTypedArray(R.array.units);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < typedArray.length(); i++) {
            list.add(typedArray.getString(i).toUpperCase(Locale.CANADA));
        }
        return list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec_to);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ToAdapter adapter = new ToAdapter((MainActivity) getActivity(), makeData());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
