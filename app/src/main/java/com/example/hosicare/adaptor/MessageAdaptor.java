package com.example.hosicare.adaptor;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hosicare.R;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hosicare.modals.Message;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdaptor extends RecyclerView.Adapter<MessageAdaptor.MessageHolder> {
    private ArrayList<Message> messages = new ArrayList<>();
    private String senderImg, receiverImg;
    private Context context;

    public MessageAdaptor(ArrayList<Message> messages, String senderImg, String receiverImg, Context context) {
//        if (messages == null || messages.isEmpty()) {
//            throw new IllegalArgumentException("messages list cannot be null or empty");
//        }
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.messageholder, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = messages.get(position);
        holder.txtMessage.setText(message.getContext());

        ConstraintLayout constraintLayout = holder.ccll;

        if (message.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            Glide.with(context).load(senderImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.LEFT);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.LEFT);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.RIGHT, R.id.cclayout, ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.RIGHT, R.id.profile_cardView, ConstraintSet.LEFT, 0);
            constraintSet.applyTo(constraintLayout);
        } else {
            Glide.with(context).load(receiverImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.RIGHT);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.LEFT, R.id.cclayout, ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.LEFT, R.id.profile_cardView, ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageHolder extends RecyclerView.ViewHolder {
        ConstraintLayout ccll;
        TextView txtMessage;
        ImageView profImage;

        MessageHolder(@NonNull View itemView) {
            super(itemView);
            ccll = itemView.findViewById(R.id.cclayout);
            txtMessage = itemView.findViewById(R.id.txtMessageContent);
            profImage = itemView.findViewById(R.id.small_profile_img);
        }
    }
}
