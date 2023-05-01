package com.guysrobot.spreddit.model

import jakarta.persistence.*

@Entity
class Vote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voteId: Long = 0,
    var voteType: VoteType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    var post: Post,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var user: User
)