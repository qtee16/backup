package com.example.fragmentorentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Student> arrayStudent;

    public StudentAdapter(Context context, int layout, ArrayList<Student> arrayStudent) {
        this.context = context;
        this.layout = layout;
        this.arrayStudent = arrayStudent;
    }

    @Override
    public int getCount() {
        return arrayStudent.size();
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
        TextView tvName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = arrayStudent.get(position);

        viewHolder.tvName.setText(student.getName());
        return convertView;
    }

}
