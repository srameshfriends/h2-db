package srimalar.sample.webflux;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/*@Configuration*/
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(
                request, options);
        map.forEach((str, obj) -> System.out.println(str + " \t : \t " + obj));
       /* map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", "username is required");*/
        return map;
    }
}
