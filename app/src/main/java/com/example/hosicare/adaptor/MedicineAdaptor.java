package com.example.hosicare.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hosicare.R;
import com.example.hosicare.modals.MedicineModal;

import java.util.ArrayList;

public class MedicineAdaptor extends RecyclerView.Adapter<MedicineAdaptor.ViewHolder> {
    Context context;
    ArrayList<MedicineModal> list;

    public MedicineAdaptor(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MedicineAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicineholder, parent, false);
        return new MedicineAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdaptor.ViewHolder holder, int position) {
        holder.nameTxt.setText(list.get(position).getName());
        holder.ageTxt.setText((list.get(position).getAge()));
        holder.treatTxt.setText(list.get(position).getTreatment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, ageTxt, treatTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.medNameID);
            ageTxt = itemView.findViewById(R.id.medAgeID);
            treatTxt = itemView.findViewById(R.id.medTreatID);
        }
    }
}
