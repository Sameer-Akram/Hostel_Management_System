package com.example.projectdemo;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterMess extends RecyclerView.Adapter<AdapterMess.MyViewHolder> {

    Context context;
    ArrayList<UserMess>userRequestArrayList;




    public AdapterMess(Context context, ArrayList<UserMess> userRequestArrayList) {
        this.context = context;
        this.userRequestArrayList = userRequestArrayList;
    }

    @NonNull
    @Override
    public AdapterMess.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.itemdesignmess,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMess.MyViewHolder holder, int position) {

        UserMess user =userRequestArrayList.get(position);
        holder.FullName.setText(user.FullName);


        String userID;



        holder.RollNo.setText(user.RollNo);



        FirebaseFirestore db;

        holder.firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        userID= holder.firebaseAuth.getCurrentUser().getUid();
        holder.firebaseAuth=FirebaseAuth.getInstance();


        String url1=null;
        url1=user.getUrl();
        Picasso.get().load(url1).into(holder.url);

        holder.Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                user.put("isMess", "Paid");
                db.collection("Users").whereEqualTo("isMess","Un-Paid").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                            String DocumentID =documentSnapshot.getId();

                            db.collection("Users").document(DocumentID).update("isMess","Paid").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Not-updated", Toast.LENGTH_SHORT).show();
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
                user.put("isMess", "Un-Paid");
                db.collection("Users").whereEqualTo("isMess","Paid").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                            String DocumentID =documentSnapshot.getId();
                            db.collection("Users").document(DocumentID).update("isMess","Un-Paid").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Not-updated", Toast.LENGTH_SHORT).show();
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
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            url=itemView.findViewById(R.id.imgmess);
            FullName=itemView.findViewById(R.id.namemess);

            Img1=itemView.findViewById(R.id.checkmess);
            Img2=itemView.findViewById(R.id.check2mess);
            RollNo=itemView.findViewById(R.id.rollnmbermess);


        }
    }
}
