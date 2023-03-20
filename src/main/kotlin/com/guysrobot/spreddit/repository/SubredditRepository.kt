package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.Subreddit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubredditRepository : JpaRepository<Subreddit, Long>