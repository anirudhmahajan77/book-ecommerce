package com.book.store;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Books & Co.")
                        .contact(new Contact()
                                .name("Developer")
                                .email("anirudhmahajan18@gmail.com"))
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .description("This is a Spring Boot based Backend with PosgtreSQL intergreated using JPA," +
                                "Which supports features like Stock Management," +
                                " Author Managament and User and Admin acess also "));
    }

}
