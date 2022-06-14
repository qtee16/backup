package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<String> listData = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(List<String> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_recycler_view,parent,false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_description.setText(listData.get(position));
        holder.txt_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "click " + listData.get(position), Toast.LENGTH_SHORT).show();
            }
        });

//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if(isLongClick)
//                    Toast.makeText(context, "Long Click: "+listData.get(position), Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(context, " "+listData.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txt_description;

//    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txt_description = (TextView)itemView.findViewById(R.id.txtDescription);

    }

//    public void setItemClickListener(ItemClickListener itemClickListener)
//    {
//        this.itemClickListener = itemClickListener;
//    }

    @Override
    public void onClick(View v) {
//        itemClickListener.onClick(v,getAdapterPosition(),false);
        Toast.makeText(v.getContext(), "click me", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onLongClick(View v) {
//        itemClickListener.onClick(v,getAdapterPosition(),true);
//        return true;
//    }
}