package com.example.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class EditPersonActivity extends AppCompatActivity {

    EditText edtNameEdit, edtBirthEdit, edtAddressEdit;
    Button btnSaveEdit, btnCancelEdit;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        mapping();
        getDataBundle();

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameEdit.getText().toString().trim();
                String birth = edtBirthEdit.getText().toString().trim();
                String address = edtAddressEdit.getText().toString().trim();
                if (name.isEmpty() || birth.isEmpty() || address.isEmpty()) {
                    Toast.makeText(EditPersonActivity.this, "You must enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    updateData("http://qtee16.epizy.com/androidwebservice/update.php");
                }
            }
        });

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void updateData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(EditPersonActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditPersonActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(EditPersonActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditPersonActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", Integer.toString(ID));
                params.put("name", edtNameEdit.getText().toString());
                params.put("birth", edtBirthEdit.getText().toString());
                params.put("address", edtAddressEdit.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getDataBundle() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ID = bundle.getInt("id");
        edtNameEdit.setText(bundle.getString("name"));
        edtBirthEdit.setText(bundle.getString("birth"));
        edtAddressEdit.setText(bundle.getString("address"));
    }

    private void mapping() {
        edtNameEdit = (EditText) findViewById(R.id.edtNameEdit);
        edtBirthEdit = (EditText) findViewById(R.id.edtBirthEdit);
        edtAddressEdit = (EditText) findViewById(R.id.edtAddressEdit);
        btnSaveEdit = (Button) findViewById(R.id.btnSaveEdit);
        btnCancelEdit = (Button) findViewById(R.id.btnCancelEdit);
    }
}