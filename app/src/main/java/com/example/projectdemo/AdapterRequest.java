package com.example.projectdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRequest extends RecyclerView.Adapter<AdapterRequest.MyViewHolder> {

    Context context;
    ArrayList<UserRequest>userRequestArrayList;



    public AdapterRequest(Context context, ArrayList<UserRequest> userRequestArrayList) {
        this.context = context;
        this.userRequestArrayList = userRequestArrayList;
    }

    @NonNull
    @Override
    public AdapterRequest.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item_design_student_request,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRequest.MyViewHolder holder, int position) {

        UserRequest user =userRequestArrayList.get(position);
        holder.FullName.setText(user.FullName);
        holder.RollNo.setText(user.RollNo);

        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        String url1;
        url1=user.getUrl();
        Picasso.get().load(url1).into(holder.url);


        holder.Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



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

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            url=itemView.findViewById(R.id.img);
            FullName=itemView.findViewById(R.id.name);
            Img1=itemView.findViewById(R.id.check);
            Img2=itemView.findViewById(R.id.check2);
            RollNo=itemView.findViewById(R.id.rollnmber);


        }
    }

}
