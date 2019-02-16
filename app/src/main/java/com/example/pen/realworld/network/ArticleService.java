package com.example.pen.realworld.network;

import com.example.pen.realworld.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {
    //https://conduit.productionready.io/api/ ~~~

    @GET("articles")
    Call<ArticlesResult> getArticles();

    @GET("articles/{slug}")
    Call<ArticleResult> getArticleDetail(@Path("slug") String slug);

    class ArticleResult{
        private Article article;

        public ArticleResult(Article article) {
            this.article = article;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }
    }

    class ArticlesResult{
        private List<Article> articles;
        private int articlesCount;

        public ArticlesResult(List<Article> articles, int articlesCount) {
            this.articles = articles;
            this.articlesCount = articlesCount;
        }

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }

        public int getArticlesCount() {
            return articlesCount;
        }

        public void setArticlesCount(int articlesCount) {
            this.articlesCount = articlesCount;
        }
    }
}
