package com.gerald.springDemo.student;

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
            Student gerald = new Student(
                    "Gerald",
                    "gerald@gmail.com",
                    LocalDate.of(1998, Month.FEBRUARY, 14)
            );

            Student mark = new Student(
                    "Mark",
                    "mark@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY, 14)
            );

            repository.saveAll(List.of(gerald, mark));
        };
    }
}
