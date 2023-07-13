package com.example.hosicare.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hosicare.R;
import com.example.hosicare.modals.Tips;

import java.util.ArrayList;

public class TipAdaptor extends RecyclerView.Adapter<TipAdaptor.TipHolder> {
    Context context;
    ArrayList<Tips> tips;


    public TipAdaptor(Context context, ArrayList<Tips> tips) {
        this.context = context;
        this.tips = tips;
    }

    @NonNull
    @Override
    public TipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tipholder, parent, false);
        return new TipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipHolder holder, int position) {
        holder.tiptitle.setText(tips.get(position).getTitle());
        holder.tipdetail.setText(tips.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipHolder extends RecyclerView.ViewHolder{
        private TextView tiptitle;
        private TextView tipdetail;

        public TipHolder(@NonNull View itemView) {
            super(itemView);
            tiptitle = itemView.findViewById(R.id.tipTitleID);
            tipdetail = itemView.findViewById(R.id.tipDetailID);

        }
    }
}
