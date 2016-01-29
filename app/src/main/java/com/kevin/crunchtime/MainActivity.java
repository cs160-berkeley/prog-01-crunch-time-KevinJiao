package com.kevin.crunchtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Key: exercise Value: calories per rep/min
    HashMap<String, Exercise> exerciseHashMap = new HashMap<String, Exercise>() {
        {
            put("Pushup", new Exercise(100.0 / 350, "reps"));
            put("Situp", new Exercise(100.0 / 200, "reps"));
            put("Squat", new Exercise(100.0 / 225, "reps"));
            put("Leg-lift", new Exercise(100.0 / 25, "minutes"));
            put("Plank", new Exercise(100.0 / 25, "minutes"));
            put("Jumping Jacks", new Exercise(100.0 / 10, "minutes"));
            put("Pullup", new Exercise(1.0, "reps"));
            put("Cycling", new Exercise(100.0 / 12, "minutes"));
            put("Walking", new Exercise(100.0 / 20, "minutes"));
            put("Jogging", new Exercise(100.0 / 12, "minutes"));
            put("Swimming", new Exercise(100.0 / 13, "minutes"));
            put("Stair-climbing", new Exercise(100.0 / 15, "minutes"));
        }
    };
    Spinner exerciseSpinner;
    EditText editText;

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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exercises);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(arrayAdapter);
        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText editText = (EditText) findViewById(R.id.exercise_amount);
                if (editText.getText().toString().length() == 0) {
                    Exercise exercise = exerciseHashMap.get(parent.getItemAtPosition(position).toString());
                    editText.setHint(exercise.unit);
                } else {
                    updateDisplay();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        String message = "Calories burned: " + calories;
        TextView textView = (TextView) findViewById(R.id.calorie_display);
        textView.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
