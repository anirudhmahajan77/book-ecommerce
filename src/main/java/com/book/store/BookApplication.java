package com.book.store;

import com.book.store.Service.EmailService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;


@SpringBootApplication
@ComponentScan("com.book.store.*")
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Autowired
    EmailService service;
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
                        .description("Spring Boot based Backend with PosgtreSQL integrated using JPA," +
                                " JWT and Email Services" +
                                "Which supports features like User Management," +
                                " Admin Panel Management and a lot more."));
    }


    /*@EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws IOException {
        service.sendRegistrationEmail("anirudhmahajan18@gmail.com", "anirudh");
    }*/

}
