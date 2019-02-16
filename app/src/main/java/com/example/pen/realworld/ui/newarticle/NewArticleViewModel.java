package com.example.pen.realworld.ui.newarticle;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.pen.realworld.data.PreferenceHelper;
import com.example.pen.realworld.model.User;
import com.example.pen.realworld.network.ArticleService;
import com.example.pen.realworld.network.NetworkHelper;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArticleViewModel extends ViewModel {

    public MutableLiveData<String> title;
    public MutableLiveData<String> description;
    public MutableLiveData<String> body;

    //액티비티 전단계로 이동하기 위한 수단
    public MutableLiveData<Object> returnToMain;

    //에러메시지 받기 위한 수단
    public MutableLiveData<String> errorMsg;

    public NewArticleViewModel() {
        title = new MutableLiveData<>();
        description = new MutableLiveData<>();
        body = new MutableLiveData<>();
        returnToMain = new MutableLiveData<>();
        errorMsg = new MutableLiveData<>();
    }

    public void createArticle(){
        User currentUser = PreferenceHelper.getInstance().getCurrentUser();
        ArticleService.CreateArticleInfo createArticleInfo =
                new ArticleService.CreateArticleInfo(
                        title.getValue(),
                        description.getValue(),
                        body.getValue(),
                        Arrays.asList(""));

        Log.d("test", "token : " + currentUser.getToken());

        NetworkHelper.getInstance()
                .articleService
                .createArticle(currentUser.getToken(),
                        new ArticleService.CreateArticleRequest(createArticleInfo))
                .enqueue(new Callback<ArticleService.ArticleResult>() {
                    @Override
                    public void onResponse(Call<ArticleService.ArticleResult> call, Response<ArticleService.ArticleResult> response) {
                        if(!response.isSuccessful()){
                            try {
                                errorMsg.setValue(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        returnToMain.setValue(new Object()); //전 단계로 이동
                    }

                    @Override
                    public void onFailure(Call<ArticleService.ArticleResult> call, Throwable t) {
                        t.printStackTrace();
                        errorMsg.setValue("onFailure 발생.");
                    }
                });
    }
}
