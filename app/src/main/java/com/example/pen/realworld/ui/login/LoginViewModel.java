package com.example.pen.realworld.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.pen.realworld.data.PreferenceHelper;
import com.example.pen.realworld.network.AuthService;
import com.example.pen.realworld.network.NetworkHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    //Intent로 액티비티 넘기기 위한 조치
    public MutableLiveData<Object> navigateToMain = new MutableLiveData<>();
    public MutableLiveData<Object> navigateToRegister = new MutableLiveData<>();

    //뮤터블은 읽기 쓰기 전용
    public MutableLiveData<Boolean> isFetching; // 프로그레스바

    public LoginViewModel() {
        isFetching = new MutableLiveData<>();
        isFetching.setValue(false);
    }

    public void login(){
        NetworkHelper.getInstance()
                .authService
                .login(new AuthService.LoginRequest(
                        new AuthService.LoginInfo(email.getValue(),password.getValue())))
                .enqueue(new Callback<AuthService.UserResult>() {
                    @Override
                    public void onResponse(Call<AuthService.UserResult> call, Response<AuthService.UserResult> response) {
                        if(!response.isSuccessful()) {
                            Log.d("test","http code : " + response.code() + "login fail!!!");
                            return;
                        }

                        Log.d("test","http code : " + response.code() + "login Success!!!");
                        PreferenceHelper.getInstance().setCurrentUser(response.body().getUser());
                        PreferenceHelper.getInstance().saveCurrentUser();
                        navigateToMain.setValue(new Object());
                    }

                    @Override
                    public void onFailure(Call<AuthService.UserResult> call, Throwable t) {
                        Log.d("test","login Fail!!!");
                    }
                });
    }

    public void register(){
        navigateToRegister.setValue(new Object());
    }
}
