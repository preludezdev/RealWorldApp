package com.example.pen.realworld.model

data class User(
        val user : String,
        val email : String,
        val token : String,
        val username : String,
        val bio : String,
        val image : String
)

/*
{
    "user": {
    "email": "jake@jake.jake",
    "token": "jwt.token.here",
    "username": "jake",
    "bio": "I work at statefarm",
    "image": null
}
}
 */