package srimalar.sample.webflux;

import org.springframework.http.HttpStatus;

public abstract class MessageExceptions {

    public static MessageException error(HttpStatus httpStatus) {
        return new MessageException().status(httpStatus);
    }

    public static MessageException errorCode(HttpStatus httpStatus, String code) {
        return new MessageException().status(httpStatus).code(code);
    }

    public static MessageException errorDetails(HttpStatus httpStatus, String details) {
        return new MessageException().status(httpStatus).details(details);
    }

    public static MessageException badRequest(String code) {
        return new MessageException().status(HttpStatus.BAD_REQUEST).code(code);
    }

    public static MessageException unauthorized(String code) {
        return new MessageException().status(HttpStatus.UNAUTHORIZED).code(code);
    }

    public static MessageException conflict(String code) {
        return new MessageException().status(HttpStatus.CONFLICT).code(code);
    }

    public static MessageException preconditionFailed(String code) {
        return new MessageException().status(HttpStatus.PRECONDITION_FAILED).code(code);
    }
}
