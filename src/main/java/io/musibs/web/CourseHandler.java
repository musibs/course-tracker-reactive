package io.musibs.web;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;


import io.musibs.domains.Course;
import io.musibs.repository.CourseRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Data
@Service
public class CourseHandler {

    private final CourseRepository courseRepository;

    public Mono<ServerResponse> getCourses(ServerRequest serverRequest) {
        var courses = courseRepository.findAll();
        return ServerResponse.ok().body(fromValue(courses));
    }

    public Mono<ServerResponse> getCourse(ServerRequest serverRequest) {
        var id = Long.valueOf(serverRequest.pathVariable("id"));
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.map(course -> ServerResponse.ok().body(fromValue(course)))
                .orElse(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createCourse(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Course.class)
                .map(course -> courseRepository.save(course))
                .flatMap(course -> ServerResponse.status(HttpStatus.CREATED).body(fromValue(course)));
    }

    public Mono<ServerResponse> getCourseNames(ServerRequest serverRequest) {
        return Flux.fromIterable(courseRepository.findAll()).map(course -> course.getName())
                .collectList()
                .flatMap(list -> ServerResponse.ok().body(fromValue(list)));
    }
}
