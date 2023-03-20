package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long>