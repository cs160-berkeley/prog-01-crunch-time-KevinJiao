package com.kevin.crunchtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kevin on 1/29/16.
 */
public class ExerciseAdapter extends BaseAdapter {
    Context context;
    double calories;
    Exercise[] exercises;
    private static LayoutInflater inflater = null;

    public ExerciseAdapter(Context context, Exercise[] exercises, double calories) {
        this.context = context;
        this.calories = calories;
        this.exercises = exercises;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public int getCount() {
        return exercises.length;
    }

    @Override
    public Object getItem(int position) {
        return exercises[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.conversion_row, parent, false);
        }
        TextView exerciseName = (TextView) convertView.findViewById(R.id.row_exercise);
        TextView exerciseAmount = (TextView) convertView.findViewById(R.id.row_amount);
        Exercise exercise = exercises[position];
        exerciseName.setText(exercise.name);
        double amount = calories / exercise.value;
        String amountMsg = String.format("%.1f " + exercise.unit, amount);
        exerciseAmount.setText(amountMsg);
        return convertView;
    }
}
