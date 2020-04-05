package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bloodbanksemesterproject.Adapters.RequestAdapter;
import com.example.bloodbanksemesterproject.DataModels.RequestDataModel;
import com.example.bloodbanksemesterproject.R;

import java.util.ArrayList;
import java.util.List;
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
        RequestDataModel requestDataModel = new RequestDataModel("Message: Blah Blah\n" +
                "Blah Blah Blah Blah\n" +
                "Blah Blah Blah Blah\n" +
                "Blah Blah Blah Blah\n" +
                "Blah Blah Blah Blah\n" +
                "Blah Blah Blah Blah ", "https://images.unsplash.com/photo-1532611322744-369123d95bd3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");

    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestAdapter.notifyDataSetChanged();
    }
}
