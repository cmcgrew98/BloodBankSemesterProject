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
import com.example.bloodbanksemesterproject.R;
import com.example.bloodbanksemesterproject.Utils.Endpoints;
import com.example.bloodbanksemesterproject.Utils.VolleySingleton;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    /*
    name: Clara McGrew
    date of Presentation: 4/21/2020
    project; Android Blood Bank semester project
     */

    //These are the EditText variables
    private EditText nameEditText, cityEditText, mobileNumberEditText,
            emailEditText, bloodGroupEditText, passwordEditText;

    //This is the button
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //This binds the variables to their respective ids.
        nameEditText = findViewById(R.id.name);
        cityEditText = findViewById(R.id.city);
        mobileNumberEditText = findViewById(R.id.mobilenumber);
        emailEditText = findViewById(R.id.email);
        bloodGroupEditText = findViewById(R.id.bloodgroup);
        passwordEditText = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        //This sets the onclick listener for the button, so that the email, name, city, and mobile number
        //are all submitted to the database.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, city, mobileNumber, email, bloodGroup, password;
                name = nameEditText.getText().toString();
                city = cityEditText.getText().toString();
                mobileNumber = mobileNumberEditText.getText().toString();
                email = emailEditText.getText().toString();
                bloodGroup = bloodGroupEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if(isValid(name, city, mobileNumber, email, bloodGroup, password)) {
                    register(name, city, mobileNumber, email, bloodGroup, password);
                }
            }
        });
    }

    //Register method which helps to make a request to the database to create a new user.
    private void register(final String name, final String city, final String mobileNumber, final String email, final String bloodGroup, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            if(response.equals("Success")) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", city).apply();
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                RegisterActivity.this.finish();
            }
            else {
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(RegisterActivity.this, "Something bad happened:(",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("city", city);
                parameters.put("mobile_number", mobileNumber);
                parameters.put("email", email);
                parameters.put("blood_group", bloodGroup);
                parameters.put("password", password);
                return parameters;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    //Show message called whenever there is a need.
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Checks to make sure that stuff is validated properly. Such as making sure that the blood group is valid
    //and the other things are not blank.
    private boolean isValid(String name, String city, String mobileNumber, String email, String bloodGroup,
    String password
    ) {
        List<String> validBloodGroups = new ArrayList<>();
        validBloodGroups.add("A+");
        validBloodGroups.add("A-");
        validBloodGroups.add("B+");
        validBloodGroups.add("B-");
        validBloodGroups.add("AB+");
        validBloodGroups.add("AB-");
        validBloodGroups.add("O+");
        validBloodGroups.add("O-");
        if(name.isEmpty()) {
            showMessage("You did not enter a name");
            return false;
        }
        else if (city.isEmpty()) {
            showMessage("You did not enter a city");
            return false;
        }
        else if(mobileNumber.isEmpty() || mobileNumber.length() != 10) {
            showMessage("You did not enter a correct mobile number. Please enter one that is 10 numbers long");
            return false;
        }
        else if(email.isEmpty()) {
            showMessage("You did not enter an email");
            return false;
        }
        else if(!validBloodGroups.contains(bloodGroup)) {
            showMessage("Please choose from the following blood groups" + validBloodGroups);
            return false;
        }
        else if(password.isEmpty()) {
            showMessage("You did not enter your password");
            return false;
        }

        return true;
    }
}
