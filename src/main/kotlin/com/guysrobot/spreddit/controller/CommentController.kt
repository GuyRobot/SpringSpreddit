package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.CommentDto
import com.guysrobot.spreddit.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody commentRequest: CommentDto) {
        commentService.save(commentRequest)
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCommentsForPost(@PathVariable postId: Long): List<CommentDto> {
        return commentService.getAllCommentsForPost(postId)
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCommentsForUser(@PathVariable username: String): List<CommentDto> {
        return commentService.getAllCommentsForUser(username)
    }
}