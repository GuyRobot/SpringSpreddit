package com.guysrobot.spreddit.service

import com.guysrobot.spreddit.exception.SpredditException
import com.guysrobot.spreddit.model.NotificationEmail
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class MailService(
    private val mailSender: JavaMailSender,
    private val mailContentBuilder: MailContentBuilder
) {
    @Async
    fun sendMail(notificationEmail: NotificationEmail) {
        val messagePreparator = MimeMessagePreparator { mimeMessage ->
            val messageHelper = MimeMessageHelper(mimeMessage)
            messageHelper.setFrom("bluexker@gmail.com")
            messageHelper.setTo(notificationEmail.recipient)
            messageHelper.setSubject(notificationEmail.subject)
            messageHelper.setText(mailContentBuilder.build(notificationEmail.message))
        }

        try {
            mailSender.send(messagePreparator)
            org.slf4j.LoggerFactory.getLogger(MailService::class.java.name).info("Activation email sent")
        } catch (e: Exception) {
            e.printStackTrace()
            throw SpredditException("An error occurred when sending email to ${notificationEmail.recipient}: ${e.message}")
        }
    }
}