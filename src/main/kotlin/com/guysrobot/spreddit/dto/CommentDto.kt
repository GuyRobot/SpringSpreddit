package com.guysrobot.spreddit.dto

import java.time.Instant

data class CommentDto(
    val id: Long = 0,
    val postId: Long?,
    val createdDate: Instant = Instant.now(),
    val text: String,
    val userName: String?
)
