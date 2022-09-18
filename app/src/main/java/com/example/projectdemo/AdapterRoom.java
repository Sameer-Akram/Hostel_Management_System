package com.example.projectdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class AdapterRoom extends RecyclerView.Adapter<AdapterRoom.MyViewHolder> {

    Context context;
    ArrayList<UserRoom> userRequestArrayList;




    public AdapterRoom(Context context, ArrayList<UserRoom> userRequestArrayList) {
        this.context = context;
        this.userRequestArrayList = userRequestArrayList;
    }

    @NonNull
    @Override
    public AdapterRoom.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item_design_rooms,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRoom.MyViewHolder holder, int position) {

        UserRoom user =userRequestArrayList.get(position);
        holder.FullName.setText(user.FullName);


        String userID;



        holder.status1.setText(user.RoomB);
        holder.status.setText(user.RoomNo);
        holder.RollNo.setText(user.RollNo);



        FirebaseFirestore db;

        holder.firebaseAuth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        userID= holder.firebaseAuth.getCurrentUser().getUid();
        holder.firebaseAuth=FirebaseAuth.getInstance();


        String url1=null;
        url1=user.getUrl();
        Picasso.get().load(url1).into(holder.url);

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                user.put("RoomNo",holder.RoomNo.getText().toString());
                user.put("RoomB",holder.RoomB.getText().toString());
                db.collection("Users").orderBy("RoomNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                            String DocumentID =documentSnapshot.getId();

                            db.collection("Users").document(DocumentID).update("RoomNo",holder.RoomNo.getText().toString(),"RoomB",holder.RoomB.getText().toString() ).addOnSuccessListener(new OnSuccessListener<Void>() {
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

//        holder.btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Map<String, Object> user = new HashMap<>();
//                user.put("isMess", "Un-Paid");
//                db.collection("Users").whereEqualTo("isMess","Paid").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful() && !task.getResult().isEmpty())
//                        {
//                            DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
//                            String DocumentID =documentSnapshot.getId();
//                            db.collection("Users").document(DocumentID).update("isMess","Un-Paid").addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(context, "Not-updated", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                });
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return userRequestArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {



        ImageView url;
        TextView FullName,RollNo;
        Button btn1;
        EditText RoomB;
        EditText RoomNo;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;

        TextView status1;
        TextView status;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);


            btn1=itemView.findViewById(R.id.btn_done);
            RoomB=itemView.findViewById(R.id.occupied);
            RoomNo=itemView.findViewById(R.id.enter_room_no);
            url=itemView.findViewById(R.id.imgrm);
            FullName=itemView.findViewById(R.id.namerm);
            RollNo=itemView.findViewById(R.id.rollnmberrm);
            status=itemView.findViewById(R.id.textView10);
            status1=itemView.findViewById(R.id.textView13);





        }
    }
}
