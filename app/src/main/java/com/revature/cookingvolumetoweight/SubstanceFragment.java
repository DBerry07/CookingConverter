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

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubstanceFragment extends Fragment {

    public SubstanceFragment() {
        // Required empty public constructor
    }

    private ArrayList<Pair<String, Float>> makeData(){

        ArrayList<Pair<String, Float>> list = new ArrayList<>();
        TypedArray substanceNames = getResources().obtainTypedArray(R.array.substance_names);
        TypedArray substanceValues = getResources().obtainTypedArray(R.array.substance_values);

        for (int i = 0; i < substanceNames.length(); i++){

            Pair<String, Float> pair = new Pair<>(
                    substanceNames.getString(i).toUpperCase(Locale.CANADA),
                    substanceValues.getFloat(i, 0.00f)
            );
            list.add(pair);
        }

        return list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_substance, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec_substance);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        SubstanceAdapter adapter = new SubstanceAdapter((MainActivity) getActivity(), makeData());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
