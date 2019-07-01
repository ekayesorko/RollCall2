package com.example.user.rollcall2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddClassActivity extends AppCompatActivity {

    boolean firstTime;
    int selectedSeries, selectedSection;
    Spinner spinner;
    ViewGroup.LayoutParams sectionYesParams,sectionNoParam,seriesYesParam,seriesNoParam;
    float scale;
    String [] subjectArray = {"CSE 3101: Computer Algorithm", "CSE 3101: Computer Algorithm" ,
            "CSE 3101: Computer Algorithm" , "CSE 3105: Database Management And Queueing Theory",
            "CSE 3101: Computer Algorithm"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        firstTime=true;
        scale= getApplicationContext().getResources().getDisplayMetrics().density;
        selectedSeries=R.id.series_button18;
        selectedSection=R.id.section_button_a;
        initializeSubject();
    }
    public void initializeSubject(){
        final ViewGroup subjectLayout = findViewById(R.id.subjectLayout);
        TextView subjectCard;
        for(int i=0;i<5;i++){
            subjectCard = (TextView) subjectLayout.getChildAt(i);
            subjectCard.setText(subjectArray[i]);
            subjectCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fade myfade = new Fade(Fade.IN);
                    TransitionManager.beginDelayedTransition(subjectLayout,myfade);
                    for (int j = 0;j<5 ; j++){
                        TextView child = (TextView) ((ViewGroup)findViewById(R.id.subjectLayout)).getChildAt(j);
                        if(child == v){
                            child.setBackgroundResource(R.drawable.green_solid);
                            child.setTextColor(getResources().getColor(R.color.mywhite));
                        }else{
                            child.setBackgroundResource(R.drawable.green_border);
                            child.setTextColor(getResources().getColor(R.color.myblack));
                        }
                    }
                }
            });
        }
    }

    public void CancelButtonMethod(View view){

    }
    public void NextButtonMethod(View view){
        Intent intent = new Intent(this,AddClassActivityTwo.class);
        intent.putExtra("classFirstRoll",1503001);
        startActivity(intent);
    }

    void initializeMeasure(){
        sectionYesParams = findViewById(R.id.section_button_a).getLayoutParams();
        sectionNoParam = findViewById(R.id.section_button_c).getLayoutParams();
        seriesNoParam = findViewById(R.id.series_button15).getLayoutParams();
        seriesYesParam= findViewById(R.id.series_button18).getLayoutParams();
    }

    public void SeriesClickMethod(View view){
        if(firstTime) {
            initializeMeasure();
            firstTime=false;
        }
        if(view.getId()==selectedSeries)
            return;
        else
            selectedSeries = view.getId();
        LinearLayout seriesLayout = findViewById(R.id.series_layout);
        TransitionManager.beginDelayedTransition(seriesLayout);
        for(int i=0;i<4;i++){
            Button button = (Button) seriesLayout.getChildAt(i);
            if(button.getId() == view.getId()){
                button.setLayoutParams(seriesYesParam);
                button.setBackgroundResource(R.drawable.green_solid);
                button.setText(Integer.toString(15+i) + " Series");
                button.setTextColor(getResources().getColor(R.color.mywhite));
                button.setTextSize( (int) (18) );
            }else{
                button.setLayoutParams(seriesNoParam);
                button.setBackgroundResource(R.color.mywhite);
                button.setText(Integer.toString(15+i));
                button.setTextSize( (int) (15) );
                button.setTextColor(getResources().getColor(R.color.myblack));
            }
        }
        if (view.getId()==R.id.series_button18){ //for dealing with 18 extra section
            findViewById(R.id.section_button_c).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.section_button_c).setVisibility(View.GONE);
            if(selectedSection==R.id.section_button_c)
                SectionClickMethod(findViewById(R.id.section_button_b));
        }
    }
    public void SectionClickMethod(View view){
        if(firstTime) {
            initializeMeasure();
            firstTime=false;
        }
        if(view.getId()==selectedSection)
            return;
        else
            selectedSection = view.getId();
        LinearLayout section_layout = findViewById(R.id.section_layout);
        TransitionManager.beginDelayedTransition(section_layout);
        Button button;
        for(int i=0;i<3;i++){
            button = (Button) section_layout.getChildAt(i);
            if(button.getId() == view.getId()){
                button.setLayoutParams(sectionYesParams);
                button.setBackgroundResource(R.drawable.green_solid);
                button.setText("Section " + (char) ('A'+i));
                button.setTextSize(18);
                button.setTextColor(getResources().getColor(R.color.mywhite));
            }else{
                button.setLayoutParams(sectionNoParam);
                button.setBackgroundResource(R.color.mywhite);
                button.setText( Character.toString ( (char) ('A'+i)) );
                button.setTextSize(15);
                button.setTextColor(getResources().getColor(R.color.myblack));
            }
        }
    }
}