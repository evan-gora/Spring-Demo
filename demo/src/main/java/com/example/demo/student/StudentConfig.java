package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student evan = new Student("Evan", "goraej@miamioh.edu", LocalDate.of(2003, Month.DECEMBER, 14));
            Student ryan = new Student("Ryan", "gorara@miamioh.edu", LocalDate.of(2002, Month.APRIL, 7));

            repository.saveAll(List.of(evan, ryan));
        };
    }
}
