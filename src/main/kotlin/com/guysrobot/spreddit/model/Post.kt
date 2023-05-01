package com.guysrobot.spreddit.model

import com.github.marlonlom.utilities.timeago.TimeAgo
import com.guysrobot.spreddit.dto.PostResponse
import com.guysrobot.spreddit.repository.CommentRepository
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant


@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var postId: Long = 0,
    @NotBlank(message = "Post name cannot be blank")
    var postName: String,
    var url: String?,
    @Lob
    var description: String,
    var voteCount: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var user: User? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    var subreddit: Subreddit? = null,
    var createdDate: Instant = Instant.now(),
) {
    fun toDto(commentRepository: CommentRepository): PostResponse {
        return PostResponse(
            postId = postId,
            postName = postName,
            description = description,
            url = url,
            subredditName = subreddit?.name,
            username = user?.username,
            commentCount = commentRepository.findByPost(post = this).count(),
            duration = TimeAgo.using(createdDate.toEpochMilli())
        )
    }

    fun copy(voteCount: Int): Post {
        return Post(postId, postName, url, description, voteCount, user, subreddit, createdDate)
    }
}