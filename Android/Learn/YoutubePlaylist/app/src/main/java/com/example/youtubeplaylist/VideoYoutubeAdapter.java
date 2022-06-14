package com.example.youtubeplaylist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoYoutubeAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private ArrayList<VideoYoutube> arrayVideo;

    public VideoYoutubeAdapter(MainActivity context, int layout, ArrayList<VideoYoutube> arrayVideo) {
        this.context = context;
        this.layout = layout;
        this.arrayVideo = arrayVideo;
    }

    @Override
    public int getCount() {
        return arrayVideo.size();
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
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VideoYoutube video = arrayVideo.get(position);
        viewHolder.textView.setText(video.getTitle());
        Picasso.get().load(video.getThumbnail()).into(viewHolder.imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("video", video);
                context.sendData(bundle);
            }
        });
        return convertView;
    }
}
