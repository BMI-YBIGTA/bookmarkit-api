package com.bmi.bookmarkitapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookmarkitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmarkitApiApplication.class, args);
    }

}
