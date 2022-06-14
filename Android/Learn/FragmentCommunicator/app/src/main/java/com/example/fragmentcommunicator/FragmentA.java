package com.example.fragmentcommunicator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    TextView tvFragA;
    EditText edtFragA;
    Button btnFragA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        tvFragA = (TextView) view.findViewById(R.id.tvFragA);
        edtFragA = (EditText) view.findViewById(R.id.edtFragA);
        btnFragA = (Button) view.findViewById(R.id.btnFragA);

        btnFragA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtMain = (TextView) getActivity().findViewById(R.id.tvFragB);
                txtMain.setText(edtFragA.getText().toString().trim());
            }
        });
        return view;
    }

    public void setTextFragA(String content) {
        tvFragA.setText(content);
    }
}
