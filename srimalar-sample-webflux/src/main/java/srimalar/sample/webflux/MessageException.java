package srimalar.sample.webflux;

import org.springframework.http.HttpStatus;
import srimalar.sample.webflux.model.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class MessageException extends RuntimeException {
    private final List<ErrorObject> errors;

    public MessageException() {
        super();
        errors = new ArrayList<>();
        errors.add(new ErrorObject());
    }

    public MessageException(String message) {
        super(message);
        errors = new ArrayList<>();
        errors.add(new ErrorObject());
        title(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
        errors = new ArrayList<>();
        errors.add(new ErrorObject());
        title(message);
        details(cause.getMessage());
    }

    public MessageException(Throwable cause) {
        super(cause);
        errors = new ArrayList<>();
        errors.add(new ErrorObject());
        details(cause.getMessage());
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public MessageException details(String details) {
        errors.get(0).setDetail(details);
        return MessageException.this;
    }

    public MessageException status(HttpStatus httpStatus) {
        errors.get(0).setStatus(httpStatus);
        return MessageException.this;
    }

    public MessageException code(String code) {
        errors.get(0).setCode(code);
        return MessageException.this;
    }

    public MessageException code(String code, Object... args) {
        errors.get(0).setCode(code);
        errors.get(0).setArguments(args);
        return MessageException.this;
    }

    public MessageException arguments(Object... args) {
        errors.get(0).setArguments(args);
        return MessageException.this;
    }

    public MessageException title(String title) {
        errors.get(0).setTitle(title);
        return MessageException.this;
    }
}
