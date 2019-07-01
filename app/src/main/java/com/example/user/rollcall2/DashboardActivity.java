package com.example.user.rollcall2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    public class ClassCard{
        private String subjectName,subjectID,series,section;
        public ClassCard(String subjectName, String subjectID, String series, String section){
            this.subjectName= subjectName;
            this.subjectID=subjectID;
            this.series=series;
            this.section=section;
        }
    }
    ArrayList classCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        classCards = new ArrayList();
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        classCards.add( new ClassCard("Computer Algorithm", "CSE 3101", "15","A"));
        setClassCards();
    }
    public void test_button(View view){
        Intent intent = new Intent(this,RollCallActivity.class);
        startActivity(intent);
        setClassCards();
    }
    public void setClassCards(){
        final ViewGroup classLayout = findViewById(R.id.class_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < classCards.size(); i++) {
                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    layoutInflater.inflate(R.layout.class_card, classLayout);
                    ViewGroup card = (ViewGroup) classLayout.getChildAt(i);
                    if(i==0)
                        card.getChildAt(3).setVisibility(View.VISIBLE);           //shurur ta init hobe visible

                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TransitionManager.beginDelayedTransition(classLayout);
                            ViewGroup cardLayout = (ViewGroup) v.getParent();
                            for(int j=0;j<cardLayout.getChildCount();j++){
                                ViewGroup card = (ViewGroup) cardLayout.getChildAt(j);
                                if(v==card && card.getChildAt(3).getVisibility() == View.GONE){
                                    card.getChildAt(3).setVisibility(View.VISIBLE);
                                } else if(card.getChildAt(3).getVisibility() == View.VISIBLE){
                                    card.getChildAt(3).setVisibility(View.GONE);
                                }
                            }
                        }
                    });

                    ViewGroup cardButtonLayout = (ViewGroup) card.getChildAt(3);
                    for(int j=0;j<4;j++){
                        cardButtonLayout.getChildAt(j).setOnClickListener(new View.OnClickListener() {  //prottekta button
                            @Override                                                                         //er onlclick
                            public void onClick(View v) {
                                int cardId =0, buttonId = 0;
                                ViewGroup lowerCard = (ViewGroup) v.getParent();
                                for(int k=0;k<4;k++){
                                    if(lowerCard.getChildAt(k) ==v )
                                        buttonId=k;
                                }
                                for(int k = 0;k <classLayout.getChildCount();k++){
                                    if(classLayout.getChildAt(k) == lowerCard.getParent())
                                        cardId=k;
                                }
                                Log.d("kunkka", Integer.toString(cardId) + " " + Integer.toString(buttonId));
                                startActivity(new Intent(getApplicationContext(),RollCallActivity.class));
                            }
                        });
                    }
                }
            }
        },200);
    }
    public void AddClassMethod(View view){
        Intent intent = new Intent (this, AddClassActivity.class);
        startActivity(intent);
    }
}
