package com.example.pen.realworld.network;

import com.example.pen.realworld.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {
    //https://conduit.productionready.io/api/ ~~~

    @GET("articles")
    Call<ArticlesResult> getArticles();

    @GET("articles/{slug}")
    Call<ArticleResult> getArticleDetail(@Path("slug") String slug);

    // @Header("Authorization") String token
    // 넣을때 Token ${token}
    @POST("articles")
    Call<ArticleResult> createArticle(@Header("Authorization") String token,
                                      @Body CreateArticleRequest article);

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

    class CreateArticleRequest{
        private CreateArticleInfo article;

        public CreateArticleRequest(CreateArticleInfo createArticleInfo) {
            this.article = createArticleInfo;
        }

        public CreateArticleInfo getCreateArticleInfo() {
            return article;
        }

        public void setCreateArticleInfo(CreateArticleInfo createArticleInfo) {
            this.article = createArticleInfo;
        }
    }

    class CreateArticleInfo{
        private String title;
        private  String description;
        private String body;
        private List<String> tagList;

        public CreateArticleInfo(String title, String description, String body, List<String> tagList) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.tagList = tagList;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<String> getTagList() {
            return tagList;
        }

        public void setTagList(List<String> tagList) {
            this.tagList = tagList;
        }
    }
}
