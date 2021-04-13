package io.musibs.config;

import io.musibs.routes.CourseRoutes;
import io.musibs.web.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class AppRouteConfiguration {

    @Bean
    public RouterFunction<?> routerFunction(CourseHandler courseHandler) {
        return CourseRoutes.routes(courseHandler);
    }
}
