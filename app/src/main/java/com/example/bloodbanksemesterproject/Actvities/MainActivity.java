package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbanksemesterproject.Adapters.RequestAdapter;
import com.example.bloodbanksemesterproject.DataModels.RequestDataModel;
import com.example.bloodbanksemesterproject.R;
import com.example.bloodbanksemesterproject.Utils.Endpoints;
import com.example.bloodbanksemesterproject.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    /*
    Name: Clara McGrew
    Date of Presentation: April 21 2020
    Date Due: April 24?
    Project; Semester Project
    This project was heavily derived from a YouTube tutorial on how to create the Android Blood Bank.
    Here is the link below to the playlist.
    https://www.youtube.com/playlist?list=PLjhLAYtxanrSCh31QDpCse7q6ZUpMyu5A
    There are some differences, though in design. I designed the Android project myself, using Figma,
    which was the same website that the person who did these tutorials used.


     */

    //These are the variables
    private RecyclerView mRecyclerView;
    private List<RequestDataModel> requestDataModels;
    private MenuInflater inflate;
    private RequestAdapter requestAdapter;

    //This is the onCreate module for the MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This binds the textview to the Make Request button
        TextView makeRequestButton = findViewById(R.id.make_request_button);
        //By clicking on the makeRequestButton, the user will start a new Activity, the MakeRequestActivity,
        //thus going to another page.
        makeRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MakeRequestActivity.class));
            }
        });

        //This will be used to create an Array List of requestDataModels. They will add the list of objects from
        //Json to the homepage, particularly things that people (You) have uploaded before.
        requestDataModels = new ArrayList<>();

        //The next few lines bind the toolbar to the id. When someone clicks on it, it will take you to a new activity,
        //that enables you to search by city and blood group.
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                }
                return false;
            }
        });
        //Binds the recyclerView to the recyclerView id.
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        //With this request adapter, the requestDataModels are able to be used to populate the MainActivity page.
        requestAdapter = new RequestAdapter(requestDataModels, this);
        //Sets the adapter to the request adapter. There always has to be an adapter for a recycler view.
        mRecyclerView.setAdapter(requestAdapter);

        //Calls the populateHomePage to populate everything with stuff that you have uploaded.
        populateHomePage();
        //Enables you to search by location.
        TextView chooseLocation = findViewById(R.id.pick_location);
        String location = PreferenceManager.getDefaultSharedPreferences(this).getString("city", "no_city_found");
        if (!location.equals("no_city_found")) {
            chooseLocation.setText(location);
        }

    }
//This module enables you to populate the homepage by using Gson and Json.
    private void populateHomePage() {
        final String city =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", "no_city");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.get_requests, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<RequestDataModel>>() {
                }.getType();
                List<RequestDataModel> dataModels = gson.fromJson(response, type);
                requestDataModels.addAll(dataModels);
                requestAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something bad happened:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("city", city);
                return parameters;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
