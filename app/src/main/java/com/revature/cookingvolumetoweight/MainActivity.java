package com.revature.cookingvolumetoweight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final int CALCULATOR = 0;
    public final int SUBSTANCE = 1;
    public final int FROM = 2;
    public final int TO = 3;

    //private NavController navController;
    private FragmentManager fragmentManager;
    private CalculatorFragment calculatorFragment;
    private SubstanceFragment substanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //navController = Navigation.findNavController(findViewById(R.id.nav_host_main));
        fragmentManager = this.getSupportFragmentManager();
        calculatorFragment = new CalculatorFragment();
        substanceFragment = new SubstanceFragment();
    }

    private void addFragments(){
        fragmentManager.beginTransaction().add(R.id.main_fragment, substanceFragment, "sub").commit();
        fragmentManager.beginTransaction().hide(substanceFragment);

        fragmentManager.beginTransaction().add(R.id.main_fragment, calculatorFragment, "calc").commit();
        fragmentManager.beginTransaction().show(calculatorFragment);
    }

    public void navigate(int destination){
        switch (destination){
            case CALCULATOR:
                fragmentManager.beginTransaction().hide(substanceFragment).commit();
                fragmentManager.beginTransaction().show(calculatorFragment).commit();
                break;
            case SUBSTANCE:
                fragmentManager.beginTransaction().hide(calculatorFragment).commit();
                fragmentManager.beginTransaction().show(substanceFragment).commit();
                break;
        }
    }
}
