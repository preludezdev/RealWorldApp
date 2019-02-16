package com.example.pen.realworld.ui.detail;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.pen.realworld.model.Article;
import com.example.pen.realworld.network.ArticleService;
import com.example.pen.realworld.network.NetworkHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<Article> article;
    public String slug;

    public MutableLiveData<Boolean> isFetching;
    public MediatorLiveData<Integer> fetchingProgressbarVisibility;
    public MediatorLiveData<Integer> fetchingConstraintGroupVisibility;

    public DetailViewModel(){
        article = new MutableLiveData<>();
        isFetching = new MutableLiveData<>();

        isFetching.setValue(false);

        fetchingProgressbarVisibility = new MediatorLiveData<>();
        fetchingProgressbarVisibility.addSource(isFetching, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                fetchingProgressbarVisibility.setValue(aBoolean ? View.VISIBLE : View.INVISIBLE);
            }
        });

        fetchingConstraintGroupVisibility = new MediatorLiveData<>();
        fetchingConstraintGroupVisibility.addSource(isFetching, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                fetchingConstraintGroupVisibility.setValue(aBoolean ? View.INVISIBLE : View.VISIBLE);
            }
        });

    }

    public void fetch(){
        isFetching.setValue(true);

        NetworkHelper.getInstance()
                .articleService
                .getArticleDetail(slug)
                .enqueue(new Callback<ArticleService.ArticleResult>() {
                    @Override
                    public void onResponse(Call<ArticleService.ArticleResult> call, Response<ArticleService.ArticleResult> response) {
                        article.setValue(response.body().getArticle());
                        isFetching.setValue(false);
                    }

                    @Override
                    public void onFailure(Call<ArticleService.ArticleResult> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("test", "articleDetailService call failed.");
                    }
                });
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return this.slug;
    }
}
