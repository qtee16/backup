package com.example.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddPersonActivity extends AppCompatActivity {

    EditText edtName, edtBirth, edtAddress;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        mapping();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String birth = edtBirth.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                if (name.isEmpty() || birth.isEmpty() || address.isEmpty()) {
                    Toast.makeText(AddPersonActivity.this, "You must enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    postData("http://qtee16.epizy.com/androidwebservice/demo.php");
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Successfully")) {
                            Toast.makeText(AddPersonActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPersonActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(AddPersonActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddPersonActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", edtName.getText().toString());
                params.put("birth", edtBirth.getText().toString());
                params.put("address", edtAddress.getText().toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void mapping() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtBirth = (EditText) findViewById(R.id.edtBirth);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }
}