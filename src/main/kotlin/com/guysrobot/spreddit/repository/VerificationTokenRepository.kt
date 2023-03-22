package com.guysrobot.spreddit.repository

import com.guysrobot.spreddit.model.User
import com.guysrobot.spreddit.model.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerificationTokenRepository : JpaRepository<VerificationToken, Long>