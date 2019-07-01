package com.example.user.rollcall2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RollCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call);
        String days[] = new String[] {"A","B","C","D","E"};
        String cycle[] = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13"};


        /*ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,days);
        ( (Spinner) findViewById(R.id.day_spinner)).setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,cycle);
        Spinner spinner =  findViewById(R.id.cycle_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(5);*/
    }

}
