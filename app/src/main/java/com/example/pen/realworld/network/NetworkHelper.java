package com.example.pen.realworld.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//액티비티와 소통할 클래스
public class NetworkHelper {

    //싱글톤 패턴
    private static NetworkHelper instance = null;
    public static NetworkHelper getInstance(){
        if(instance == null) instance = new NetworkHelper();
        return instance;
    }

    //레트로핏 생성
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://conduit.productionready.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //서비스 생성
    public ArticleService articleService = retrofit.create(ArticleService.class);
    public AuthService authService = retrofit.create(AuthService.class);
}
