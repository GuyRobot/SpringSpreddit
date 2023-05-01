package com.guysrobot.spreddit.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "Token")
class VerificationToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var token: String,
    @OneToOne(fetch = FetchType.LAZY)
    var user: User,
    var expireDate: Instant
)