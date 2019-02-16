package com.example.pen.realworld.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pen.realworld.databinding.ItemArticleBinding;
import com.example.pen.realworld.databinding.ItemDateBinding;
import com.example.pen.realworld.model.Article;
import com.example.pen.realworld.model.DateVo;
import com.example.pen.realworld.ui.detail.DetailArticleActivity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecyclerViewModel> list = new ArrayList<>();
    RecyclerView.RecycledViewPool pool;

    public static final int VIEW_TYPE_DATE = 1;
    public static final int VIEW_TYPE_ARTICLE = 2;

    public RecyclerViewAdapter() {
        this.pool = new RecyclerView.RecycledViewPool();
    }

    public void setList(List<Article> newArticleList) {
        if (newArticleList.size() == 0) return;

        Date prevDate = new Date(1990,12,8);
        int prevYear = prevDate.getYear();
        int prevMonth = prevDate.getMonth();
        int prevDay = prevDate.getDay();
        int prevHours = prevDate.getHours();

        for (int i = 0; i < newArticleList.size(); i++) {
            Article currArticle = newArticleList.get(i);
            Date currDate = currArticle.getUpdatedAt();

            if (prevYear == currDate.getYear() && prevMonth == currDate.getMonth()
                    && prevDay == currDate.getDay() && prevHours == currDate.getHours()) {
                list.add(new RecyclerViewModel(VIEW_TYPE_ARTICLE, currArticle));
            } else { // 날짜가 달라진 경우
                list.add(new RecyclerViewModel(VIEW_TYPE_DATE, new DateVo(currDate))); //날짜 뷰모델 삽입.=
                list.add(new RecyclerViewModel(VIEW_TYPE_ARTICLE, currArticle));

                prevYear = currDate.getYear();
                prevMonth = currDate.getMonth();
                prevDay = currDate.getDay();
                prevHours = currDate.getHours();
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //뷰타입에 따라 뷰홀더 생성하기
        if (viewType == 1) {
            ItemDateBinding binding = ItemDateBinding.inflate(inflater, parent, false);
            DateViewHolder viewholder = new DateViewHolder(binding);
            return viewholder;
        }
        else {
            ItemArticleBinding binding = ItemArticleBinding.inflate(inflater, parent, false);
            ArticleViewHolder viewHolder = new ArticleViewHolder(binding, parent.getContext());
            viewHolder.binding.recyclerViewFx.setRecycledViewPool(pool);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewModel model = list.get(position);

        if(model.getViewType() == VIEW_TYPE_DATE){
            DateViewHolder currHolder = ((DateViewHolder)holder);
            currHolder.binding.tvDate.setText(model.getModel().toString());
        }
        else{ //VIEW_TYPE_ARTICLE
            ArticleViewHolder currHolder = ((ArticleViewHolder)holder);
            Article article = (Article)model.getModel();
            currHolder.binding.setArticle(article);

            //FlexLayout 적용
            FlexLayoutAdapter adapter = new FlexLayoutAdapter();

            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(currHolder.context);
            layoutManager.setFlexDirection(FlexDirection.ROW);
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);

            currHolder.binding.recyclerViewFx.setLayoutManager(layoutManager);
            currHolder.binding.recyclerViewFx.setAdapter(adapter);

            adapter.addList(article.getTagList());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getViewType();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ItemArticleBinding binding;

        //바인딩을 생성자에서 받는다.
        public ArticleViewHolder(final ItemArticleBinding binding, Context context) {
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
                    intent.putExtra("slug", list.get(getAdapterPosition()).getSlug());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        ItemDateBinding binding;

        public DateViewHolder(final ItemDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

