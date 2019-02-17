package com.example.pen.realworld.ui.newarticle;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pen.realworld.R;
import com.example.pen.realworld.databinding.ActivityNewArticleBinding;
import com.example.pen.realworld.ui.main.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class NewArticleActivity extends AppCompatActivity {

    ActivityNewArticleBinding binding;
    NewArticleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_article);

        viewModel = ViewModelProviders.of(this).get(NewArticleViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.returnToMain.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object o) {
                Toast.makeText(NewArticleActivity.this, "Your article is Posted.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //기존에 가고자 하는 액티비티가 스택에 쌓여있으면 해당 액티비티를 최상위로 올리고 중간에 경유한 액티비티는 다 종료
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        viewModel.errorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                Toast.makeText(NewArticleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
