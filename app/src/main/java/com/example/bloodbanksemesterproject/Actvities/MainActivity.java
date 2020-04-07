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
    private RecyclerView mRecyclerView;
    private List<RequestDataModel> requestDataModels;
   private MenuInflater inflate;
   private RequestAdapter requestAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView makeRequestButton = findViewById(R.id.make_request_button);
        makeRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MakeRequestActivity.class));
            }
        });
        requestDataModels = new ArrayList<>();
   Toolbar toolbar = findViewById(R.id.toolbar);
   toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
       @Override
       public boolean onMenuItemClick(MenuItem item) {
           if(item.getItemId() == R.id.search_button){
               //Open Search
               startActivity(new Intent(MainActivity.this, SearchActivity.class));
           }
           return false;
       }
   });
       mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
 mRecyclerView.setLayoutManager(layoutManager);
 requestAdapter = new RequestAdapter(requestDataModels, this);
 populateHomePage();
 mRecyclerView.setAdapter(requestAdapter);
    }

private void populateHomePage() {
    final String city =
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", "no_city");
    StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.get_requests, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<RequestDataModel>>() {}.getType();
            List<RequestDataModel> dataModels = gson.fromJson(response, type);
            requestDataModels.addAll(dataModels);
            requestAdapter.notifyDataSetChanged();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Something bad happened:(",Toast.LENGTH_SHORT).show();
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
