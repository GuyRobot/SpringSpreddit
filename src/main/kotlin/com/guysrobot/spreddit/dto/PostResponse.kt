package com.guysrobot.spreddit.dto

data class PostResponse(
    val postId: Long,
    val postName: String,
    val description: String,
    val url: String?,
    val subredditName: String?,
    val username: String?
)