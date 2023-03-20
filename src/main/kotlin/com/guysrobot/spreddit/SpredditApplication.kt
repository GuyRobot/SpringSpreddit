package com.guysrobot.spreddit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpredditApplication

fun main(args: Array<String>) {
	runApplication<SpredditApplication>(*args)
}
