package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.Comment
import com.guysrobot.spreddit.model.Post
import com.guysrobot.spreddit.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByPost(post: Post) : List<Comment>
    fun findByUser(post: User?): List<Comment>
}