package com.example.hosicare.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hosicare.R;
import com.example.hosicare.modals.Doctor;

import java.util.ArrayList;

public class DoctorAdaptor extends RecyclerView.Adapter<DoctorAdaptor.MyViewHolder> {
    Context context;
    ArrayList<Doctor> list;

    public DoctorAdaptor(Context context, ArrayList<Doctor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DoctorAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doc_holder, parent, false);
        return new DoctorAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdaptor.MyViewHolder holder, int position) {
        holder.nameTxt.setText(list.get(position).getUsername());
        holder.firstnametxt.setText(list.get(position).getFirstname());
        holder.lastnametxt.setText(list.get(position).getLastname());
        holder.specTxt.setText(list.get(position).getSpecilist());
        holder.availTxt.setText(list.get(position).getAvailability());
        holder.emailTxt.setText(list.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, firstnametxt, lastnametxt, specTxt, emailTxt, availTxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.docNameID);
            firstnametxt = itemView.findViewById(R.id.docfirstnameID);
            lastnametxt = itemView.findViewById(R.id.doclastnameID);
            specTxt = itemView.findViewById(R.id.docspeciID);
            emailTxt = itemView.findViewById(R.id.docemailID);
            availTxt = itemView.findViewById(R.id.docAvailID);

        }
    }
}
