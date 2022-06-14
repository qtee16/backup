package com.example.fragmentlistview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class FragmentList extends ListFragment {

    ProvinceAdapter adapter;
    ArrayList<Province> arrayProvince;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        arrayProvince = new ArrayList<>();
        arrayProvince.add(new Province("Hanoi", 29));
        arrayProvince.add(new Province("Thanhhoa", 36));
        arrayProvince.add(new Province("Nghean", 37));
        arrayProvince.add(new Province("Haiduong", 34));
        arrayProvince.add(new Province("Ninhbinh", 35));
        arrayProvince.add(new Province("Bacgiang", 98));

        adapter = new ProvinceAdapter(getActivity(), arrayProvince, R.layout.layout_row);

        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_list_custom, container, false);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Toast.makeText(getActivity(), arrayProvince.get(position).getName(), Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
