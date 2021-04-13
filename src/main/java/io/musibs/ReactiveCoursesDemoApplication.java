package io.musibs;

import io.musibs.domains.Course;
import io.musibs.repository.CourseRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveCoursesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveCoursesDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner courseLoader(CourseRepository courseRepository) {
        return args -> {
          courseRepository.save(new Course("Spring Boot", "Spring", 4, "Sprng Boot in Practice"));
            courseRepository.save(new Course("Spring Cloud", "Spring", 4, "Sprng Boot in Practice"));
            courseRepository.save(new Course("Spring Data", "Spring", 4, "Sprng Boot in Practice"));
            courseRepository.save(new Course("Spring AOP", "Spring", 4, "Sprng Boot in Practice"));
            courseRepository.save(new Course("Spring Cloud Flow", "Spring", 4, "Sprng Boot in Practice"));
        };
    }
}
