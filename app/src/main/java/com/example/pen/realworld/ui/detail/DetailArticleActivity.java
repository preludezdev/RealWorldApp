package com.example.pen.realworld.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pen.realworld.R;
import com.example.pen.realworld.databinding.ActivityDetailArticleBinding;
import com.example.pen.realworld.model.Article;

public class DetailArticleActivity extends AppCompatActivity {

    ActivityDetailArticleBinding binding;
    public Article article;
    DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail_article);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_article);

        Intent intent = getIntent();
        String slug = intent.getStringExtra("slug");

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.setSlug(slug);
        viewModel.fetch();

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}
