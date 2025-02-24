package com.examportal.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.examportal.server")
@EnableJpaRepositories(basePackages = "com.examportal.server.Repositories")
public class ExamPortalServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalServerApplication.class, args);
    }

}
