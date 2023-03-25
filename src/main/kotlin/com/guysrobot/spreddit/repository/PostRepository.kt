package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.Post
import com.guysrobot.spreddit.model.Subreddit
import com.guysrobot.spreddit.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findAllBySubreddit(subreddit: Subreddit) : List<Post>
    fun findAllByUser(user: User) : List<Post>
}