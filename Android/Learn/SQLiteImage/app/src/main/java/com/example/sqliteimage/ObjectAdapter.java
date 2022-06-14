package com.example.sqliteimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ObjectAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private ArrayList<MyObject> objectsList;

    public ObjectAdapter(MainActivity context, int layout, ArrayList<MyObject> objectsList) {
        this.context = context;
        this.layout = layout;
        this.objectsList = objectsList;
    }

    @Override
    public int getCount() {
        return objectsList.size();
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
        ImageView imgObject, imgDel, imgEdit;
        TextView tvName, tvDesc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.imgObject = (ImageView) convertView.findViewById(R.id.imgObject);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
            holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MyObject object = objectsList.get(position);

        holder.tvName.setText(object.getName());
        holder.tvDesc.setText(object.getDesc());

        byte[] byteImage = object.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        holder.imgObject.setImageBitmap(bitmap);

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showEdit(object);
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showDelConfirm(object);
            }
        });


        return convertView;
    }
}
