package com.example.fragmentorentation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentStudentInfo extends Fragment {

    TextView tvNameVal, tvBirthVal, tvAddressVal, tvEmailVal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        tvNameVal = (TextView) view.findViewById(R.id.tvNameVal);
        tvBirthVal = (TextView) view.findViewById(R.id.tvBirthVal);
        tvAddressVal = (TextView) view.findViewById(R.id.tvAddressVal);
        tvEmailVal = (TextView) view.findViewById(R.id.tvEmailVal);

        return view;
    }

    public void setInfo(Student student) {
        tvNameVal.setText(student.getName());
        tvBirthVal.setText(Integer.toString(student.getBirth()));
        tvAddressVal.setText(student.getAddress());
        tvEmailVal.setText(student.getEmail());
    }
}
