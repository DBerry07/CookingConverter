package com.revature.cookingvolumetoweight.pojos;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Pair;

import com.revature.cookingvolumetoweight.R;

import java.util.ArrayList;

public class Conversion {

    private static boolean unitsSet = false;
    private ArrayList<Float> arrayWeightToGrams = new ArrayList<>();
    private ArrayList<Float> arrayMilliToVolume = new ArrayList<>();
    private ArrayList<Float> arraySubstanceDensities = new ArrayList<>();
    private ArrayList<String> arraySubstanceNames = new ArrayList<>();
    private ArrayList<String> arrayWeightUnitNames = new ArrayList<>();
    private ArrayList<String> arrayVolumeUnitNames = new ArrayList<>();

    private Resources resources;

    /*private static int grainsPos, dramsPos, ouncesPos, poundsPos;
    private static int milligramsPos, centigramsPos, decigramsPos, gramsPos, kilosgramsPos;

    private static String grams, kilograms;
    private static String ounces, pounds;

    private static int fluidOuncesPos, quartsPos;

    private static String fluidOunces, quarts;
    private static String teaspoons = "tea";
    private static String tablespoons = "table";*/

    public Conversion(Resources res) {
        resources = res;
        setWeightStrings();
        setVolumeStrings();
        /*setWeightInts();
        setVolumeInts();*/
        getArrays();
    }

    private void setWeightStrings() {
        TypedArray array = resources.obtainTypedArray(R.array.units_weights);
        for (int i = 0; i < array.length(); i++) {
            arrayWeightUnitNames.add(array.getString(i).toUpperCase());
        }
    }

    private void setVolumeStrings() {
        TypedArray array = resources.obtainTypedArray(R.array.units_volume);
        for (int i = 0; i < array.length(); i++) {
            arrayVolumeUnitNames.add(array.getString(i).toUpperCase());
        }
    }

    //Copies resource arrays to object arrays
    private void getArrays() {

        //Assigns resource array to variable
        TypedArray array = resources.obtainTypedArray(R.array.to_grams);
        //Copies resource array contents to specific object array
        for (int i = 0; i < array.length(); i++) {
            arrayWeightToGrams.add(array.getFloat(i, 0.00f));
        }

        //Assigns resource array to variable
        array = resources.obtainTypedArray(R.array.from_milli);
        //Copies resource array contents to specific object array
        for (int i = 0; i < array.length(); i++) {
            arrayMilliToVolume.add(array.getFloat(i, 0.00f));
        }

        //Assigns resource array to variable
        TypedArray arrayNames = resources.obtainTypedArray(R.array.substance_names);
        //Copies resource array contents to specific object array
        for (int i = 0; i < arrayNames.length(); i++){
            arraySubstanceNames.add(arrayNames.getString(i));
        }

        //Assigns resource array to variable
        array = resources.obtainTypedArray(R.array.substance_values);
        //Copies resource array contents to specific object array
        for (int i = 0; i < array.length(); i++){
            arraySubstanceDensities.add(array.getFloat(i, 0.00f));
        }
    }


    public float calculate(Pair<String, Float> substance, Pair<String, Float> entry, String answerName) {
        float input = 0.0f, midstage = 0.0f, output = 0.0f;

        if (entry.first.isEmpty() || substance.first.isEmpty() || answerName.isEmpty()) {
            return output;
        }

        if (arrayWeightUnitNames.contains(entry.first.toUpperCase())) {
            input = convertWeightToGrams(entry.first, entry.second);
        }
        else if (arrayVolumeUnitNames.contains(entry.first.toUpperCase())) {
            input = convertVolumeToMillilitres(entry.first, entry.second);
        }

        if (arrayWeightUnitNames.contains(entry.first.toUpperCase()) && arrayVolumeUnitNames.contains(answerName.toUpperCase())) {
            midstage = convertGramsToMillilitres(input, substance);
        }
        else if (arrayVolumeUnitNames.contains(entry.first.toUpperCase()) && arrayWeightUnitNames.contains(answerName.toUpperCase())) {
            midstage = convertMillilitresToGrams(input, substance);
        }
        else if (arrayVolumeUnitNames.contains(entry.first.toUpperCase()) && arrayVolumeUnitNames.contains(answerName.toUpperCase())) {
            midstage = input;
        }
        else if (arrayWeightUnitNames.contains(entry.first.toUpperCase()) && arrayWeightUnitNames.contains(answerName.toUpperCase())) {
            midstage = input;
        }

        if (arrayVolumeUnitNames.contains(answerName.toUpperCase())) {
            output = convertMillilitresToVolume(answerName, midstage);
        }
        else if (arrayWeightUnitNames.contains(answerName.toUpperCase())) {
            output = convertGramsToWeight(answerName, midstage);
        }

        return output;
    }

    public float convertWeightToGrams(String entryName, Float entryVal) {
        float answer = 0.00f;
        String upperEntry = entryName.toUpperCase();

        int entryPos = arrayWeightUnitNames.indexOf(upperEntry);
        answer = entryVal * arrayWeightToGrams.get(entryPos);

        return answer;
    }

    public float convertGramsToWeight(String answerName, Float answerVal) {
        float answer = 0.00f;
        String upperAnswer = answerName.toUpperCase();

        int answerPos = arrayWeightUnitNames.indexOf(upperAnswer);
        answer = answerVal / arrayWeightToGrams.get(answerPos);

        return answer;
    }

    private float convertGramsToMillilitres(float value, Pair<String, Float> substance) {
        return value / substance.second;
    }
    private float convertMillilitresToGrams(float value, Pair<String, Float> substance) {
        return value * substance.second;
    }

    public float convertMillilitresToVolume(String answerName, Float answerVal) {
        float answer = 0.00f;
        String upperAnswer = answerName.toUpperCase();

        int answerPos = arrayVolumeUnitNames.indexOf(upperAnswer);
        answer = answerVal * arrayMilliToVolume.get(answerPos);

        return answer;
    }

    public float convertVolumeToMillilitres(String entryName, float entryVal) {
        float answer = 0.00f;
        String upperEntry = entryName.toUpperCase();

        int entryPos = arrayVolumeUnitNames.indexOf(upperEntry);
        answer = entryVal / arrayMilliToVolume.get(entryPos);

        return answer;
    }




}
