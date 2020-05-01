package com.revature.cookingvolumetoweight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment
        //implements View.OnClickListener
        {

    private View thisView;

    private TextView fromVal;
    private TextView fromName;
    private TextView toVal;
    private TextView toName;
    private TextView substance;
    private TextView density;
    private TabLayout tabLayout;
    private MainActivity parent;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_display, container, false);

        fromVal = thisView.findViewById(R.id.display_tv_from_val);
        fromName = thisView.findViewById(R.id.display_tv_from_name);
        toVal = thisView.findViewById(R.id.display_tv_to_val);
        toName = thisView.findViewById(R.id.display_tv_to_name);
        substance = thisView.findViewById(R.id.display_tv_sub_name);
        density = thisView.findViewById(R.id.display_tv_density);
        tabLayout = thisView.findViewById(R.id.display_tabs);
        parent = (MainActivity) getActivity();

        tabConfig();
        /*setOnClickListener(R.id.btn_sub);
        setOnClickListener(R.id.btn_calc);*/

        return thisView;
    }

    /*@Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_sub:
                parent.navigate(parent.SUBSTANCE);
                break;
            case R.id.btn_calc:
                parent.navigate(parent.CALCULATOR);
                break;
        }
    }*/

    /*private void setOnClickListener(int viewID){
        thisView.findViewById(viewID).setOnClickListener(this);
    }*/

    public void tabConfig(){
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        parent.navigate(parent.CALCULATOR);
                        break;
                    case 1:
                        parent.navigate(parent.SUBSTANCE);
                        break;
                    case 2:
                        parent.navigate(parent.FROM);
                        break;
                    case 3:
                        parent.navigate(parent.TO);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public boolean updateFromVal(String str){
        String contents = getResources().getString(R.string.blank);
        if (str.equals(getResources().getString(R.string.blank))) {
            try {
                fromVal.setText(contents);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            contents = fromVal.getText().toString();
        } catch (Exception e){
            Log.d("Display Entry", "Failed to get contents of entry textview");
        }
        contents = contents + str;
        try {
            fromVal.setText(contents);
        } catch (Exception e) {
            Log.d("Display Entry", "Failed to set contents of entry textview");
            return false;
        }
        return true;
    }

    public boolean updateSubstance(Pair<String, Float> pair){
        String name = "";
        float density = 0.0f;
        try {
            name = pair.first;
            density = pair.second;
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            this.substance.setText(name);
            this.density.setText(Float.toString(density));
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateFromName(String str){
        try {
            this.fromName.setText(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateToName(String str){
        try {
            this.toName.setText(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateToValue(String str){
        try{
            this.toVal.setText(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String fetchViewText(int viewID) {
        return ((TextView) getView().findViewById(viewID)).getText().toString();
    }
}
