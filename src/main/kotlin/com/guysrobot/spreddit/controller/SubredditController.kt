package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.SubredditDto
import com.guysrobot.spreddit.service.SubredditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subreddit")
class SubredditController(private val subredditService: SubredditService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody subredditDto: SubredditDto): ResponseEntity<SubredditDto> {
        val res = subredditService.save(subredditDto)
        return ResponseEntity(res, HttpStatus.CREATED)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getSubreddits(): ResponseEntity<List<SubredditDto>> {
        return ResponseEntity(subredditService.getAll(), HttpStatus.OK)
    }
}