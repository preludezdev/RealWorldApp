package com.example.pen.realworld.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.pen.realworld.databinding.ItemArticleBinding;
import com.example.pen.realworld.model.Article;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    List<Article> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemArticleBinding binding = ItemArticleBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = list.get(position);
        holder.binding.setArticle(article);

//        holder.tvName.setText(article.getName());
//        holder.tvDate.setText(article.getDate());
//        holder.tvTitle.setText(article.getTitle());
//        holder.tvContent.setText(article.getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ItemArticleBinding binding;

        public ViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}

