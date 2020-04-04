package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.bloodbanksemesterproject.R;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
   private MenuInflater inflate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


}
