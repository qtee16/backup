package com.example.nodejssocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;

    ListView lvUser, lvChat;
    EditText edtEnter;
    ImageView imgAddUser, imgSend;
    ArrayList<String> arrayUser, arrayMsg;
    ArrayAdapter adapter, adapterMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        arrayUser = new ArrayList<>();
        arrayMsg = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayUser);
        adapterMsg = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayMsg);
        lvUser.setAdapter(adapter);
        lvChat.setAdapter(adapterMsg);

        try {
            mSocket = IO.socket("http://192.168.1.11:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.on("server-send-result", onRetrieveData);
        mSocket.on("server-send-user", onListUser);
        mSocket.on("server-send-msg", onReceiveMsg);

        imgAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtEnter.getText().toString().trim();
                if (name.length() > 0) {
                    mSocket.emit("client-register-user", name);
                }
            }
        });
        
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtEnter.getText().toString().trim();
                if (msg != null) {
                    mSocket.emit("client-send-msg", msg);
                }
            }
        });
    }

    private void mapping() {
        lvUser = (ListView) findViewById(R.id.lvUser);
        lvChat = (ListView) findViewById(R.id.lvChat);
        edtEnter = (EditText) findViewById(R.id.edtEnter);
        imgAddUser = (ImageView) findViewById(R.id.imgAddUser);
        imgSend = (ImageView) findViewById(R.id.imgSend);
    }

    private Emitter.Listener onReceiveMsg = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        String msg = object.getString("msg");
                        arrayMsg.add(msg);
                        adapterMsg.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onListUser = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        JSONArray listUser = object.getJSONArray("listUser");
                        arrayUser.clear();
                        for (int i = 0; i < listUser.length(); i++) {
                            String name = listUser.getString(i);
                            arrayUser.add(name);
                        }
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    private Emitter.Listener onRetrieveData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        boolean check = object.getBoolean("exist");
                        if (check) {
                            Toast.makeText(MainActivity.this, "Tai khoan da ton tai", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}