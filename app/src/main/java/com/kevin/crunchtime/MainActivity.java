package com.kevin.crunchtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Key: exercise Value: calories per rep/min
    HashMap<String, Exercise> exerciseHashMap = new HashMap<String, Exercise>() {
        {
            put("Pushup", new Exercise("Pushup", 100.0 / 350, "reps"));
            put("Situp", new Exercise("Situp", 100.0 / 200, "reps"));
            put("Squat", new Exercise("Squat", 100.0 / 225, "reps"));
            put("Leg-lift", new Exercise("Leg-Lift", 100.0 / 25, "minutes"));
            put("Plank", new Exercise("Plank", 100.0 / 25, "minutes"));
            put("Jumping Jacks", new Exercise("Jumping Jacks", 100.0 / 10, "minutes"));
            put("Pullup", new Exercise("Pullups", 1.0, "reps"));
            put("Cycling", new Exercise("Cycling", 100.0 / 12, "minutes"));
            put("Walking", new Exercise("Walking", 100.0 / 20, "minutes"));
            put("Jogging", new Exercise("Jogging", 100.0 / 12, "minutes"));
            put("Swimming", new Exercise("Swimming", 100.0 / 13, "minutes"));
            put("Stair-climbing", new Exercise("Stair-Climbing", 100.0 / 15, "minutes"));
        }
    };
    Spinner exerciseSpinner;
    EditText editText;
    ListView conversionList;
    ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.exercise_amount);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateDisplay();
            }
        });

        exerciseSpinner = (Spinner) findViewById(R.id.exercise_spinner);
        ArrayList<String> exercises = new ArrayList<>(exerciseHashMap.keySet());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exercises);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(arrayAdapter);
        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText editText = (EditText) findViewById(R.id.exercise_amount);
                TextView hintText = (TextView) findViewById(R.id.unit_hint);
                Exercise exercise = exerciseHashMap.get(parent.getItemAtPosition(position).toString());
                if (editText.getText().toString().length() != 0) {
                    updateDisplay();
                }
                hintText.setText(exercise.unit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        exerciseAdapter = new ExerciseAdapter(this, exerciseHashMap.values().toArray(new Exercise[12]), 0);
        conversionList = (ListView) findViewById(R.id.conversion_list);
        conversionList.setAdapter(exerciseAdapter);
    }

    public void updateDisplay() {
        Exercise exercise = exerciseHashMap.get(exerciseSpinner.getSelectedItem().toString());
        EditText editText = (EditText) findViewById(R.id.exercise_amount);
        int quantity;
        if (editText.getText().length() > 0) {
            quantity = Integer.parseInt(editText.getText().toString());
        } else {
            quantity = 0;
        }
        double calories = exercise.value * quantity;
        String message = String.format("Calories burned: %.1f ", calories);
        TextView textView = (TextView) findViewById(R.id.calorie_display);
        textView.setText(message);
        exerciseAdapter.setCalories(calories);
        exerciseAdapter.notifyDataSetChanged();
    }
}
