package com.example.pen.realworld.model

import java.util.*

data class Article(
        val title: String,
        val body: String,
        val slug: String,
        val createdAt: Date,
        val updatedAt: Date,
        val tagList: List<String>,
        val description: String,
        val author: Profile,
        val favorited: Boolean,
        val favoritesCount: Int
)