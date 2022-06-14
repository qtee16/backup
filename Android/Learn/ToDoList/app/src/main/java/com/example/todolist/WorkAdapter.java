package com.example.todolist;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WorkAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Work> workList;

    public WorkAdapter(MainActivity context, int layout, List<Work> workList) {
        this.context = context;
        this.layout = layout;
        this.workList = workList;
    }

    @Override
    public int getCount() {
        return workList.size();
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
        TextView txtName;
        ImageView imgDel, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
            viewHolder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Work work = workList.get(position);
        viewHolder.txtName.setText(work.getWorkName());

        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showEditDialog(work);
            }
        });

        viewHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showDeleteDialog(work);
            }
        });

        return convertView;
    }


}
