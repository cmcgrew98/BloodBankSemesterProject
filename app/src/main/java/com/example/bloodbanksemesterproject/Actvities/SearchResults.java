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
    date of presentation: 4/21/2020
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
