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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.test@abv.bg",
                    LocalDate.of(2000, Month.FEBRUARY, 10)
            );

            Student genadi = new Student(
                    "Genadi",
                    "genata@mail.bg",
                    LocalDate.of(1995, Month.JULY, 8)
            );

            studentRepository.saveAll(List.of(mariam, genadi));
        };
    }
}
