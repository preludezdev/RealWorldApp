package com.example.pen.realworld.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Date;

//@BindingAdapter()는 데이터바인딩에서 단순 데이터 형변환으로 데이터를 뷰(xml)에 뿌리는게 불가능할 때 사용한다.
public class MyBindingAdapter {
    //바뀔때마다 계속 실행
    @BindingAdapter("ImageURI")
    public static void setImageURL(ImageView view, String uri){
        Glide.with(view.getContext())
                .load(uri)
                .into(view);
    }

    @BindingAdapter("isVisible")
    public static void setVisible(View view, boolean isVisible){
        view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, Object obj){
        if(obj instanceof Date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            view.setText(simpleDateFormat.format((Date)obj));
        }
        else{
            view.setText((String)obj);
        }
    }
}
