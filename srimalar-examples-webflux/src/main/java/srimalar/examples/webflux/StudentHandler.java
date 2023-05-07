package srimalar.examples.webflux;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class StudentHandler {
    private final StudentService studentService;

    public StudentHandler(StudentService studentService) {
        this.studentService = studentService;
    }

    public Mono<ServerResponse> listStudents(ServerRequest serverRequest) {
        System.out.println("ALL STUDENTS");
        Flux<Student> studentFlux = Flux.fromIterable(studentService.getStudentList().stream().toList());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentFlux, Student.class);
    }

    public Mono<ServerResponse> getStudent(ServerRequest serverRequest) {
        String studentId = serverRequest.pathVariable("id");
        System.out.println(studentId + " GET STUDENT FROM ID");
        Student student = studentService.findStudentById(studentId);
        return student == null ? ServerResponse.notFound().build() : ServerResponse.ok().bodyValue(student);
    }

    public Mono<ServerResponse> addNewStudent(ServerRequest serverRequest) {
        Mono<Student> studentMono = serverRequest.bodyToMono(Student.class);
        return studentMono.flatMap(student ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(APPLICATION_JSON)
                        .body(studentService.addNewStudent(student), Student.class));
    }

    public Mono<ServerResponse> updateStudent(ServerRequest serverRequest) {
        String studentId = serverRequest.pathVariable("id");
        System.out.println(studentId + " UPDATE STUDENT FROM ID");
        Mono<Student> studentMono = serverRequest.body(BodyExtractors.toMono(Student.class));
        Optional<Student> optional = studentMono.blockOptional();
        Student student = optional.orElse(null);
        if(student == null) {
            return ServerResponse.notFound().build();
        }
        Student result = studentService.updateStudent(studentId, student);
        return ServerResponse.status(HttpStatus.OK)
                        .contentType(APPLICATION_JSON)
                        .body(result, Student.class);
    }

    public Mono<ServerResponse> deleteStudent(ServerRequest serverRequest) {
        String studentId = serverRequest.pathVariable("id");
        System.out.println(studentId + " DELETE STUDENT BY ID");
        Student result = studentService.deleteStudentById(studentId);
        if(result == null) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(result, Student.class);
    }
}
