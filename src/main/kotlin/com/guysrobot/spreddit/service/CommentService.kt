package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.dto.CommentDto
import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.Comment
import com.guysrobot.spreddit.model.NotificationEmail
import com.guysrobot.spreddit.model.User
import com.guysrobot.spreddit.repository.CommentRepository
import com.guysrobot.spreddit.repository.PostRepository
import com.guysrobot.spreddit.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val authService: AuthService,
    private val mailContentBuilder: MailContentBuilder,
    private val mailService: MailService
) {

    @Transactional
    fun save(commentRequest: CommentDto) {
        commentRequest.postId ?: throw SpredditException("Post id not found")
        val post = postRepository.findById(commentRequest.postId)
            .orElseThrow { SpredditException("Post not found with id: ${commentRequest.postId}") }
        val comment = Comment(commentRequest, post, user = authService.getCurrentUser())
        commentRepository.save(comment)

        val message = mailContentBuilder.build("${post.user?.username} posted a comment on your post. ${post.url}")
        post.user?.let {
            sendCommentNotification(message, post.user)
        }
    }

    private fun sendCommentNotification(message: String, user: User) {
        mailService.sendMail(NotificationEmail("${user.username} commented on your post", user.email, message))
    }

    @Transactional(readOnly = true)
    fun getAllCommentsForPost(postId: Long): List<CommentDto> {
        val post = postRepository.findById(postId).orElseThrow { SpredditException("Post not found with id: $postId") }
        return commentRepository.findByPost(post).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun getAllCommentsForUser(username: String): List<CommentDto> {
        val user = userRepository.findByUsername(username).orElseThrow { SpredditException("User not found with username: $username") }
        return commentRepository.findByUser(user).map { it.toDto() }
    }
}
