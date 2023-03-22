package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.RegisterRequest
import com.guysrobot.spreddit.model.User
import com.guysrobot.spreddit.model.VerificationToken
import com.guysrobot.spreddit.repository.UserRepository
import com.guysrobot.spreddit.repository.VerificationTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
@Component
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val verificationTokenRepository: VerificationTokenRepository
) {

    @Transactional
    fun signup(registerRequest: RegisterRequest) {
        val user = User(
            email = registerRequest.email,
            username = registerRequest.username,
            password = passwordEncoder.encode(registerRequest.password),
            created = Instant.now(),
            enabled = false
        )

        userRepository.save(user)

        val token = verify(user)
    }

    fun verify(user: User): String {
        val token = UUID.randomUUID().toString()
        val verificationToken =
            VerificationToken(token = token, user = user, expireDate = Instant.now().plus(24, ChronoUnit.HOURS))
        verificationTokenRepository.save(verificationToken)

        return token
    }
}