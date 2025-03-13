package ru.hpclab.hl.module1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.hpclab.hl.module1.controller", "ru.hpclab.hl.module1.service", "ru.hpclab.hl.module1.repository", "ru.hpclab.hl.module1.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
