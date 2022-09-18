package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class Student_Profile extends AppCompatActivity {

    ImageView Img1;

    TextView Name;
    TextView Email;
    TextView Mobile;
    TextView BloodGroup;
    TextView Category;
    TextView Gender;
    TextView RollNo;
    TextView CNIC;
    TextView PermAdd;
    TextView LocalGuardian;
    TextView Class1;
    TextView Year;
    TextView StdPrflExit;
    TextView Branch;
    TextView Att;
    TextView Mess;
    TextView RoomType;
    TextView RoomNO;
    String userID;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();

        RoomType=findViewById(R.id.textView14);
        RoomNO=findViewById(R.id.textView8);
        StdPrflExit=findViewById(R.id.ExitpROFILE);
        Name=findViewById(R.id.tv_username);
        Mess=findViewById(R.id.textView5);
        Email=findViewById(R.id.tv_emailaddress);
        Att=findViewById(R.id.textView7);
        Mobile=findViewById(R.id.tv_mobile_umber);
        BloodGroup=findViewById(R.id.tv_blood_group);
        Category=findViewById(R.id.tv_cast);
        Gender=findViewById(R.id.tv_gender);
        RollNo=findViewById(R.id.tv_enrollment_no);
        CNIC=findViewById(R.id.tv_aadhar_no);
        PermAdd=findViewById(R.id.tv_perma_address);
        LocalGuardian=findViewById(R.id.tv_guardian_name);
        Class1=findViewById(R.id.sp_class);
        Year=findViewById(R.id.sp_year);
        Img1=findViewById(R.id.iv_display_image);
        Branch=findViewById(R.id.sp_branch);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        userID=firebaseAuth.getCurrentUser().getUid();

        StdPrflExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RoleActivity.class));
            }
        });

        DocumentReference documentReference=firebaseFirestore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Name.setText(value.getString("FullName"));
                Email.setText(value.getString("Email"));
                Mobile.setText(value.getString("Phone"));
                BloodGroup.setText(value.getString("BloodGroup"));
                Category.setText(value.getString("Category"));
                Gender.setText(value.getString("Gender"));
                RollNo.setText(value.getString("RollNo"));
                CNIC.setText(value.getString("CNIC"));
                PermAdd.setText(value.getString("PermeAdd"));
                LocalGuardian.setText(value.getString("localGuardian"));
                Class1.setText(value.getString("Class"));
                Att.setText(value.getString("Att"));
                Year.setText(value.getString("Year"));
                Mess.setText(value.getString("isMess"));
                RoomNO.setText(value.getString("RoomNo"));
                RoomType.setText(value.getString("RoomB"));
                Branch.setText(value.getString("Branch"));
                String url=value.getString("url");

                Picasso.get().load(url).into(Img1);





            }

        });

    }
}

//    @Override
//    protected void onStart() {
//        super.onStart();
//        DocumentReference documentReference=firebaseFirestore.collection("Users").document(userID);
//       documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//           @Override
//           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//               if(task.getResult().exists())
//               {
//                   String FullName=task.getResult().getString("FullName");
//                   String Email=task.getResult().getString("Email");
//                   String Phone=task.getResult().getString("Phone");
//                   String BloodGroup=task.getResult().getString("BloodGroup");
//                   String Category=task.getResult().getString("Category");
//                   String Gender=task.getResult().getString("Gender");
//                   String RollNo=task.getResult().getString("RollNo");
//                   String CNIC=task.getResult().getString("CNIC");
//                   String PermeAdd=task.getResult().getString("PermeAdd");
//                   String localGuardian=task.getResult().getString("localGuardian");
//                   String Class=task.getResult().getString("Class");
//                   String Year=task.getResult().getString("Year");
//                   String Branch=task.getResult().getString("Branch");
//                   String url=task.getResult().getString("url");
//
//                   Picasso.get().load(url).into(ImgStdProfile);
//                   Name.setText(FullName);
//                   Email1.setText(Email);
//                   Mobile1.setText(Phone);
//                   BloodGroup1.setText(BloodGroup);
//                   Category1.setText(Category);
//                   Gender1.setText(Gender);
//                   RollNo1.setText(RollNo);
//                   CNIC1.setText(CNIC);
//                   PermAdd1.setText(PermeAdd);
//                   LocalGuardian1.setText(localGuardian);
//                   Class11.setText(Class);
//                   Year1.setText(Year);
//                   Branch1.setText(Branch);
//
//
//
//
//
//               }
//           }
//       });
//    }
