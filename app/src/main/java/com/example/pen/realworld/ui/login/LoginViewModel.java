package com.example.pen.realworld.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.pen.realworld.data.PreferenceHelper;
import com.example.pen.realworld.network.AuthService;
import com.example.pen.realworld.network.NetworkHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    //라이브데이터는 읽기 전용
    //뮤터블은 읽기 쓰기 전용
    //라이브데이터는 값을 가져오는것만 되지만 뮤터블은 값을 바꿀 수 있다.
    public MutableLiveData<String> email;
    public MutableLiveData<String> password;

    //Intent로 액티비티 넘기기 위한 객체(로그인 액티비티에서 observe 중)
    public MutableLiveData<Object> navigateToMain;
    public MutableLiveData<Object> navigateToRegister;

    //뮤터블은 읽기 쓰기 전용
    public MutableLiveData<Boolean> isFetching; // 프로그레스바

    //에러메시지 띄우기 전용
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public LoginViewModel() {
        email = new MutableLiveData<>();
        password = new MutableLiveData<>();
        navigateToMain = new MutableLiveData<>();
        navigateToRegister = new MutableLiveData<>();
        isFetching = new MutableLiveData<>();

        isFetching.setValue(false);
    }

    public void login(){
        NetworkHelper.getInstance()
                .authService
                .login(new AuthService.LoginRequest(
                        new AuthService.LoginInfo(email.getValue().trim(),password.getValue().trim())))
                .enqueue(new Callback<AuthService.UserResult>() {
                    @Override
                    public void onResponse(Call<AuthService.UserResult> call, Response<AuthService.UserResult> response) {
                        if(!response.isSuccessful()) {
                            try {
                                errorMsg.setValue(response.errorBody().string());
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            return;
                        }

                        PreferenceHelper.getInstance().setCurrentUser(response.body().getUser());
                        PreferenceHelper.getInstance().saveCurrentUser();
                        navigateToMain.setValue(new Object()); //MainActivity로 이동
                        //데이터가 바뀌면 navigateToMain 을 Observer 하고 있던 로그인액티비티에서 해당 로직을 실행한다.
                    }

                    @Override
                    public void onFailure(Call<AuthService.UserResult> call, Throwable t) {
                        t.printStackTrace();
                        errorMsg.setValue("onFailure 발생");
                    }
                });
    }

    public void register(){
        navigateToRegister.setValue(new Object()); //RegisterActivity로 이동
    }
}
