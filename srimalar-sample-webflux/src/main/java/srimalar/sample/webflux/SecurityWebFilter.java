package srimalar.sample.webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Component
public class SecurityWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("---------------------- FILTER IS CALLED ---------------------------- ");
        System.out.println(create(exchange.getRequest()));
        return chain.filter(exchange);
    }

    public static ObjectNode create(ServerHttpRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("request.getId : ", request.getId());
        RequestPath requestPath = request.getPath();
        node.put("requestPath.value : ", requestPath.value());
        node.put("requestPath.toString : ", requestPath.toString());
        PathContainer pathContainer = requestPath.contextPath();
        node.put("requestPath.contextPath", pathContainer.value());
        node.put("requestPath.PathContainer0", pathContainer.subPath(0).value());

        StringBuilder builder = new StringBuilder();
        requestPath.elements().forEach(new Consumer<PathContainer.Element>() {
            @Override
            public void accept(PathContainer.Element element) {
                builder.append(element.value()).append(",");
            }
        });
        if(0 < builder.length()) {
            node.put("requestPath.elements", builder.substring(0 , builder.length() - 1));
        }
        HttpHeaders headers = request.getHeaders();
        ObjectNode header = node.putObject("headers");
        headers.entrySet().forEach(new Consumer<Map.Entry<String, List<String>>>() {
            @Override
            public void accept(Map.Entry<String, List<String>> entry) {
                header.put(entry.getKey(), Arrays.toString(entry.getValue().toArray()));
            }
        });

        ObjectNode queryParams = node.putObject("queryParams");
        request.getQueryParams().forEach(new BiConsumer<String, List<String>>() {
            @Override
            public void accept(String str, List<String> strings) {
                queryParams.put(str, Arrays.toString(strings.toArray()));
            }
        });
        return node;
    }
}
