package com.example.pen.realworld.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pen.realworld.databinding.ItemArticleBinding;
import com.example.pen.realworld.model.Article;
import com.example.pen.realworld.ui.detail.DetailArticleActivity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Article> list = new ArrayList<>();
    RecyclerView.RecycledViewPool pool;

    public RecyclerViewAdapter(){
        this.pool = new RecyclerView.RecycledViewPool();
    }

    void setList(List<Article> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); //inflater 는 그냥 선언만.

        //실질적인 데이터바인딩 부분
        //뷰홀더가 생성될 때
        //item_article 리소스 파일과 바인딩 된 ItemArticleBinding 객체에서 inflate 한걸 바인딩 객체로 생성
        ItemArticleBinding binding = ItemArticleBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding, parent.getContext());
        viewHolder.binding.recyclerViewFx.setRecycledViewPool(pool);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = list.get(position);
        holder.binding.setArticle(article);
        //지금 바인딩이 모든 뷰를 들고있으므로 바인딩으로 대체

        //FlexLayout 적용
        FlexLayoutAdapter adapter = new FlexLayoutAdapter();

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(holder.context);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);

        holder.binding.recyclerViewFx.setLayoutManager(layoutManager);
        holder.binding.recyclerViewFx.setAdapter(adapter);

        adapter.addList(article.getTagList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        Context context;
        ItemArticleBinding binding;

        //바인딩을 생성자에서 받는다.
        public ViewHolder(final ItemArticleBinding binding, Context context) {
            //binding 객체의 가장 바깥 껍질 View(여기선 item_article 리소스의 바깥 layout) 을 리턴한다.
            super(binding.getRoot());
            this.binding = binding;
            this.context = context;
            // Intent 로 아티클 하나 보는 액티비티로 넘기기
            // slug 값을 가지고 넘어간다.
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailArticleActivity.class);
                    intent.putExtra("slug",list.get(getAdapterPosition()).getSlug());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

