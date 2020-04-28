package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToFragment extends Fragment {

    public ToFragment() {
        // Required empty public constructor
    }

    private ArrayList<String> makeData(){

        ArrayList<String> list = new ArrayList<>();
        list.add("Ounces");
        list.add("Pounds");
        list.add("Grams");
        list.add("Kilograms");
        list.add("Millilitres");
        list.add("Quarts");
        list.add("Litres");
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
