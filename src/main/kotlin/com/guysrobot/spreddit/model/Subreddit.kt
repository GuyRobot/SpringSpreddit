package com.guysrobot.spreddit.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
class Subreddit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @NotBlank(message = "Community name is required")
    var name: String,
    @NotBlank(message = "Description is required")
    var description: String,
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Post::class)
    var posts: List<Post> = listOf(),
    var createdDate: Instant = Instant.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null
)
