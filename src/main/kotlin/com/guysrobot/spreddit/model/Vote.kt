package com.guysrobot.spreddit.model

import jakarta.persistence.*

@Entity
data class Vote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val voteId: Long,
    val voteType: VoteType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: Post,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: User
)