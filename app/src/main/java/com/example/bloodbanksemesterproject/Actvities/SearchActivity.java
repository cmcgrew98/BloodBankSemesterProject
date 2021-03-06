package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

public class SearchActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_search);
        //Declares the EditText variables.
        final EditText bloodGroupEditText, cityEditText;

        //Binds the edittext variables to their respective ids.
        bloodGroupEditText = findViewById(R.id.et_bloodGroup);
        cityEditText = findViewById(R.id.city_et);

        //Binds the find donors button to its id.
        Button findDonorsButton = findViewById(R.id.find_donors);
        findDonorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bloodGroup = bloodGroupEditText.getText().toString();
                String city = cityEditText.getText().toString();
                if(isValid(bloodGroup, city)) {
                    getSearchResults(bloodGroup, city);
                }
            }
        });
    }
//Method called to get the search results.
    private void getSearchResults(final String bloodGroup, final String city) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.search_donors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //json response
            Intent intent = new Intent(SearchActivity.this, SearchResults.class);
            intent.putExtra("city", city);
            intent.putExtra("blood_group",bloodGroup);
            intent.putExtra("json", response);
            startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, "Something bad happened:(",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("city", city);
                parameters.put("blood_group", bloodGroup);
                return parameters;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

        //Checks to see of the pereson is searching for valid data and not leaving anything blank.
    private boolean isValid(String bloodGroup, String city) {
        List<String> validBloodGroups = new ArrayList<>();
        validBloodGroups.add("A+");
        validBloodGroups.add("A-");
        validBloodGroups.add("B+");
        validBloodGroups.add("B-");
        validBloodGroups.add("AB+");
        validBloodGroups.add("AB-");
        validBloodGroups.add("O+");
        validBloodGroups.add("O-");

         if(!validBloodGroups.contains(bloodGroup)) {
            showMessage("Please choose from the following blood groups" + validBloodGroups);
            return false;
        }   else if (city.isEmpty()) {
             showMessage("You did not enter a city");
             return false;
         }
         return true;
}

//Show message is called from everything above.
private void showMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
}
}
