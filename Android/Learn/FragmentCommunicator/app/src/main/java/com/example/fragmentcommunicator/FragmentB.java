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

public class FragmentB extends Fragment {

    TextView tvFragB;
    EditText edtFragB;
    Button btnFragB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        tvFragB = (TextView) view.findViewById(R.id.tvFragB);
        edtFragB = (EditText) view.findViewById(R.id.edtFragB);
        btnFragB = (Button) view.findViewById(R.id.btnFragB);

        btnFragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtA = (TextView) getActivity().findViewById(R.id.tvMain);
                txtA.setText(edtFragB.getText().toString().trim());
            }
        });
        return view;
    }
}
