package com.example.pen.realworld.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pen.realworld.R;
import com.example.pen.realworld.databinding.ActivityMainBinding;
import com.example.pen.realworld.model.Article;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        //To make it work, we also need to tell binding class who’s the LifecycleOwner.
        binding.setLifecycleOwner(this);
    }

    //livedata 에서 데이터가 바뀌면 자동으로 호출되는 함수
    @BindingAdapter("items")
    public static void setItems(RecyclerView view, List<Article> articles){
        RecyclerViewAdapter adapter;

        if (view.getAdapter() == null){
            adapter = new RecyclerViewAdapter();
            view.setAdapter(adapter);
        }
        else{
            adapter = (RecyclerViewAdapter) view.getAdapter();
        }

        //리사이클러뷰 구분선 추가
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                layoutManager.getOrientation());
        view.addItemDecoration(dividerItemDecoration);

        layoutManager.setRecycleChildrenOnDetach(true); // 자식들도 다 디태치됨
        view.setLayoutManager(layoutManager);
        adapter.setList(articles);
    }
}
