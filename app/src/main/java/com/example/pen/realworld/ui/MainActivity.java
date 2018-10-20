package com.example.pen.realworld.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pen.realworld.R;
import com.example.pen.realworld.databinding.ActivityMainBinding;
import com.example.pen.realworld.model.Article;
import com.example.pen.realworld.model.Profile;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    //RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //recyclerView = findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter();
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.list.addAll(Arrays.asList(
                new Article("띠용~", "테스트 바디", "1", new Date(),
                        new Date(), Arrays.asList("tag"), "설명",
                        new Profile(
                                "최예찬", "안녕하세요", "", true
                        ),
                        false, 5
                ),new Article("띠용~", "테스트 바디", "1", new Date(),
                        new Date(), Arrays.asList("tag"), "설명",
                        new Profile(
                                "최예찬", "안녕하세요", "", true
                        ),
                        false, 5
                ),new Article("띠용~", "테스트 바디", "1", new Date(),
                        new Date(), Arrays.asList("tag"), "설명",
                        new Profile(
                                "최예찬", "안녕하세요", "", true
                        ),
                        false, 5
                ),new Article("띠용~", "테스트 바디", "1", new Date(),
                        new Date(), Arrays.asList("tag"), "설명",
                        new Profile(
                                "최예찬", "안녕하세요", "", true
                        ),
                        false, 5
                ),new Article("띠용~", "테스트 바디", "1", new Date(),
                        new Date(), Arrays.asList("tag"), "설명",
                        new Profile(
                                "최예찬", "안녕하세요", "", true
                        ),
                        false, 5
                )
        ));
    }
}
