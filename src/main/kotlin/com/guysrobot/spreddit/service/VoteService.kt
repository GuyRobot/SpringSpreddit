package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.VoteDto
import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.Vote
import com.guysrobot.spreddit.model.VoteType
import com.guysrobot.spreddit.repository.PostRepository
import com.guysrobot.spreddit.repository.VoteRepository
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteRepository: VoteRepository,
    private val postRepository: PostRepository,
    private val authService: AuthService
) {
    fun save(voteDto: VoteDto) {
        val post = postRepository.findById(voteDto.postId)
            .orElseThrow { SpredditException("Post not found with id: ${voteDto.postId}") }
        val voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser())
        if (voteByPostAndUser.isPresent && voteByPostAndUser.get().voteType == voteDto.voteType) {
            throw SpredditException("You have already ${voteDto.voteType}d for this post")
        }

        postRepository.save(post.copy(voteCount = post.voteCount + if (voteDto.voteType == VoteType.UPVOTE) 1 else -1))
        voteRepository.save(Vote(voteType = voteDto.voteType, post = post, user = authService.getCurrentUser()))
    }

}
