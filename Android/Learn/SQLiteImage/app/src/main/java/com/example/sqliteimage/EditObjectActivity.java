package com.example.sqliteimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditObjectActivity extends AppCompatActivity {

    EditText edtNameEdit, edtDescEdit;
    ImageView imgChooseEdit;
    ImageButton ibtnCamEdit, ibtnFolderEdit;
    Button btnSaveEdit, btnCancelEdit;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_object);

        mapping();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            ID = bundle.getInt("ID", 0);
            String name = bundle.getString("name", "");
            String desc = bundle.getString("desc", "");
            byte[] image = bundle.getByteArray("image");

            edtNameEdit.setText(name);
            edtDescEdit.setText(desc);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imgChooseEdit.setImageBitmap(bitmap);
        }

        ibtnCamEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        EditObjectActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });

        ibtnFolderEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        EditObjectActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameEdit.getText().toString();
                String desc = edtDescEdit.getText().toString();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgChooseEdit.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] image = byteArray.toByteArray();
                MainActivity.database.updateData(ID, name, desc, image);

                Toast.makeText(EditObjectActivity.this, "Added new object", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditObjectActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(EditObjectActivity.this, "You do not accept permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_FOLDER);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgChooseEdit.setImageBitmap(bitmap);
        } else if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgChooseEdit.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void mapping() {
        edtNameEdit = (EditText) findViewById(R.id.edtNameEdit);
        edtDescEdit = (EditText) findViewById(R.id.edtDesEdit);
        ibtnCamEdit = (ImageButton) findViewById(R.id.ibtnCamEdit);
        ibtnFolderEdit = (ImageButton) findViewById(R.id.ibtnFolderEdit);
        imgChooseEdit = (ImageView) findViewById(R.id.imgChooseEdit);
        btnSaveEdit = (Button) findViewById(R.id.btnSaveEdit);
        btnCancelEdit = (Button) findViewById(R.id.btnCancelEdit);
    }
}