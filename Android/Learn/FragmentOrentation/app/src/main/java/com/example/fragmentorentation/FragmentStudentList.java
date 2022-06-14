package com.example.fragmentorentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class FragmentStudentList extends ListFragment {

    StudentAdapter adapter;
    ArrayList<Student> arrayStudent;
    iStudentInfo dataStudent;
    MainActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        arrayStudent = new ArrayList<>();
        addArray();
        adapter = new StudentAdapter(getActivity(), R.layout.row_student, arrayStudent);
        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_list_student, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        context = (MainActivity) getActivity();
        if (context.fragmentInfo != null) {
            context.fragmentInfo.setVisibility(View.VISIBLE);
        }
        dataStudent = (iStudentInfo) getActivity();
        dataStudent.getInfo(arrayStudent.get(position), context);
    }

    private void addArray() {
        arrayStudent.add(new Student("Tran Kim Quoc Thang", 2001, "Thanh Hoa", "thangsv01@gmail.com"));
        arrayStudent.add(new Student("Nguyen Van A", 2003, "Ha Noi", "nva@gmail.com"));
        arrayStudent.add(new Student("Le Thi B", 2000, "Ha Nam", "ltb@gmail.com"));
        arrayStudent.add(new Student("Tran Van C", 2004, "Bac Ninh", "tvc@gmail.com"));
        arrayStudent.add(new Student("Pham Thi D", 2003, "Quang Ninh", "ptd@gmail.com"));
        arrayStudent.add(new Student("Truong Van E", 2007, "Ninh Binh", "tve@gmail.com"));
        arrayStudent.add(new Student("Nguyen Thi F", 2005, "Hai Duong", "ntd@gmail.com"));
    }

}
