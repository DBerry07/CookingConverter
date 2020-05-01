package com.revature.cookingvolumetoweight;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Pair;

import java.util.ArrayList;

public class Conversion {

    private static boolean unitsSet = false;
    private ArrayList<Float> toGrams = new ArrayList<>();
    private ArrayList<Float> fromMilli = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Float> densities = new ArrayList<>();

    private Resources resources;

    private static int ouncesPos = 2;
    private static int poundsPos = 3;
    private static String ounce = "ounce";
    private static String pound = "pound";

    private static int fluidOuncePos = 0;
    private static int quartPos = 2;

    private static String fluidOunce = "fluid";
    private static String quart = "quart";
    private static String teaspoons = "tea";
    private static String tablespoons = "table";

    public Conversion(Resources res) {
        resources = res;
        getArrays();
    }


    private void getArrays() {
        TypedArray array = resources.obtainTypedArray(R.array.to_grams);
        for (int i = 0; i < array.length(); i++) {
            toGrams.add(array.getFloat(i, 0.00f));
        }

        array = resources.obtainTypedArray(R.array.from_milli);
        for (int i = 0; i < array.length(); i++) {
            fromMilli.add(array.getFloat(i, 0.00f));
        }

        TypedArray arrayNames = resources.obtainTypedArray(R.array.substance_names);
        for (int i = 0; i < arrayNames.length(); i++){
            names.add(arrayNames.getString(i));
        }

        array = resources.obtainTypedArray(R.array.substance_values);
        for (int i = 0; i < array.length(); i++){
            densities.add(array.getFloat(i, 0.00f));
        }
    }


    public float calculate(Pair<String, Float> substance, Pair<String, Float> from, String to) {
        float input, output, midstage = 0.0f;
        String substanceName = substance.first;
        Float substanceDensity = substance.second;
        String fromName = from.first;
        Float fromValue = from.second;

        input = toGrams(fromName, fromValue);
        midstage = gramsToMillilitres(input, substance);
        output = fromMillilitres(to, midstage);

        return output;
    }

    public float toGrams(String fromName, Float fromValue) {
        float answer = 0.00f;

        if (fromName.toUpperCase().contains(ounce.toUpperCase())) {
            answer = fromValue * toGrams.get(ouncesPos);
        }
        else if (fromName.toUpperCase().contains(pound.toUpperCase())) {
            answer = fromValue * toGrams.get(poundsPos);
        }

        return answer;
    }

    private float gramsToMillilitres(float value, Pair<String, Float> substance) {
        return value / substance.second;
    }

    public float fromMillilitres(String toName, Float toValue) {
        float answer = 0.00f;

        if (toName.toUpperCase().contains(fluidOunce.toUpperCase())) {
            answer = toValue * fromMilli.get(fluidOuncePos);
        }
        else if (toName.toUpperCase().contains(quart.toUpperCase())) {
            answer = toValue * fromMilli.get(quartPos);
        }

        return answer;
    }




}
