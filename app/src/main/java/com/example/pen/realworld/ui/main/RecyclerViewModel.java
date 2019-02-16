package com.example.pen.realworld.ui.main;

import com.example.pen.realworld.model.Article;

public class RecyclerViewModel {
    private int viewType; //1 : date , 2 : article
    private Object model;

    public RecyclerViewModel(int viewType, Object model) {
        this.viewType = viewType;
        this.model = model;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public String getSlug() {
        return ((Article)model).getSlug();
    }
}
