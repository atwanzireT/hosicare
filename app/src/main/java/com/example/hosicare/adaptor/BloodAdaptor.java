package com.example.hosicare.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hosicare.R;
import com.example.hosicare.modals.BloodModal;

import java.util.ArrayList;

public class BloodAdaptor extends RecyclerView.Adapter<BloodAdaptor.MyViewHolder> {
    Context context;
    ArrayList<BloodModal> list;

    public BloodAdaptor(Context context, ArrayList<BloodModal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BloodAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bloodgrp_holder, parent, false);
        return new BloodAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodAdaptor.MyViewHolder holder, int position) {
        holder.bloodgrp.setText(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bloodgrp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bloodgrp = itemView.findViewById(R.id.idbloodTxt);
        }
    }
}
