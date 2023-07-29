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

public class HospitalAdaptor extends RecyclerView.Adapter<HospitalAdaptor.ViewHolder> {
    private Context context;
    private ArrayList <HospitalModal> hospital;
    private OnHospitalListener onHospitalListener;

    public interface OnHospitalListener{
        void onHospitalClicked(int position);
    }

    public HospitalAdaptor(Context context, ArrayList<HospitalModal> hospital, OnHospitalListener onHospitalListener) {
        this.context = context;
        this.hospital = hospital;
        this.onHospitalListener = onHospitalListener;
    }

    @NonNull
    @Override
    public HospitalAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospitalholder, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HospitalAdaptor.ViewHolder holder, int position) {
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHospitalListener.onHospitalClicked(getAdapterPosition());
                }
            });
            nametxt = itemView.findViewById(R.id.hospNameID);
            postofficetxt = itemView.findViewById(R.id.postofficeID);
            locationtxt = itemView.findViewById(R.id.locationID);
            leveltxt = itemView.findViewById(R.id.levelID);
        }
    }
}
