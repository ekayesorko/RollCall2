package com.example.user.rollcall2;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddClassActivityTwo extends AppCompatActivity {

    int rollCardIndex;
    ArrayList rollList;
    EditText addRollEditText;
    ScrollView rollScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_two);
        rollList= new ArrayList();
        initializeUIComponent();
        initializeRollComponent();
    }
    public void CreateButtonMethod(View view){

    }
    public void BackButtonMethod(View view){

    }
    void initializeUIComponent(){
        findViewById(R.id.add_roll_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.add_roll_edittext);
                String text = editText.getText().toString();
                Integer roll = new Integer( Integer.parseInt(text) );
                ViewGroup rollFlexbox= findViewById(R.id.roll_container_flexbox);
                if(text.length()==7){
                    if(!rollList.contains(roll)) {
                        rollList.add(roll);
                        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().
                                getSystemService(LAYOUT_INFLATER_SERVICE);
                        layoutInflater.inflate(R.layout.roll_card,rollFlexbox);
                        ((TextView)((ViewGroup)rollFlexbox.getChildAt(rollCardIndex)).
                                getChildAt(0)).setText(text);
                        final ScrollView rollScrollView = findViewById(R.id.roll_scrollview);
                        rollScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                rollScrollView.fullScroll(rollScrollView.FOCUS_DOWN);
                            }
                        });
                        ViewGroup rollCard = (ViewGroup) rollFlexbox.getChildAt(rollCardIndex);
                        TextView rollText = (TextView) rollCard.getChildAt(0);
                        Button rollButton = (Button) rollCard.getChildAt(1);
                        rollButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((ViewGroup)v.getParent()).setVisibility(View.GONE);
                            }
                        });
                        rollText.setText(text);
                        editText.getText().clear();
                        rollCardIndex++;
                    }else{
                        Toast.makeText(AddClassActivityTwo.this, "already inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        addRollEditText = findViewById(R.id.add_roll_edittext);
        addRollEditText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override
                    public void afterTextChanged(Editable s) {
                        String text = addRollEditText.getText().toString();
                        Button button = ((Button)findViewById(R.id.add_roll_button));
                        if(text.length()==7){
                            button.setTextColor(getResources().getColor(R.color.mywhite));
                        }else{
                            button.setTextColor(getResources().getColor(R.color.mygreen));
                        }
                    }
                }
        );
    }

    void initializeRollComponent(){
        rollCardIndex = 60;
        final int roll = 1503001;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final ScrollView rollScrollView = findViewById(R.id.roll_scrollview);
                rollScrollView.setVisibility(View.VISIBLE);
                final ViewGroup rollFlexBox = findViewById(R.id.roll_container_flexbox);
                TextView textView;
                ViewGroup cardIterator;
                Button button;
                for(int i=0;i<60;i++){
                    rollList.add(roll+i);
                    layoutInflater.inflate(R.layout.roll_card,rollFlexBox,true);
                    cardIterator =  ( (ViewGroup) rollFlexBox.getChildAt(i));
                    textView = (TextView) cardIterator.getChildAt(0);
                    button = (Button) cardIterator.getChildAt(1);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer i = new Integer( Integer.parseInt( (
                                    (TextView) ((ViewGroup) v.getParent()).getChildAt(0)).
                                    getText().toString() ) );
                            rollList.remove(i);
                            ( (View)v.getParent()).setVisibility(View.GONE);
                        }
                    });
                    textView.setText(Integer.toString(roll + i));
                }
                rollScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        rollScrollView.fullScroll(rollScrollView.FOCUS_DOWN);
                    }
                });
            }
        }, 100);
    }
}
