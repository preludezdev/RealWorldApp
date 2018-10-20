package com.example.pen.realworld.model

data class Profile(
        val username: String,
        val bio: String?,
        val image: String,
        val following: Boolean
)