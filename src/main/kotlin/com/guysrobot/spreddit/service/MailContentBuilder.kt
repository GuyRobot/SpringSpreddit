package com.guysrobot.spreddit.service

import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

class MailContentBuilder {
    private lateinit var templateEngine: TemplateEngine

    fun build(message: String): String {
        val context = Context()
        context.setVariable("message", message)
        return templateEngine.process("mail_template", context)
    }
}