package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp.MessageActivity;
import com.example.chatapp.Model.User;
import com.example.chatapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> mUsers;
    private boolean isOnline;

    public UserAdapter(Context context, List<User> mUsers, boolean isOnline) {
        this.context = context;
        this.mUsers = mUsers;
        this.isOnline = isOnline;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.tvUsername.setText(user.getUsername());

        if (user.getImageURL().equals("default")) {
            holder.imgAvatar.setImageResource(R.drawable.man);
        } else {
            Glide.with(context)
                    .load(user.getImageURL())
                    .into(holder.imgAvatar);
        }

        if (isOnline) {
            if (user.getStatus().equals("online")) {
                holder.imgUserStatus.setVisibility(View.VISIBLE);
            } else {
                holder.imgUserStatus.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.imgUserStatus.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userId", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername;
        public CircleImageView imgAvatar;
        public ImageView imgUserStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsernameUser);
            imgAvatar = itemView.findViewById(R.id.imgAvatarUser);
            imgUserStatus = itemView.findViewById(R.id.imgUserStatus);
        }
    }
}
