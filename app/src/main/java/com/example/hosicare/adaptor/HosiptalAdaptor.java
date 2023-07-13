package com.example.hosicare.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hosicare.R;
import com.example.hosicare.modals.HospitalModal;

import java.util.ArrayList;

public class HosiptalAdaptor extends RecyclerView.Adapter<HosiptalAdaptor.ViewHolder> {
    private Context context;
    private ArrayList <HospitalModal> hospital;

    public HosiptalAdaptor(Context context, ArrayList<HospitalModal> hospital) {
        this.context = context;
        this.hospital = hospital;
    }

    @NonNull
    @Override
    public HosiptalAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospitalholder, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HosiptalAdaptor.ViewHolder holder, int position) {
        holder.nametxt.setText(hospital.get(position).getName());
        holder.postofficetxt.setText(hospital.get(position).getPostoffice());
        holder.locationtxt.setText(hospital.get(position).getLocation());
        holder.leveltxt.setText(hospital.get(position).getLevel());
    }

    @Override
    public int getItemCount() {
        return hospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nametxt, postofficetxt, locationtxt, leveltxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.hospNameID);
            postofficetxt = itemView.findViewById(R.id.postofficeID);
            locationtxt = itemView.findViewById(R.id.locationID);
            leveltxt = itemView.findViewById(R.id.levelID);
        }
    }
}
