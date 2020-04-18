package com.example.bloodbanksemesterproject.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bloodbanksemesterproject.R;

public class EmailActivity extends AppCompatActivity {
    /*
    name: Clara McGrew
    date of Presentation: 4/21/2020
    project: Android Blood bank semester project
     */


//EditText variables.
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //Binds the edittext variables to their respective ids.
        mEditTextTo = findViewById(R.id.toEditText);
        mEditTextSubject = findViewById(R.id.subjectEditText);
        mEditTextMessage = findViewById(R.id.messageEditText);
        //Binds the button to its id,
        Button sendButton = findViewById(R.id.sendButton);
        //Calls the send email method
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    //The sendEmail method to send an email.
    private void sendEmail() {
        String listOfReceivers = mEditTextTo.getText().toString();
        String[] receivers = listOfReceivers.split(",");
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, receivers);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Pick an email client"));
    }
}
