package com.guysrobot.spreddit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class SpredditApplication

fun main(args: Array<String>) {
	runApplication<SpredditApplication>(*args)
}
