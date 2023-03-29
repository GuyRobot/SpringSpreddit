package com.guysrobot.spreddit.config

import org.springframework.context.annotation.Bean
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

class SwaggerConfig {

    @Bean
    fun spredditApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis { true }
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo())
    }

    private fun getApiInfo() : ApiInfo {
        return ApiInfoBuilder()
            .title("Spreddit API")
            .version("1.0")
            .description("API for Spreddit application")
            .contact(Contact("Guys", "http://guys.com", "guys@gmail.com"))
            .license("Apache License Version 2.0")
            .build()
    }
}