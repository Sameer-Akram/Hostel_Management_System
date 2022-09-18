package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class RoleActivity extends AppCompatActivity {
    ImageView btn1;
    ImageView btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Objects.requireNonNull(getSupportActionBar()).hide();
        btn1= findViewById(R.id.student);
        btn2= findViewById(R.id.warden);
        btn1.setOnClickListener(view -> {
            Intent intent =new Intent(RoleActivity.this,StudentSignIn.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            Intent intent1 =new Intent(RoleActivity.this,WardenSignIn.class);
            startActivity(intent1);
        });


    }
}