package com.example.pen.realworld.ui;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.Observer;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.pen.realworld.model.Article;
import com.example.pen.realworld.network.ArticleService;
import com.example.pen.realworld.network.NetworkHelper;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel{

    //데이터가 있음
    //기본 라이브데이터는 읽기 전용
    //즉, 라이브데이터는 값을 가져오는것만 되지만
    //뮤터블은 값을 바꿀 수 있다.
    //뮤터블은 읽기 쓰기 전용
    public MutableLiveData<Boolean> isFetching; // 프로그레스바
    //데이터바인딩에서 접근할 객체는 다 퍼블릭이어아야 한다.
    public MutableLiveData<List<Article>> articleList;

    public MediatorLiveData<Integer> fetchingProgressbarVisibility;
    public MediatorLiveData<Integer> fetchingRecyclerViewVisibility;

    public MainViewModel() {
        isFetching = new MutableLiveData<>();
        isFetching.setValue(false);

        articleList = new MutableLiveData<>();

        fetchingProgressbarVisibility = new MediatorLiveData<>();
        fetchingProgressbarVisibility.addSource(isFetching, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                fetchingProgressbarVisibility.setValue(aBoolean ? View.VISIBLE : View.GONE);
            }
        });

        fetchingRecyclerViewVisibility = new MediatorLiveData<>();
        fetchingRecyclerViewVisibility.addSource(isFetching, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                fetchingRecyclerViewVisibility.setValue(aBoolean ? View.GONE : View.VISIBLE);
            }
        });

        fetch();
    }

    public void fetch(){
        isFetching.setValue(true);

        NetworkHelper.getInstance()
                .articleService
                .getArticles()
                .enqueue(new Callback<ArticleService.ArticlesResult>() {
                    @Override
                    public void onResponse(Call<ArticleService.ArticlesResult> call, Response<ArticleService.ArticlesResult> response) {
                        articleList.setValue(response.body().getArticles());
                        isFetching.setValue(false);
                    }

                    @Override
                    public void onFailure(Call<ArticleService.ArticlesResult> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("test", "articleService call failed.");
                    }
                });
    }
}
