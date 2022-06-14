package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chatapp.Adapter.MessageAdapter;
import com.example.chatapp.Model.Chat;
import com.example.chatapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    TextView tvUsernameMes;
    CircleImageView imgAvatarMes;
    ImageView imgUserStatusChat;

    RecyclerView rvChat;
    EditText edtMsg;
    ImageButton btnSendMsg;

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    Intent intent;

    String userId;
    MessageAdapter messageAdapter;
    List<Chat> mChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        imgAvatarMes        = findViewById(R.id.imgAvatarMes);
        tvUsernameMes       = findViewById(R.id.tvUsernameMes);
        rvChat              = findViewById(R.id.rvChat);
        edtMsg              = findViewById(R.id.edtMsg);
        btnSendMsg          = findViewById(R.id.btnSendMsg);
        imgUserStatusChat   = findViewById(R.id.imgUserStatusChat);

        rvChat.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        rvChat.setLayoutManager(linearLayoutManager);

        intent = getIntent();
        userId = intent.getStringExtra("userId");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.
                getInstance().getReference("MyUsers").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                tvUsernameMes.setText(user.getUsername());

                if (user.getStatus().equals("online")) {
                    imgUserStatusChat.setVisibility(View.VISIBLE);
                } else {
                    imgUserStatusChat.setVisibility(View.INVISIBLE);
                }
                if (user.getImageURL().equals("default")) {
                    imgAvatarMes.setImageResource(R.drawable.man);
                } else {
                    Glide.with(MessageActivity.this)
                            .load(user.getImageURL())
                            .into(imgAvatarMes);
                }


                readMessage(firebaseUser.getUid(), userId, user.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtMsg.getText().toString();
                if (msg.isEmpty()) {
                    Toast.makeText(MessageActivity.this, "Please send a non empty message!", Toast.LENGTH_SHORT).show();
                } else {
                    sendMessage(firebaseUser.getUid(), userId, msg);
                    edtMsg.setText("");
                }
            }
        });

    }

    private void sendMessage(String sender, String receiver, String msg) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("msg", msg);

        reference.child("Chats").push().setValue(hashMap);


        // Adding User to chat fragment
        final DatabaseReference myRef = FirebaseDatabase
                .getInstance().getReference("ChatList")
                .child(firebaseUser.getUid())
                .child(userId);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Date currentTime = Calendar.getInstance().getTime();
                if (!snapshot.exists()) {
                    myRef.child("id").setValue(userId);
                    myRef.child("date").setValue(currentTime.getTime());
                } else {
                    myRef.child("date").setValue(currentTime.getTime());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final DatabaseReference friendRef = FirebaseDatabase
                .getInstance().getReference("ChatList")
                .child(userId)
                .child(firebaseUser.getUid());

        friendRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Date currentTime = Calendar.getInstance().getTime();
                if (!snapshot.exists()) {
                    friendRef.child("id").setValue(firebaseUser.getUid());
                    friendRef.child("date").setValue(currentTime.getTime());
                } else {
                    friendRef.child("date").setValue(currentTime.getTime());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readMessage(String myId, String friendId, String imgURL) {
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Chat chat = snap.getValue(Chat.class);
                    if (chat.getReceiver().equals(myId) && chat.getSender().equals(friendId) ||
                    chat.getReceiver().equals(friendId) && chat.getSender().equals(myId)) {
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(MessageActivity.this, mChat, imgURL);
                    rvChat.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setStatus(String status) {
        reference = FirebaseDatabase.getInstance().getReference("MyUsers")
                .child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        reference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStatus("online");
    }



    @Override
    protected void onPause() {
        super.onPause();
        setStatus("offline");
    }
}