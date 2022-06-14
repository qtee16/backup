package com.example.chatwithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.chatwithme.Adapter.ChatAdapter;
import com.example.chatwithme.Adapter.GroupChatAdapter;
import com.example.chatwithme.Model.Message;
import com.example.chatwithme.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding bd;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    ArrayList<Message> arrayMsg;
    GroupChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        arrayMsg = new ArrayList<>();
        adapter = new GroupChatAdapter(arrayMsg, this, mAuth.getUid());

        String senderId = mAuth.getUid();

        bd.rvGroupChat.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bd.rvGroupChat.setLayoutManager(layoutManager);

        database.getReference().child("Group Chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayMsg.clear();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Message msg = snap.getValue(Message.class);
                            arrayMsg.add(msg);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        bd.imgSendMsgGroupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = bd.edtEnterMsgGroupChat.getText().toString().trim();
                Message modelMsg = new Message(senderId, msg);
                modelMsg.setTimestamp(new Date().getTime());

                bd.edtEnterMsgGroupChat.setText("");

                String msgId = database.getReference().child("Group Chat")
                        .push()
                        .getKey();

                modelMsg.setCheckId(msgId);

                database.getReference().child("Group Chat")
                        .child(msgId)
                        .push()
                        .setValue(modelMsg)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });

        bd.imgBackGroupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void setStatus(String status) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(mAuth.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        myRef.updateChildren(hashMap);
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