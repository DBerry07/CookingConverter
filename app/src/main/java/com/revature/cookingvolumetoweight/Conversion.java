package com.revature.cookingvolumetoweight;

public class Conversion {

    public static final String ounces = "OUNCES";
    public static final String pounds = "POUNDS";
    public static final String grams = "GRAMS";
    public static final String millilitres = "MILLILITRES";
    public static final String fluidOunces = "FLUID OUNCES";
    public static final String teaspoons = "TEASPOONS";
    public static final String tablespoons = "TABLESPOONS";

    public static float volumeToWeight(float density, float millilitres) {
        float grams = millilitres * density;
        return grams;
    }

    //WEIGHTS
    public static float gramsOunces(String convertTo, float from){
        float to = 0.0f;
        switch (convertTo){
            case grams:
                to = from * 28.35f;
                break;
            case ounces:
                to = from / 28.35f;
                break;
        }
        return to;
    }

    public static float gramsPounds(String convertTo, float from){
        float to = 0.0f;
        switch (convertTo){
            case grams:
                to = gramsOunces(grams, from * 16f);
                break;
            case pounds:
                to = gramsOunces(ounces, from) / 16f;
                break;
        }
        return to;
    }

    //VOLUMES
    public static float millilitresFluidOunces(String convertTo, float from){
        float to = 0.0f;
        switch (convertTo){
            case millilitres:
                to = from / 0.033814f;
                break;
            case fluidOunces:
                to = from * 0.033814f;
                break;
        }
        return to;
    }

    public static float millilitresTeaspoons(String convertTo, float from){
        float to = 0.0f;
        switch (convertTo){
            case millilitres:
                to = from * 4.92892f;
                break;
            case teaspoons:
                to = from / 4.92892f;
                break;
        }
        return to;
    }

    public static float millilitresTablespoon(String convertTo, float from){
        float to = 0.0f;
        switch (convertTo){
            case millilitres:
                to = millilitresTeaspoons(grams, from * 3);
                break;
            case tablespoons:
                to = millilitresTeaspoons(teaspoons, from) / 3;
                break;
        }
        return to;
    }



}
