package com.guysrobot.spreddit

import com.guysrobot.spreddit.config.SwaggerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig::class)
class SpredditApplication

fun main(args: Array<String>) {
	runApplication<SpredditApplication>(*args)
}
