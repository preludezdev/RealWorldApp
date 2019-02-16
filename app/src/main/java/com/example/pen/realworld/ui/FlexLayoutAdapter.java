package com.example.pen.realworld.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pen.realworld.R;

import java.util.ArrayList;
import java.util.List;

public class FlexLayoutAdapter extends RecyclerView.Adapter<FlexLayoutAdapter.MyViewHolder> {

    List<String> list = new ArrayList<>();
    static int count = 0;

    public void addList(List<String> newList){
        list.clear();
        list.addAll(newList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_hashtag,parent, false);

        Log.d("test","hashtag" + count++);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String currTag = list.get(position);

        holder.tvHashTag.setText("#"+currTag);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvHashTag;

        public MyViewHolder(View view){
            super(view);

            tvHashTag = view.findViewById(R.id.tvHashTag);
        }
    }
}
