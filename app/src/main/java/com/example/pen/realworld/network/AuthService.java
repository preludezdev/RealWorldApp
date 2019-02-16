package com.example.pen.realworld.network;

import com.example.pen.realworld.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//로그인이랑 레지스터 네트워크 서비스
public interface AuthService {

    @POST("users/login")
    Call<UserResult> login(@Body LoginRequest loginRequest);

    @POST("users")
    Call<UserResult> register(@Body RegisterRequest registerRequest);

    //로그인
    class LoginInfo {
        private String email;
        private String password;

        public LoginInfo(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    class RegisterInfo {
        private String username;
        private String email;
        private String password;

        public RegisterInfo(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    class LoginRequest {
        private LoginInfo user;

        public LoginRequest(LoginInfo user) {
            this.user = user;
        }

        public LoginInfo getUser() {
            return user;
        }

        public void setUser(LoginInfo user) {
            this.user = user;
        }
    }

    class RegisterRequest {
        RegisterInfo user;

        public RegisterRequest(RegisterInfo user) {
            this.user = user;
        }

        public RegisterInfo getUser() {
            return user;
        }

        public void setUser(RegisterInfo user) {
            this.user = user;
        }
    }


    class UserResult{
        private User user;

        public UserResult(User user) {
            this.user = user;
        }

        public User getUser(){
            return this.user;
        }
    }
}
