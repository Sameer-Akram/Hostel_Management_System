package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class StudentSignIn extends AppCompatActivity {

    Button signin;
    TextView ForgetPassword;
    EditText EmailL;
    EditText PasswordL;
    Button registration;
    ProgressBar progressBar;


    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    boolean validL = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(getSupportActionBar()).hide();
        EmailL = findViewById(R.id.editText);
        PasswordL = findViewById(R.id.editText2);
        signin = findViewById(R.id.Sign_in);
        registration = findViewById(R.id.create_account);
        ForgetPassword=findViewById(R.id.create);
        progressBar=findViewById(R.id.progress_bar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressBar.setVisibility(View.INVISIBLE);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkFieldL(EmailL);
                checkFieldL(PasswordL);

                if (validL) {
                    firebaseAuth.signInWithEmailAndPassword(EmailL.getText().toString(), PasswordL.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                           progressBar.setVisibility(View.VISIBLE);
                            checkIfAdminorUser(authResult.getUser().getUid());
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(StudentSignIn.this, "Incorrect Info Try Again", Toast.LENGTH_SHORT).show();




                        }
                    });
                }

            }


        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));

            }
        });
        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),forgot_password.class));

            }
        });

    }

    private void checkIfAdminorUser(String uid) {

        DocumentReference documentReference=firebaseFirestore.collection("Users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Log.d("TAG", "onSuccess: "+documentSnapshot.getData());
                if(documentSnapshot.getString("isUser")!=null)
                {
                    if(firebaseAuth.getCurrentUser().isEmailVerified())
                    {
                        startActivity(new Intent(getApplicationContext(),Student_Profile.class));
                    }
                    else
                    {
                        Toast.makeText(StudentSignIn.this, "Plz verify your email", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(StudentSignIn.this, "Incorrect Info Try Again", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }


    public boolean checkFieldL(EditText text)
    {
        if(text.getText().toString().isEmpty())
        {
            text.setError("Fill all Fields");
            validL=false;
        }
        else
        {
            validL=true;
        }
        return  validL;

    }

    }
