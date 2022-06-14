package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView tvWorkName;
        ImageView imgDel, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvWorkName = (TextView) convertView.findViewById(R.id.tvWorkName);
            holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Work work = workList.get(position);
        holder.tvWorkName.setText(work.getWorkName());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showEditDialog(work);
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showConfirmDel(work);
            }
        });
        return convertView;
    }
}
