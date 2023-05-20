package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.PostRequest
import com.guysrobot.spreddit.dto.PostResponse
import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.Post
import com.guysrobot.spreddit.repository.CommentRepository
import com.guysrobot.spreddit.repository.PostRepository
import com.guysrobot.spreddit.repository.SubredditRepository
import com.guysrobot.spreddit.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val subredditRepository: SubredditRepository,
    private val userRepository: UserRepository,
    private val authService: AuthService
) {

    @Transactional
    fun save(postRequest: PostRequest) {
        val subreddit = subredditRepository.findByName(postRequest.subredditName)
            .orElseThrow { SpredditException("Subreddit not found with name ${postRequest.subredditName}") }
        val user = authService.getCurrentUser()
        val post = Post(
            postName = postRequest.postName,
            url = postRequest.url,
            description = postRequest.description,
            subreddit = subreddit,
            user = user
        )
        postRepository.save(post)
    }

    @Transactional(readOnly = true)
    fun getAll(): List<PostResponse> {
        return postRepository.findAll().map { it.toDto(commentRepository) }
    }

    @Transactional(readOnly = true)
    fun getPost(id: Long): PostResponse {
        return postRepository.findById(id).orElseThrow { SpredditException("Post not found with id: $id") }.toDto(commentRepository)
    }

    @Transactional(readOnly = true)
    fun getPostsBySubreddit(subredditId: Long): List<PostResponse> {
        val subreddit = subredditRepository.findById(subredditId)
            .orElseThrow { SpredditException("Subreddit not found with id: $subredditId") }
        return postRepository.findAllBySubreddit(subreddit).map { it.toDto(commentRepository) }
    }

    @Transactional(readOnly = true)
    fun getPostsByUser(username: String): List<PostResponse> {
        val user = userRepository.findByUsername(username)
            .orElseThrow { SpredditException("User not found with username: $username") }
        return postRepository.findAllByUser(user).map { it.toDto(commentRepository) }
    }
}
