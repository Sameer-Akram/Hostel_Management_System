package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaRouter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student_Request_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserRequest>userRequestArrayList;
    AdapterRequest adapterRequest;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ItemTouchHelper itemTouchHelper;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);
        Objects.requireNonNull(getSupportActionBar()).hide();



        FirebaseAuth firebaseAuth;
        FirebaseFirestore db;

        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        userID=firebaseAuth.getCurrentUser().getUid();
        firebaseAuth=FirebaseAuth.getInstance();




        recyclerView=findViewById(R.id.recycle);
        itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        db=FirebaseFirestore.getInstance();
        userRequestArrayList=new ArrayList<UserRequest>();
        adapterRequest=new AdapterRequest(Student_Request_Activity.this,userRequestArrayList);
        recyclerView.setAdapter(adapterRequest);



        //getting data from firestore

        EventChangeListener();

    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {



            deleteCourse();

;







        }
    };

    private void EventChangeListener() {

        db.collection("Users").orderBy("FullName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error!=null)
                        {


                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges())
                        {
                            if(dc.getType() == DocumentChange.Type.ADDED);
                            {
                                userRequestArrayList.add(dc.getDocument().toObject(UserRequest.class));

                            }
                            adapterRequest.notifyDataSetChanged();


                        }
                    }
                });
    }
    private void deleteCourse() {

        db.collection("Users").orderBy("RollNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty())
                {
                    DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                    String DocumentID =documentSnapshot.getId();
                    db.collection("Users").document(DocumentID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Student_Request_Activity.this, "Deleted", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Student_Request_Activity.this, "Not Deleted", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }

}