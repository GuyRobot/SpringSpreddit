package com.guysrobot.spreddit.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import java.time.Instant

@Entity
data class Subreddit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotBlank(message = "Community name is required")
    val name: String,
    @NotBlank(message = "Description is required")
    val description: String,
    @OneToMany(fetch = FetchType.LAZY)
    val posts: List<Post> = listOf(),
    val createdDate: Instant = Instant.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User? = null
)
