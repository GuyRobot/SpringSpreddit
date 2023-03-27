package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.RefreshToken
import com.guysrobot.spreddit.repository.RefreshTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class RefreshTokenService(private val refreshTokenRepository: RefreshTokenRepository) {
    fun generateRefreshToken(): RefreshToken {
        val refreshToken = RefreshToken(token = UUID.randomUUID().toString())

        return refreshTokenRepository.save(refreshToken)
    }

    fun validateRefreshToken(token: String) {
        refreshTokenRepository.findByToken(token).orElseThrow { SpredditException("Invalid refresh token") }
    }

    fun deleteRefreshToken(token: String) {
        refreshTokenRepository.deleteByToken(token)
    }
}