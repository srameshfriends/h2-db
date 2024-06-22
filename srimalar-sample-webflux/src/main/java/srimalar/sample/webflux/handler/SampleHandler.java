package srimalar.sample.webflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import srimalar.sample.webflux.model.Sample;
import srimalar.sample.webflux.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class SampleHandler {

    public Mono<EntityResponse<Sample>> getName(ServerRequest request) {
        System.out.println("getName");
        String name = request.pathVariable("name");
        return EntityResponse.fromObject(Sample.create("100", "Welcome Mr/Miss " + name)).build();
    }

    public Mono<EntityResponse<Student>> getStudents(ServerRequest request) {
        System.out.println("getStudents");
        String name = request.pathVariable("name");
        return EntityResponse.fromObject(Student.create("50", name, 5)).build();
    }

    public Mono<ServerResponse> errors(ServerRequest request) {
        System.out.println("errors produce");
        throw new NullPointerException("Student name should not be empty");
    }

    public Mono<ServerResponse>  fetchAll(ServerRequest request) {
        Flux<Sample> sampleFlux = Flux.fromIterable(getSampleMap().values().stream().toList());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(sampleFlux, Sample.class);
    }

    private static final ConcurrentHashMap<String, Sample> sampleMap = new ConcurrentHashMap<>();

    public static Map<String, Sample> getSampleMap() {
        if(sampleMap.isEmpty()) {
            List<Sample> list = new ArrayList<>();
            list.add(Sample.create("1", "Ramesh"));
            list.add(Sample.create("2", "Rajeswari"));
            list.add(Sample.create("3", "Abinesh"));
            list.add(Sample.create("4", "Kashvika"));
            list.add(Sample.create("5", "Bala"));
            list.add(Sample.create("6", "Manikandan"));
            list.add(Sample.create("7", "Velayutham"));
            list.add(Sample.create("8", "Panner"));
            list.add(Sample.create("9", "Kalayani"));
            list.add(Sample.create("10", "Malliga"));
            list.forEach(new Consumer<Sample>() {
                @Override
                public void accept(Sample sample) {
                    sampleMap.put(sample.getName(), sample);
                }
            });
        }
        return sampleMap;
    }
}
