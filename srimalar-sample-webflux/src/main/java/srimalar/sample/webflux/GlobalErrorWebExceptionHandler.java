package srimalar.sample.webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(-99)
public class GlobalErrorWebExceptionHandler implements WebExceptionHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        System.out.println("------------------>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(serverWebExchange.getRequest().getPath().value());
        ServerHttpResponse response = serverWebExchange.getResponse();
        MessageException messageException;
        if(throwable instanceof MessageException msgException) {
            messageException = msgException;
        } else {
            messageException = new MessageException(throwable);
        }
        String responseText = "{\"errors\":[]}";
        try {
            ObjectWriter objectWriter = mapper.writer()
                    .with(SerializationFeature.WRAP_ROOT_VALUE)
                    .withRootName("errors");
            responseText = objectWriter.writeValueAsString(messageException.getErrors());
            response.setStatusCode(messageException.getErrors().get(0).getStatus());
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        } catch (IOException ex) {
            // ignore messages
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.getHeaders().setContentType(MediaType.TEXT_HTML);
        }
        DataBuffer buffer = response.bufferFactory().wrap(responseText.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
   /* public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext) {
        super(errorAttributes, resources, applicationContext);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(
            ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
                ErrorAttributeOptions.defaults());
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }*/
