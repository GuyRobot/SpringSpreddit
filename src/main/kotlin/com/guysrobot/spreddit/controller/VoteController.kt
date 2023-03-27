package com.guysrobot.spreddit.controller

import com.guysrobot.spreddit.dto.VoteDto
import com.guysrobot.spreddit.service.VoteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/votes")
class VoteController(private val voteService: VoteService) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun create(@RequestBody voteDto: VoteDto) {
        return voteService.save(voteDto)
    }

}