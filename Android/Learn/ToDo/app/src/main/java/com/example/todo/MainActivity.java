package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvWork;
    WorkAdapter adapter;
    ArrayList<Work> arrayWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWork = (ListView) findViewById(R.id.lvWork);
        arrayWork = new ArrayList<>();

        adapter = new WorkAdapter(this, R.layout.work_row, arrayWork);
        lvWork.setAdapter(adapter);

        database = new Database(this, "todo.sqlite", null, 1);

        database.queryData("CREATE TABLE IF NOT EXISTS " +
                "Work(Id INTEGER PRIMARY KEY AUTOINCREMENT, WorkName VARCHAR(200))");

//        database.queryData("INSERT INTO Work VALUES(null, 'Do homework 2')");

        loadData();

    }

    private void loadData() {
        Cursor data = database.getData("SELECT * FROM Work");
        arrayWork.clear();
        while (data.moveToNext()) {
            String name = data.getString(1);
            int id = data.getInt(0);
            arrayWork.add(new Work(id, name));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.addMenu) {
            showAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddDialog() {
        Dialog dialogAdd = new Dialog(this);
        dialogAdd.setContentView(R.layout.dialog_add_work);

        EditText edtEnterName = (EditText) dialogAdd.findViewById(R.id.edtEnterName);
        Button btnAdd = (Button) dialogAdd.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) dialogAdd.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWork = edtEnterName.getText().toString();

                if (newWork.equals("")) {
                    Toast.makeText(MainActivity.this, "Must enter new work!", Toast.LENGTH_SHORT).show();
                } else {
                    database.queryData("INSERT INTO Work VALUES(null, '"+newWork+"')");
                    dialogAdd.dismiss();
                    Toast.makeText(MainActivity.this, "Add " + newWork, Toast.LENGTH_SHORT).show();
                    loadData();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.dismiss();
            }
        });
        dialogAdd.show();
    }

    public void showEditDialog(Work work) {
        Dialog dialogEdit = new Dialog(this);
        dialogEdit.setContentView(R.layout.dialog_edit_work);

        EditText edtEnterNameEdit = (EditText) dialogEdit.findViewById(R.id.edtEnterNameEdit);
        Button btnEdit = (Button) dialogEdit.findViewById(R.id.btnEdit);
        Button btnCancelEdit = (Button) dialogEdit.findViewById(R.id.btnCancelEdit);

        edtEnterNameEdit.setText(work.getWorkName());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editWork = edtEnterNameEdit.getText().toString();
                if (editWork.equals("")) {
                    Toast.makeText(MainActivity.this, "Must enter new work!", Toast.LENGTH_SHORT).show();
                } else {
                    database.queryData("UPDATE Work SET WorkName = '"+editWork+"' WHERE Id = " + work.getWorkID());
                    dialogEdit.dismiss();
                    Toast.makeText(MainActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    loadData();
                }
            }
        });

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEdit.dismiss();
            }
        });

        dialogEdit.show();
    }

    public void showConfirmDel(Work work) {
        Dialog dialogConfirm = new Dialog(this);
        dialogConfirm.setContentView(R.layout.dialog_del_work);

        Button btnYes = (Button) dialogConfirm.findViewById(R.id.btnYes);
        Button btnNo = (Button) dialogConfirm.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.queryData("DELETE FROM Work WHERE Id = " + work.getWorkID());
                dialogConfirm.dismiss();
                Toast.makeText(MainActivity.this, "Delete " + work.getWorkName(), Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm.dismiss();
            }
        });
        dialogConfirm.show();
    }
}