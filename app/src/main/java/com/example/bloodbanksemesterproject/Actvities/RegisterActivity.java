package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText nameEditText, cityEditText, mobileNumberEditText,
            emailEditText, bloodGroupEditText, passwordEditText;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEditText = findViewById(R.id.name);
        cityEditText = findViewById(R.id.city);
        mobileNumberEditText = findViewById(R.id.mobilenumber);
        emailEditText = findViewById(R.id.email);
        bloodGroupEditText = findViewById(R.id.bloodgroup);
        passwordEditText = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
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

    private void register(final String name, final String city, final String mobileNumber, final String email, final String bloodGroup, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            if(response.equals("Success")) {
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
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
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
