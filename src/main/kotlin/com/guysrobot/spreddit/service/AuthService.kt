package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.RegisterRequest
import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.NotificationEmail
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
    private val verificationTokenRepository: VerificationTokenRepository,
    private val mailService: MailService
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

        mailService.sendMail(NotificationEmail("Please activate your account", user.email, "Thank you for signing up for Spreddit, please click on the link below to activate your account: http://localhost:8080/api/auth/accountVertification/$token"))
    }

    fun verify(user: User): String {
        val token = UUID.randomUUID().toString()
        val verificationToken =
            VerificationToken(token = token, user = user, expireDate = Instant.now().plus(24, ChronoUnit.HOURS))
        verificationTokenRepository.save(verificationToken)

        return token
    }

    fun verifyAccount(token: String) {
        val verificationToken = verificationTokenRepository.findByToken(token)
        verificationToken.orElseThrow { SpredditException("Invalid token") }
        fetchUserAndEnable(verificationToken.get())
    }

    @Transactional
    fun fetchUserAndEnable(verificationToken: VerificationToken) {
        val user = userRepository.findByUsername(verificationToken.user.username).orElseThrow { SpredditException("User not found with ${verificationToken.user.username}") }
        userRepository.save(user.copy(enabled = true))
    }
}