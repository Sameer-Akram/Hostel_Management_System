package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class WardenDashboard extends AppCompatActivity {
    ImageView Img1;

    ImageView AboutUS;
    ImageView Exit;
    ImageView Notice;
    ImageView Mess;
    ImageView Attendence;
    ImageView Rooms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Img1=findViewById(R.id.student2);
        AboutUS=findViewById(R.id.student);
        Exit=findViewById(R.id.student3);
        Notice=findViewById(R.id.attendence);
        Mess=findViewById(R.id.attendence1);
        Attendence=findViewById(R.id.attendence2);
        Rooms=findViewById(R.id.student1);
        Rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RoomActivity.class));
            }
        });
        Attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AttandenceActivity.class));

            }
        });
        Mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MessActivity.class));
            }
        });
        Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Notice_Activity.class));
            }
        });
        Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Student_Request_Activity.class));
            }
        });
        AboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),About_Activity.class));
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RoleActivity.class));
            }
        });
    }

}