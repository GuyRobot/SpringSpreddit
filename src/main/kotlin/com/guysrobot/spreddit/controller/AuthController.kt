package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.RegisterRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @PostMapping("/signup")
    fun signup(@RequestBody registerRequest: RegisterRequest) {

    }
}