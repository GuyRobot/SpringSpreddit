package com.guysrobot.spreddit.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "Token")
data class VerificationToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val token: String,
    @OneToOne(fetch = FetchType.LAZY)
    val user: User,
    val expireDate: Instant
)