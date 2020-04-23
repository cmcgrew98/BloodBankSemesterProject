package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bloodbanksemesterproject.R;

public class SplashScreen extends AppCompatActivity {
    /*
    name: Clara McGrew
    project: Android Blood Bank Semester Project
    date: 4/21/2020 Presentation
    date: 4/23/2020 Final code upload
    relevant github libraries/code/repositories used in this application include
    VolleySingleton.java
    https://gist.github.com/RISHABH3821/6b1e58de77a4e4909b097c2ca51acf6e
    StringRequest.java
    https://gist.github.com/RISHABH3821/bc48fe91119c2efa14cfab1accc71376
    ExampleAdapter.java
    https://gist.github.com/RISHABH3821/f54ed1d96d95d827ac74bb86aee39521
    Android Networking library
    https://github.com/amitshekhariitbhu/Fast-Android-Networking

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Basically the splash meal that you see at the start.
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }, 2500);
    }
}
