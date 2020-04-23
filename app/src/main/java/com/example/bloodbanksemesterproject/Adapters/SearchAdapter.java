package com.example.bloodbanksemesterproject.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbanksemesterproject.Actvities.EmailActivity;
import com.example.bloodbanksemesterproject.Actvities.MainActivity;
import com.example.bloodbanksemesterproject.Actvities.SearchActivity;
import com.example.bloodbanksemesterproject.DataModels.Donor;
import com.example.bloodbanksemesterproject.DataModels.RequestDataModel;
import com.example.bloodbanksemesterproject.R;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
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
    //These are the declared variables.
    private List<Donor> dataSet;
    private Context context;

    //This is the SearchAdapter constructor.
    public SearchAdapter(
            List<Donor> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

//Viewholder with inflates the donor item layout.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donor_item, parent, false);
        return new ViewHolder(view);
    }

//OnBindViewHolder method to show results.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int position) {

        String string = "Name: " + dataSet.get(position).getName();
        string += "\nCity: " + dataSet.get(position).getCity();
        holder.message.setText(string);

        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for later
               Intent intent = new Intent(context, EmailActivity.class);
               context.startActivity(intent);

            }
        });
    }

//getItemCount returns the size.
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//Inner ViewHolder class
    class ViewHolder extends RecyclerView.ViewHolder {

        //Variables for the class
        TextView message;
        ImageView imageView, emailButton;

        ViewHolder(final View itemView) {
            super(itemView);
            //Binds the variables to their respective ids.
            message = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.image);
           emailButton = itemView.findViewById(R.id.call_button);

        }

    }

}
