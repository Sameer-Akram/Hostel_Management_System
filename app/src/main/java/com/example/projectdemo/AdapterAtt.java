package com.example.projectdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterAtt extends RecyclerView.Adapter<AdapterAtt.MyViewHolder> {

    Context context;
    ArrayList<UserAtt> userRequestArrayList;




    public AdapterAtt(Context context, ArrayList<UserAtt> userRequestArrayList) {
        this.context = context;
        this.userRequestArrayList = userRequestArrayList;
    }

    @NonNull
    @Override
    public AdapterAtt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.itemdesignattendence,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAtt.MyViewHolder holder, int position) {

        UserAtt user =userRequestArrayList.get(position);
        holder.FullName.setText(user.FullName);


        String userID;



        holder.RollNo.setText(user.RollNo);


        holder.setIsRecyclable(false);

        FirebaseFirestore db;

        holder.firebaseAuth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        userID= holder.firebaseAuth.getCurrentUser().getUid();
        holder.firebaseAuth=FirebaseAuth.getInstance();


        String url1=null;
        url1 =user.getUrl();
        Picasso.get().load(url1).into(holder.url);


        holder.Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                user.put("Att", "Present");
                db.collection("Users").whereEqualTo("Att","Absent").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                            String DocumentID =documentSnapshot.getId();
                            db.collection("Users").document(DocumentID).update("Att","Present").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Marked Present", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        holder.Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                user.put("Att", "Absent");
                db.collection("Users").whereEqualTo("Att","Present").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                            String DocumentID =documentSnapshot.getId();
                            db.collection("Users").document(DocumentID).update("Att","Absent").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Marked-Absent", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });


            }

        });

    }

    @Override
    public int getItemCount() {
        return userRequestArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {



        ImageView url;
        TextView FullName,RollNo;
        ImageView Img1;
        ImageView Img2;
        ImageView Img3;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;



        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            url=itemView.findViewById(R.id.imgatt);
            FullName=itemView.findViewById(R.id.nameatt);


            Img1=itemView.findViewById(R.id.checkatt);
            Img2=itemView.findViewById(R.id.check2att);
            RollNo=itemView.findViewById(R.id.rollnmberatt);


        }
    }
}
