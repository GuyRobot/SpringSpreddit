package com.guysrobot.spreddit.model

import com.guysrobot.spreddit.dto.CommentDto
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import java.time.Instant

@Entity
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotEmpty
    val text: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: Post? = null,
    val createdDate: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: User? = null,
) {
    constructor(dto: CommentDto, post: Post?, user: User?) : this(
        text = dto.text,
        createdDate = dto.createdDate,
        post = post,
        user = user
    )

    fun toDto(): CommentDto {
        return CommentDto(id, post?.postId, createdDate, text, user?.username)
    }
}