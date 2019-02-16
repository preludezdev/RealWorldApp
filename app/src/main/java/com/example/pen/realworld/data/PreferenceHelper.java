package com.example.pen.realworld.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.pen.realworld.model.User;
import com.google.gson.Gson;

public class PreferenceHelper {

    private static PreferenceHelper instance;
    public static PreferenceHelper getInstance(){
        if(instance==null){
            instance = new PreferenceHelper();
        }
        return instance;
    }

    private PreferenceHelper() {

    }

    SharedPreferences pref;

    public void init(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    User currentUser;
    Gson gson = new Gson();

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void saveCurrentUser(){
        this.pref.edit().putString("currentUser",gson.toJson(this.currentUser)).apply();
    }

    public void loadCurrentUser(){
        currentUser = gson.fromJson(this.pref.getString("currentUser",""),User.class);
    }

}