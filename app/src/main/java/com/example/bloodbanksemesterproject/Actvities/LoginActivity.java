package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbanksemesterproject.R;
import com.example.bloodbanksemesterproject.Utils.Endpoints;
import com.example.bloodbanksemesterproject.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    /*
    name: Clara McGrew
    project: Android Blood Bank Semester Project
    date: 4/21/2020 Presentation
     */


    //These are the EditText variables that are used for logging into the MainActivity.
    EditText emailEditText, passwordEditText;
    //The login button
    Button loginButton;
    //This is the signUpTextView. If clicked, you will be taken to the Register page.
    TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Binds the variables to their respective ids.
        emailEditText = findViewById(R.id.emailLogin);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.sign_up);
        //sets the signUpTextView click listener so that you will go to the Register page.
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //Sets the loginButton's  on-click listener, to call the login method if the credentials are valid.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.setError(null);
                passwordEditText.setError(null);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (isValid(email, password)) {
                    login(email, password);
                }
            }
        });
    }

    //This is the login method.
    private void login(final String email, final String password) {
        //Creates a StringRequest method, which will refer to the login_url, specified in the Endpoints class.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (!response.equals("Your credentials are invalid")) {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("email", email).apply();
                    LoginActivity.this.finish();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", response).apply();
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            //An error listener if something didn't get stored correctly.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Something bad happened:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            //Creates a hashmap nd puts the email and password parameters into it.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email);
                parameters.put("password", password);
                return parameters;
            }
        };

        //Makes sure to add the request to the requestQueue, so that everything is stored when the user logs in.
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    //This makes sure that the user actually does enter in an email and a password.
    private boolean isValid(String email, String password) {
        if (email.isEmpty()) {
            showMessage("The email field is empty");
            emailEditText.setError("The email field is empty");
            return false;

        } else if (password.isEmpty()) {
            showMessage("The password field is empty");
            passwordEditText.setError("The password field is empty");
            return false;
        }
        return true;
    }

    //Shows whatever message needs to be shown, in the classes above.
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
