package com.term_train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.term_train")
public class TermTrainApplication {
    public static void main(String[] args) {
        SpringApplication.run(TermTrainApplication.class, args);
    }
}
