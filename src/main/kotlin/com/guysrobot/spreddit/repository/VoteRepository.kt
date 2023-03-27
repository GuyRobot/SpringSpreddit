package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.Post
import com.guysrobot.spreddit.model.Subreddit
import com.guysrobot.spreddit.model.User
import com.guysrobot.spreddit.model.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VoteRepository : JpaRepository<Vote, Long> {
    fun findTopByPostAndUserOrderByVoteIdDesc(post: Post, currentUser: User): Optional<Vote>
}