package ru.volkov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.volkov.controller"))
                .paths(PathSelectors.ant("/task/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Task manager",
                "Welcome! This is an API for the task manager app. " +
                        "<br/>Also there is a simple angular+bootstrap UI here => http://gentle-mountain-78246.herokuapp.com/ " +
                        "<br/>Please notice that <b>the first request may take up to 30 seconds</b> as heroku puts the app to sleep if it receives no web traffic in a 30-minute period.",
                null,
                null,
                null,
                null,
                null,
                Collections.emptyList());
    }
}
