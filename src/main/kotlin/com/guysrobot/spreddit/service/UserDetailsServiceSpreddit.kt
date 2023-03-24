package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceSpreddit(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("Username not found")
        val user =
            userRepository.findByUsername(username).orElseThrow { SpredditException("User not found with $username") }
        return User(user.username, user.password, user.enabled, true, true, true, getAuthorities("USER"))
    }

    private fun getAuthorities(role: String): MutableCollection<SimpleGrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role))
    }
}