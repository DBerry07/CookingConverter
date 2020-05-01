package com.revature.cookingvolumetoweight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Pair;

public class MainActivity extends AppCompatActivity {

    public final int CALCULATOR = 0;
    public final int SUBSTANCE = 1;
    public final int FROM = 2;
    public final int TO = 3;

    Conversion conversion;

    //private NavController navController;
    private FragmentManager fragmentManager;
    private DisplayFragment displayFragment;
    private CalculatorFragment calculatorFragment;
    private SubstanceFragment substanceFragment;
    private FromFragment fromFragment;
    private ToFragment toFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //navController = Navigation.findNavController(findViewById(R.id.nav_host_main));
        fragmentManager = this.getSupportFragmentManager();
        displayFragment = (DisplayFragment) fragmentManager.findFragmentById(R.id.display_fragment_main);
        calculatorFragment = new CalculatorFragment();
        substanceFragment = new SubstanceFragment();
        fromFragment = new FromFragment();
        toFragment = new ToFragment();

        conversion = new Conversion(getResources());
        addFragments();
    }

    private void addFragments(){
        fragmentManager.beginTransaction().add(R.id.main_fragment, calculatorFragment, "calc").commit();
        fragmentManager.beginTransaction().show(calculatorFragment).commit();

        fragmentManager.beginTransaction().add(R.id.main_fragment, substanceFragment, "sub").commit();
        fragmentManager.beginTransaction().hide(substanceFragment).commit();

        fragmentManager.beginTransaction().add(R.id.main_fragment, fromFragment, "from").commit();
        fragmentManager.beginTransaction().hide(fromFragment).commit();

        fragmentManager.beginTransaction().add(R.id.main_fragment, toFragment, "to").commit();
        fragmentManager.beginTransaction().hide(toFragment).commit();
    }

    public void updateTextView(String str){
        displayFragment.updateFromVal(str);
    }

    public void updateSubstance(Pair<String, Float> pair) { displayFragment.updateSubstance(pair); }

    public void updateFrom(String str) { displayFragment.updateFromName(str); }

    public void navigate(int destination){
        switch (destination){
            case CALCULATOR:
                hideAllFragments();
                fragmentManager.beginTransaction().show(calculatorFragment).commit();
                break;
            case SUBSTANCE:
                hideAllFragments();
                fragmentManager.beginTransaction().show(substanceFragment).commit();
                break;
            case FROM:
                hideAllFragments();
                fragmentManager.beginTransaction().show(fromFragment).commit();
                break;
            case TO:
                hideAllFragments();
                fragmentManager.beginTransaction().show(toFragment).commit();
                break;
        }
    }

    public void hideAllFragments(){
        fragmentManager.beginTransaction().hide(calculatorFragment).commit();
        fragmentManager.beginTransaction().hide(substanceFragment).commit();
        fragmentManager.beginTransaction().hide(fromFragment).commit();
        fragmentManager.beginTransaction().hide(toFragment).commit();
    }

    public void updateToName(String str) { displayFragment.updateToName(str); }

    public void updateToValue(String str) { displayFragment.updateToValue(str); }

    public void calculate(Pair<String, Float> substance, Pair<String, Float> from, String to) {
        float answer = conversion.calculate(substance, from, to);
        updateToValue(Float.toString(answer));
    }

    public boolean updateView(int viewID) {

        return false;
    }

    public String fetchViewText(int viewID) {

        switch (viewID) {
            case R.id.display_tv_sub_name:
                return displayFragment.fetchViewText(viewID);
            case R.id.display_tv_density:
                return displayFragment.fetchViewText(viewID);
            case R.id.display_tv_from_name:
                return displayFragment.fetchViewText(viewID);
            case R.id.display_tv_from_val:
                return displayFragment.fetchViewText(viewID);
            case R.id.display_tv_to_name:
                return displayFragment.fetchViewText(viewID);
            case R.id.display_tv_to_val:
                return displayFragment.fetchViewText(viewID);
            case R.id.calc_btn_0:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_1:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_2:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.btn_3:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.btn_4:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_5:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_6:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_7:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_8:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_9:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_clear:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_enter:
                return calculatorFragment.fetchViewText(viewID);
            case R.id.calc_btn_point:
                return calculatorFragment.fetchViewText(viewID);
            default:
                return getResources().getString(R.string.blank);
        }

    }

}
