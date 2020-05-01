package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private MainActivity parent;
    private View thisView;
    private boolean pointPressed = false;

    private TextView fromName;
    private TextView fromVal;
    private TextView toName;
    private TextView toVal;
    private TextView subName;
    private TextView subVal;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_calculator, container, false);

        parent = (MainActivity) getActivity();

        setOnClickListener(R.id.calc_btn_0);
        setOnClickListener(R.id.calc_btn_1);
        setOnClickListener(R.id.calc_btn_2);
        setOnClickListener(R.id.calc_btn_3);
        setOnClickListener(R.id.calc_btn_4);
        setOnClickListener(R.id.calc_btn_5);
        setOnClickListener(R.id.calc_btn_6);
        setOnClickListener(R.id.calc_btn_7);
        setOnClickListener(R.id.calc_btn_8);
        setOnClickListener(R.id.calc_btn_9);
        setOnClickListener(R.id.calc_btn_point);
        setOnClickListener(R.id.calc_btn_enter);
        setOnClickListener(R.id.calc_btn_clear);

        return thisView;
    }

    private void setOnClickListener(int viewID){
        thisView.findViewById(viewID).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Button btn = (Button) view;
        switch (btn.getId()){
            case R.id.calc_btn_enter:
                String str, to;
                Pair<String, Float> substance, from;
                float num;
                try {
                    str = parent.fetchViewText(R.id.display_tv_sub_name);
                    if (str.isEmpty()) {
                        Log.d("CALCULATOR", "Unable to obtain substance");
                        return;
                    }
                    num = Float.parseFloat(parent.fetchViewText(R.id.display_tv_density));
                   substance = new Pair<>(str, num);
                } catch (NumberFormatException e) {
                    Log.d("CALCULATOR", "Unable to obtain density");
                    return;
                }
                try {
                    str = parent.fetchViewText(R.id.display_tv_from_name);
                    if (str.isEmpty()) {
                        Log.d("CALCULATOR", "Unable to obtain entry name");
                        return;
                    }
                    num = Float.parseFloat(parent.fetchViewText(R.id.display_tv_from_val));
                    from = new Pair<>(str, num);
                } catch (NumberFormatException e) {
                    Log.d("CALCULATOR", "Unable to obtain entry val");
                    return;
                }
                try {
                    to = parent.fetchViewText(R.id.display_tv_to_name);
                    if (to.isEmpty()) {
                        Log.d("CALCULATOR", "Unable to obtain answer name");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                parent.calculate(substance, from, to);
                break;
            case R.id.calc_btn_point:
                if (!pointPressed){
                    pointPressed = true;
                    parent.updateTextView(btn.getText().toString());
                }
                break;
            case R.id.calc_btn_clear:
                parent.updateTextView(getResources().getString(R.string.blank));
                pointPressed = false;
                break;
            default:
                parent.updateTextView(btn.getText().toString());
                break;
        }
    }

    public String fetchViewText(int viewID) {
        return ((TextView) getView().findViewById(viewID)).getText().toString();
    }

}
