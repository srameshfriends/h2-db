package srimalar.examples.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.EntityResponse;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class SampleRouter {
    @Bean
    public RouterFunction<EntityResponse<Sample>> sampleRoute(SampleHandler handler) {
        return RouterFunctions
                .route(GET("/sample/{name}"), handler::getName);
               // .filter(new ExampleHandlerFilterFunction());
    }

    @Bean
    public RouterFunction<EntityResponse<Student>> sampleStudents(SampleHandler handler) {
        return RouterFunctions
                .route(GET("/sample/student/{name}"), handler::getStudents);
        // .filter(new ExampleHandlerFilterFunction());
    }

    @Bean
    public RouterFunction<ServerResponse> getSamples(SampleHandler handler) {
        return RouterFunctions.route(GET("/samples"), handler::fetchAll);
        // .filter(new ExampleHandlerFilterFunction());
    }

    @Bean
    public RouterFunction<ServerResponse> getSampleErrors(SampleHandler handler) {
        return RouterFunctions.route(GET("/sample/error/list"), handler::errors);
        // .filter(new ExampleHandlerFilterFunction());
    }
}
