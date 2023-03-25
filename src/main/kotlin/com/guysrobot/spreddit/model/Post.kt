package com.guysrobot.spreddit.model

import com.guysrobot.spreddit.dto.PostResponse
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import java.time.Instant


@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long = 0,
    @NotBlank(message = "Post name cannot be blank")
    val postName: String,
    val url: String?,
    @Lob
    val description: String,
    val voteCount: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: User? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    val subreddit: Subreddit? = null,
    val createdDate: Instant = Instant.now()
) {
    fun toDto(): PostResponse {
        return PostResponse(
            postId = postId,
            postName = postName,
            description = description,
            url = url,
            subredditName = subreddit?.name,
            username = user?.username
        )
    }
}