package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.SubredditDto
import com.guysrobot.spreddit.model.Subreddit
import com.guysrobot.spreddit.repository.SubredditRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubredditService(private val subredditRepository: SubredditRepository) {
    fun save(subredditDto: SubredditDto): SubredditDto {
        val subreddit = Subreddit(name = subredditDto.name, description = subredditDto.description)
        val save = subredditRepository.save(subreddit)
        return subredditDto.copy(id = save.id)
    }

    @Transactional(readOnly = true)
    fun getAll(): List<SubredditDto> {
        return subredditRepository.findAll().map {
            SubredditDto(
                id = it.id,
                name = it.name,
                description = it.description,
                numberOfPosts = it.posts.size
            )
        }
    }
}