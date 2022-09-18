package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registration extends AppCompatActivity {


    EditText Name;
    EditText FatherName;
    Spinner BloodGroup;
    Spinner Category;
    Spinner Gender;
    EditText Email;
    EditText Password;
    EditText Rollno;
    EditText CNIC;
    EditText Phone;
    EditText PermeAdd;
    EditText LocalGuardian;
    Spinner Class1;
    Spinner Year;
    Spinner Branch;
    Button Upload;
    Button SignUp;
    ImageView Img1;
    boolean valid = true;


    FirebaseAuth firebaseAuth;
    FirebaseStorage firebaseStorage;
    UploadTask uploadTask;
    private Uri imageuri;
    private static final int PICK_IMAGE = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference storageReference;
    DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Img1 = findViewById(R.id.profile_image);
        Upload = findViewById(R.id.addimg);
        Name = (EditText) findViewById(R.id.et_name);
        FatherName = (EditText) findViewById(R.id.et_FatherName);
        Email = (EditText) findViewById(R.id.et_email);
        Password = (EditText) findViewById(R.id.et_mobileNo);
        Rollno = (EditText) findViewById(R.id.et_enrollNo);
        CNIC = (EditText) findViewById(R.id.et_adhar);
        Phone = (EditText) findViewById(R.id.et_whatsapp_no);
        PermeAdd = (EditText) findViewById(R.id.et_address);
        LocalGuardian = (EditText) findViewById(R.id.et_guardian);
        Class1 = (Spinner) findViewById(R.id.sp_class);
        Year = (Spinner) findViewById(R.id.sp_year);
        Branch = (Spinner) findViewById(R.id.sp_branch);
        BloodGroup = (Spinner) findViewById(R.id.sp_blood);
        Category = (Spinner) findViewById(R.id.sp_category);
        Gender = (Spinner) findViewById(R.id.sp_gender);
        SignUp = (Button) findViewById(R.id.signUp);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        storageReference = firebaseStorage.getInstance().getReference("Profile Image");


        String[] Cllass = getResources().getStringArray(R.array.Cllass);
        String[] year = getResources().getStringArray(R.array.year);
        String[] branch = getResources().getStringArray(R.array.branch);
        String[] blood = getResources().getStringArray(R.array.blood_group);
        String[] categ = getResources().getStringArray(R.array.category);
        String[] gender = getResources().getStringArray(R.array.gender);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categ);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blood);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Cllass);
        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, year);
        ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, branch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(adapter);
        Gender.setAdapter(adapter1);
        BloodGroup.setAdapter(adapter2);
        Class1.setAdapter(adapter3);
        Year.setAdapter(adapter4);
        Branch.setAdapter(adapter5);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

//        Upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent,"Select Image"),101);
//
//
//            }
//        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(Name);
                checkField(FatherName);
                checkField(Email);
                checkField(Password);
                checkField(Rollno);
                checkField(CNIC);
                checkField(Phone);
                checkField(PermeAdd);
                checkField(LocalGuardian);

                if (valid) {
                    firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(Registration.this, "Account Created Verify Your Email", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });

                    final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getfileext(imageuri));
                    uploadTask = reference.putFile(imageuri);
                    Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return reference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                documentReference = db.collection("Users").document(firebaseUser.getUid());
                                Map<String, String> userInfo = new HashMap<>();
                                userInfo.put("FullName", Name.getText().toString());
                                userInfo.put("FatherName", FatherName.getText().toString());
                                userInfo.put("Email", Email.getText().toString());
                                userInfo.put("Password", Password.getText().toString());
                                userInfo.put("RollNo", Rollno.getText().toString());
                                userInfo.put("CNIC", CNIC.getText().toString());
                                userInfo.put("PermeAdd", PermeAdd.getText().toString());
                                userInfo.put("Phone", Phone.getText().toString());
                                userInfo.put("localGuardian", LocalGuardian.getText().toString());
                                userInfo.put("Class", Class1.getSelectedItem().toString());
                                userInfo.put("Year", Year.getSelectedItem().toString());
                                userInfo.put("Branch", Branch.getSelectedItem().toString());
                                userInfo.put("BloodGroup", BloodGroup.getSelectedItem().toString());
                                userInfo.put("Category", Category.getSelectedItem().toString());
                                userInfo.put("Gender", Gender.getSelectedItem().toString());
                                userInfo.put("url", downloadUri.toString());
                                userInfo.put("isUser", "1");
                                userInfo.put("isMess", "Un-Paid");
                                userInfo.put("Att", "Absent");
                                userInfo.put("RoomB", "0 Bed");
                                userInfo.put("RoomNo","Not-Allocated");



                                documentReference.set(userInfo);

                                startActivity(new Intent(getApplicationContext(), StudentSignIn.class));


                            }

                        }
                    });
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() != null) {
            imageuri = data.getData();
            Picasso.get().load(imageuri).into(Img1);
        }
    }

    private String getfileext(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private boolean checkField(EditText text) {
        if (text.getText().toString().isEmpty()) {
            text.setError("Fill all Fields");

            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

}

