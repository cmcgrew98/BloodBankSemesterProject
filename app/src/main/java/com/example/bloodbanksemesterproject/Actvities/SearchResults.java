package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

import com.example.bloodbanksemesterproject.Adapters.SearchAdapter;
import com.example.bloodbanksemesterproject.DataModels.Donor;
import com.example.bloodbanksemesterproject.DataModels.RequestDataModel;
import com.example.bloodbanksemesterproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
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
//This is the list of donors.
    List<Donor> donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        //Makes the donor list into a new ArrayList.
        donorList = new ArrayList<>();

        //The json variable.
        String json;
        String city, blood_group;

        //Helps with setting everything up in json.
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        city = intent.getStringExtra("city");
        blood_group = intent.getStringExtra("blood_group");
        TextView heading = findViewById(R.id.heading);
        String string = "Donors in" + city + " with blood group " + blood_group;
        heading.setText(string);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Donor>>() {
        }.getType();
        List<Donor> dataModels = gson.fromJson(json, type);
        if (dataModels != null && dataModels.isEmpty()) {
            heading.setText("There were no results");
        } else if (dataModels != null) {
            donorList.addAll(dataModels);
        }
        //This is the recyclerView for displaying the results.
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        SearchAdapter adapter = new SearchAdapter(donorList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }
}
