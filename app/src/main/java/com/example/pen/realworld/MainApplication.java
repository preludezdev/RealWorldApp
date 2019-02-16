package com.example.pen.realworld;

import android.app.Application;

import com.example.pen.realworld.data.PreferenceHelper;

//프로젝트 실행시 제일 먼저 실행되는 부분
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.getInstance().init(this);
    }
}
