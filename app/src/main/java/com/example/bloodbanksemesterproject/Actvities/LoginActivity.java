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
EditText emailEditText, passwordEditText;
Button loginButton;
TextView signUpTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailLogin);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.sign_up);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            emailEditText.setError(null);
            passwordEditText.setError(null);
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if(isValid(email, password)) {
                login(email, password);
            }

            }
        });


    }
    private void login(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("Your credentials are invalid")) {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("email", email).apply();
                    LoginActivity.this.finish();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", response).apply();
                    LoginActivity.this.finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Something bad happened:(",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("email", email);
                parameters.put("password", password);
                return parameters;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private boolean isValid(String email, String password) {
        if(email.isEmpty()) {
            showMessage("The email field is empty");
            emailEditText.setError("The email field is empty");
            return false;

        }
        else if(password.isEmpty()) {
            showMessage("The password field is empty");
            passwordEditText.setError("The password field is empty");
            return false;
        }
        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
