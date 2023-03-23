package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String) : Optional<User>
}