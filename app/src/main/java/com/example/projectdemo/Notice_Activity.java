package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class Notice_Activity extends AppCompatActivity {

    EditText Title,Message;
    Button Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        Message=findViewById(R.id.discription);
        Title=findViewById(R.id.titleinput);
        Confirm=findViewById(R.id.save_btn);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                    FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",Title.getText().toString(),Message.getText().toString(),getApplicationContext(),Notice_Activity.this);

                    notificationsSender.SendNotifications();
               }
        });

    }
}