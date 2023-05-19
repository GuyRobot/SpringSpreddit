package com.guysrobot.spreddit.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.stereotype.Service

@Service
class JwtProvider(private val jwtEncoder: JwtEncoder, @Value("\${jwt.expiration.time}") val jwtExpirationMills: Long) {
    fun generateToken(authentication: Authentication): String {
        val user = authentication.principal as org.springframework.security.core.userdetails.User
        return generateTokenWithUsername(user.username)
    }

    fun generateTokenWithUsername(username: String): String {
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(java.time.Instant.now())
            .expiresAt(java.time.Instant.now().plusMillis(jwtExpirationMills))
            .subject(username)
            .claim("SCOPE", "ROLE_USER")
            .build()

        return jwtEncoder.encode(org.springframework.security.oauth2.jwt.JwtEncoderParameters.from(claims)).tokenValue
    }
}