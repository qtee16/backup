package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;

    ListView lvWork;
    ArrayList<Work> arrayWork;
    WorkAdapter workAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        lvWork = (ListView) findViewById(R.id.lvWork);
        arrayWork = new ArrayList<>();

        workAdapter = new WorkAdapter(this, R.layout.layout_row, arrayWork);
        lvWork.setAdapter(workAdapter);

        database = new Database(this, "ToDoList.sqlite", null, 1);

        database.queryData("CREATE TABLE IF NOT EXISTS " +
                "Work(Id INTEGER PRIMARY KEY AUTOINCREMENT, WorkName VARCHAR(200))");

//        database.queryData("INSERT INTO Work " +
//                "VALUES (null, 'Do homework 2')");

        loadData();

    }

    private void loadData() {
        Cursor data = database.getData("SELECT * FROM Work");
        arrayWork.clear();
        while (data.moveToNext()) {
            String ten = data.getString(1);
            int id = data.getInt(0);
            arrayWork.add(new Work(id, ten));
        }

        workAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_work, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.addMenu) {
            showAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog() {
        Dialog dialogAdd = new Dialog(this);
        dialogAdd.setContentView(R.layout.dialog_add_work);

        EditText edtName = (EditText) dialogAdd.findViewById(R.id.edtEnterName);
        Button btnAdd = (Button) dialogAdd.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) dialogAdd.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Must enter work name!", Toast.LENGTH_SHORT).show();
                } else {
                    database.queryData("INSERT INTO Work " +
                        "VALUES (null, '" + name +"')");
                    Toast.makeText(MainActivity.this, "You added " + name, Toast.LENGTH_SHORT).show();
                    dialogAdd.dismiss();
                    loadData();
                }
            }
        });
        dialogAdd.show();
    }

    public void showEditDialog(Work work) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_work);
        
        EditText edtName = (EditText) dialog.findViewById(R.id.edtEnterNameEdit);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAddEdit);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelEdit);

        edtName.setText(work.getWorkName());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Must enter work name!", Toast.LENGTH_SHORT).show();
                } else {
                    database.queryData("UPDATE Work SET WorkName = '"+name+"' WHERE Id = "+work.getWorkID());
                    Toast.makeText(MainActivity.this, "You added " + name, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadData();
                }
            }
        });
        dialog.show();
    }

    public void showDeleteDialog (Work work) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirm_del);

        Button btnYes = (Button) dialog.findViewById(R.id.btnYesDel);
        Button btnNo  = (Button) dialog.findViewById(R.id.btnNoDel);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.queryData("DELETE FROM Work WHERE Id = " + work.getWorkID());
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Delete " + work.getWorkName(), Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        dialog.show();
    }
}