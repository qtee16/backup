package com.example.webservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    ArrayList<Person> infoList;

    public InfoAdapter(MainActivity context, int layout, ArrayList<Person> infoList) {
        this.context = context;
        this.layout = layout;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvName, tvBirthVal, tvAddressVal;
        ImageView imgDel, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvBirthVal = (TextView) convertView.findViewById(R.id.tvBirthVal);
            holder.tvAddressVal = (TextView) convertView.findViewById(R.id.tvAddressVal);
            holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person person = infoList.get(position);

        holder.tvName.setText(person.getName());
        holder.tvBirthVal.setText(Integer.toString(person.getBirth()));
        holder.tvAddressVal.setText(person.getAddress());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditPersonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", person.getID());
                bundle.putString("name", holder.tvName.getText().toString());
                bundle.putString("birth", holder.tvBirthVal.getText().toString());
                bundle.putString("address", holder.tvAddressVal.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showDelConfirm(person.getID(), person.getName());
            }
        });

        return convertView;
    }


}
