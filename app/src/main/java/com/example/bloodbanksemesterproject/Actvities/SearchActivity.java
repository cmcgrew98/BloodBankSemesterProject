package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText bloodGroupEditText, cityEditText;
        bloodGroupEditText = findViewById(R.id.et_bloodGroup);
        cityEditText = findViewById(R.id.city_et);
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

    private void getSearchResults(final String bloodGroup, final String city) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.search_donors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //json response

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
private void showMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
}
}
