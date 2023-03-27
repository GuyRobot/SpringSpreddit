package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {
    fun findByToken(token: String) : Optional<RefreshToken>
    fun deleteByToken(token: String)
}
