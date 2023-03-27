package com.guysrobot.spreddit.dto

import java.time.Instant

data class AuthenticateResponse(
    val token: String,
    val username: String,
    val refreshToken: String,
    val expireAt: Instant
)