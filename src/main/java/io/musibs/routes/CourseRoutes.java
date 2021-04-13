package io.musibs.routes;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


import io.musibs.web.CourseHandler;
import org.springframework.web.reactive.function.server.RouterFunction;

public class CourseRoutes {

    public static RouterFunction<?> routes(CourseHandler courseHandler) {
        return nest(path("/courses")
                        .and(accept(APPLICATION_JSON)),
                route(GET("/{id}"), courseHandler::getCourse)
                        .and(route(method(POST), courseHandler::createCourse))
                        .and(route(method(GET), courseHandler::getCourses))
        ).and(nest(path("/courseNames")
                                .and(accept(APPLICATION_JSON)),
                route(method(GET), courseHandler::getCourseNames)));
    }
}
