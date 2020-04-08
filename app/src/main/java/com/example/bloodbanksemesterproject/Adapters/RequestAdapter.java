package com.example.bloodbanksemesterproject.Adapters;


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
import com.example.bloodbanksemesterproject.DataModels.RequestDataModel;
import com.example.bloodbanksemesterproject.R;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<RequestDataModel> dataSet;
    private Context context;

    public RequestAdapter(
            List<RequestDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_item_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int position) {

        holder.message.setText(dataSet.get(position).getMessage());
        Glide.with(context).load(dataSet.get(position).getUrl()).into(holder.imageView);
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmailActivity.class);
                context.startActivity(intent);
            }
        });
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, holder.message.getText().toString() + "\n\n Contact: " + dataSet.get(position).getEmail());
                share.putExtra(Intent.EXTRA_SUBJECT, "Would you be able to help?");
                context.startActivity(Intent.createChooser(share, "Share"));


            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        ImageView imageView, callButton, shareButton;

        ViewHolder(final View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.image);
            callButton = itemView.findViewById(R.id.call_button);
            shareButton = itemView.findViewById(R.id.share_button);

        }

    }

}
