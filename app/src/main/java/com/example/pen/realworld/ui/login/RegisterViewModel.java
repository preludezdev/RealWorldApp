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

public class RegisterViewModel extends ViewModel {

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    //Intent로 액티비티 넘기기 위한 조치
    public MutableLiveData<Object> navigateToMain = new MutableLiveData<>();

    //뮤터블은 읽기 쓰기 전용
    public MutableLiveData<Boolean> isFetching; // 프로그레스바

    public RegisterViewModel() {
        isFetching = new MutableLiveData<>();
        isFetching.setValue(false);

    }

    public void register(){
        isFetching.setValue(true);

        NetworkHelper.getInstance()
                .authService
                .register(
                        new AuthService.RegisterRequest(
                                new AuthService.RegisterInfo(username.getValue(),email.getValue(),password.getValue())))
                .enqueue(new Callback<AuthService.UserResult>() {
                    @Override
                    public void onResponse(Call<AuthService.UserResult> call, Response<AuthService.UserResult> response) {
                        if(!response.isSuccessful()){ //200번대인지 체크
                            Log.d("test","http code : " + response.code());
                            try {
                                Log.d("test", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        PreferenceHelper.getInstance().setCurrentUser(response.body().getUser());
                        PreferenceHelper.getInstance().saveCurrentUser();
                        navigateToMain.setValue(new Object());

                    }

                    @Override
                    public void onFailure(Call<AuthService.UserResult> call, Throwable t) {
                        t.printStackTrace();
                    }


                });
    }
}
