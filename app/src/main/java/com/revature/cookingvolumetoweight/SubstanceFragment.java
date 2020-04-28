package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubstanceFragment extends Fragment {

    public SubstanceFragment() {
        // Required empty public constructor
    }

    private ArrayList<Pair<String, Double>> makeData(){

        ArrayList<Pair<String, Double>> list = new ArrayList<>();

        //https://hapman.com/news-and-knowledge/bulk-material-density-guide/

        list.add(new Pair<>("Whole Wheat Flour (Organic)", 0.77));
        list.add(new Pair<>("Almond Flour", 1.09));
        list.add(new Pair<>("Barley Flour", 0.46));
        list.add(new Pair<>("Corn Flour", 0.74));
        list.add(new Pair<>("Flour", 0.62));
        list.add(new Pair<>("Rock Salt", 0.560));

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
