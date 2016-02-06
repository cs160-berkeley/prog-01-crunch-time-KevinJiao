package com.kevin.crunchtime;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * @author Kevin Jiao
 */
public class SpinnerListener implements AdapterView.OnItemSelectedListener {
    private Context context;

    public SpinnerListener(Context context) {
        this.context = context;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        TextView text = (TextView) view.findViewById(R.id.calorie_display);
        if (text == null){
            return;
        }
        text.setText(parent.getItemAtPosition(pos).toString());
    }
}
