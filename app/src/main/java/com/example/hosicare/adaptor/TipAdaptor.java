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

public class TipAdaptor extends RecyclerView.Adapter<TipAdaptor.TipHolder>{
    private ArrayList<Tips> tips;
    private Context context;
    private OnTipClickListener onTipClickListener;

    public interface OnTipClickListener{
        void onTipClicked(int position);
    }

    public TipAdaptor(ArrayList<Tips> tips, Context context) {
        this.tips = tips;
        this.context = context;
    }

    @NonNull
    @Override
    public TipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tipholder, parent, false);
        return new TipAdaptor.TipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipHolder holder, int position) {
        holder.titletxt.setText(tips.get(position).getTitle());
        holder.detailtxt.setText(tips.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    class TipHolder extends RecyclerView.ViewHolder{
        TextView titletxt, detailtxt;

        public TipHolder(@NonNull View itemView) {
            super(itemView);
            titletxt = itemView.findViewById(R.id.titleField);
            detailtxt = itemView.findViewById(R.id.detailField);
        }
    }
}
