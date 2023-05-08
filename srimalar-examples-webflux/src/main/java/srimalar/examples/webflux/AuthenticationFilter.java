package srimalar.examples.webflux;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>  {

    @Override
    public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        String name = getValidAppName(request.headers().header("X-Request-ID"));
        System.out.println("Application Name : " + name);
        return next.handle(request);
    }

    private String getValidAppName(List<String> headers) {
        if(headers == null || headers.isEmpty()) {
            throw new NullPointerException("Application not registered, Please contact service provider.");
        }
        return headers.get(0);
    }
}
