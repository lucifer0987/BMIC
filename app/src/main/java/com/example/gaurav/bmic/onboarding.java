package com.example.gaurav.bmic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class onboarding extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //slide 1
        addFragment(new Step.Builder()
                .setContent("Its is a new age BMI calculator."+"\nIt will help you get your perfect BMI score, you have always wanted.")
                .setDrawable(R.drawable.draw1)
                .setBackgroundColor(Color.parseColor("#1976d2")) // int background color
                .build());

        //slide 2
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#e91e63")) // int background color
                .setDrawable(R.drawable.draw) // int top drawable
                .setContent("Enter your basic details asked in the app." +"\nIn return, you will get trainer picked Exercises to keep you fit.")
                .build());
    }

    @Override
    public void finishTutorial() {
        Toast.makeText(this, "Tutorial finished", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(onboarding.this, main.class);
        startActivity(myIntent);
        finish();
    }
}

