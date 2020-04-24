package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements View.OnClickListener {

    private TextView entry;
    private TextView answer;
    private MainActivity parent;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        entry = view.findViewById(R.id.tv_from_val);
        answer = view.findViewById(R.id.tv_to_val);
        parent = (MainActivity) getActivity();

        return view;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_sub:
                parent.navigate(parent.SUBSTANCE);
                break;
        }
    }

    public boolean updateEntry(String str){
        String contents = "";
        try {
            contents = entry.getText().toString();
        }
        catch (Exception e){
            Log.d("Display Entry", "Failed to get contents of entry textview");
        }
        contents = contents + str;
        try {
            entry.setText(contents);
        }
        catch (Exception e) {
            Log.d("Display Entry", "Failed to set contents of entry textview");
            return false;
        }
        return true;
    }
}
