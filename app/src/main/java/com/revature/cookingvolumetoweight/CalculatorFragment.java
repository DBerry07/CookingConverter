package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private MainActivity parent;
    private View thisView;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_calculator, container, false);

        parent = (MainActivity) getActivity();

        setOnClickListener(R.id.btn_0);
        setOnClickListener(R.id.btn_1);
        setOnClickListener(R.id.btn_2);
        setOnClickListener(R.id.btn_3);
        setOnClickListener(R.id.btn_4);
        setOnClickListener(R.id.btn_5);
        setOnClickListener(R.id.btn_6);
        setOnClickListener(R.id.btn_7);
        setOnClickListener(R.id.btn_8);
        setOnClickListener(R.id.btn_9);
        setOnClickListener(R.id.btn_point);
        setOnClickListener(R.id.btn_enter);

        return thisView;
    }

    private void setOnClickListener(int viewID){
        thisView.findViewById(viewID).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Button btn = (Button) view;
        switch (btn.getId()){
            case R.id.btn_enter:
                break;
            default:
                parent.updateTextView(btn.getText().toString());
                break;
        }
    }

}
