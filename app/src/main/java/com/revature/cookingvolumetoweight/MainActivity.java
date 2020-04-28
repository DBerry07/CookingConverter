package com.revature.cookingvolumetoweight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final int CALCULATOR = 0;
    public final int SUBSTANCE = 1;
    public final int FROM = 2;
    public final int TO = 3;

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

    public void updateSubstance(Pair<String, Double> pair) { displayFragment.updateSubstance(pair); }

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

}
