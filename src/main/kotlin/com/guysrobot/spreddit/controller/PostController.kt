package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.PostRequest
import com.guysrobot.spreddit.dto.PostResponse
import com.guysrobot.spreddit.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody postRequest: PostRequest) {
        postService.save(postRequest)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: Long): PostResponse {
        return postService.getPost(id)
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<PostResponse> {
        return postService.getAll()
    }

    @GetMapping("/subreddit/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPostsBySubreddit(@PathVariable subredditId: Long): List<PostResponse> {
        return postService.getPostsBySubreddit(subredditId)
    }

    @GetMapping("/user/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun getPostsByUser(@PathVariable username: String): List<PostResponse> {
        return postService.getPostsByUser(username)
    }
}