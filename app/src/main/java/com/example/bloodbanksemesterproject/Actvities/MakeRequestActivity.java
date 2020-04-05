package com.example.bloodbanksemesterproject.Actvities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bloodbanksemesterproject.R;

public class MakeRequestActivity extends AppCompatActivity {

    EditText message;
    TextView chooseImage;
    ImageView postImage;
    Button submitButton;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);
        message = findViewById(R.id.message);
        chooseImage = findViewById(R.id.choose_text);
        postImage = findViewById(R.id.post_image);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(isValid())
            {
                //Write code to upload the post
                uploadRequest(message.getText().toString());
            }
            }
        });

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code to pick image
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });
    }

    private void uploadRequest(String message){
        //code that will upload the message
    }

    private void uploadImage(){
        ///Code to upload the image

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK) {
            if(data != null){
             imageUri = data.getData();
             Glide.with(getApplicationContext()).load(imageUri).into(postImage);
            }

        }
    }

    private boolean isValid(){
        if(message.getText().toString().isEmpty()) {
            showMessage("Message is empty. Please enter a message in the message box");
            return false;
        }
        return true;
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
