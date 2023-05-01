package com.guysrobot.spreddit.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.Instant

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0,
    @NotBlank(message = "Username is required")
    var username: String,
    @NotBlank(message = "Password is required")
    var password: String,
    @Email
    @NotEmpty(message = "Email is required")
    var email: String,
    var created: Instant,
    var enabled: Boolean
) {
    fun copy(enabled: Boolean): User {
        return User(userId, username, password, email, created, enabled)
    }
}
