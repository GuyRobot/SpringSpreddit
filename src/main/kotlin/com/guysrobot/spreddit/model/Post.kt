package com.guysrobot.spreddit.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import java.time.Instant


@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long,
    @NotBlank(message = "Post name cannot be blank")
    val postName: String,
    val url: String?,
    @Lob
    val description: String?,
    val voteCount: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    val subreddit: Subreddit,
    val createdDate: Instant

)