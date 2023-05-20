package com.guysrobot.spreddit.dto

data class PostRequest(
    val subredditName: String,
    val postName: String,
    val url: String,
    val description: String
)
