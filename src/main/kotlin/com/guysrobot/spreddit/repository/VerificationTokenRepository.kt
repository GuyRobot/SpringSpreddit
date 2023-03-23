package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.User
import com.guysrobot.spreddit.model.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {
    fun findByToken(token: String) : Optional<VerificationToken>
}