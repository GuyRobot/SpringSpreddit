package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.AuthenticateResponse
import com.guysrobot.spreddit.dto.RefreshTokenRequest
import com.guysrobot.spreddit.dto.RegisterRequest
import com.guysrobot.spreddit.dto.SigninRequest
import com.guysrobot.spreddit.service.AuthService
import com.guysrobot.spreddit.service.RefreshTokenService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService, private val refreshTokenService: RefreshTokenService) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody registerRequest: RegisterRequest): ResponseEntity<String> {
        authService.signup(registerRequest)
        return ResponseEntity("Signup successfully", HttpStatus.OK)
    }

    @GetMapping("/accountVerification/{token}")
    fun verifyAccount(@PathVariable token: String): ResponseEntity<String> {
        authService.verifyAccount(token)
        return ResponseEntity("Account activated successfully", HttpStatus.OK)
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    fun signin(@RequestBody signinRequest: SigninRequest): AuthenticateResponse {
        return authService.signin(signinRequest)
    }

    @PostMapping("/refresh/token")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): AuthenticateResponse {
        return authService.refreshToken(refreshTokenRequest)
    }

    @PostMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    fun signout(@Valid @RequestBody refreshTokenRequest: RefreshTokenRequest): String {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.refreshToken)
        return "Refresh token deleted successfully"
    }
}