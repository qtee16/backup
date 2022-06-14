package com.example.sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Context mContext;
    Button btnAdd;
    public static Database database;
    ListView lvObject;
    static ObjectAdapter adapter;
    static ArrayList<MyObject> objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        btnAdd = (Button) findViewById(R.id.btnAdd);
        lvObject = (ListView) findViewById(R.id.lvObject);

        objectList = new ArrayList<>();
        adapter = new ObjectAdapter(this, R.layout.object_row, objectList);
        lvObject.setAdapter(adapter);

        database = new Database(this, "ControlObject.sqlite", null, 1);
        database.queryData("CREATE TABLE IF NOT EXISTS " +
                "Object (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(50), Desc VARCHAR(200), Image BLOB)");

        updateData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddObjectActivity.class));
            }
        });
    }

    private static void updateData() {
        Cursor cursor = database.getData("SELECT * FROM Object");
        objectList.clear();
        while (cursor.moveToNext()) {
            objectList.add(new MyObject(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }

    public static void showEdit(MyObject myObject) {
        Intent intent = new Intent(mContext, EditObjectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", myObject.getID());
        bundle.putString("name", myObject.getName());
        bundle.putString("desc", myObject.getDesc());
        bundle.putByteArray("image", myObject.getImage());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public static void showDelConfirm(MyObject myObject) {
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_del_confirm);

        Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        Button btnNo = (Button) dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.queryData("DELETE FROM Object WHERE Id = " + myObject.getID());
                updateData();
                Toast.makeText(mContext, "Delete " + myObject.getName(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}