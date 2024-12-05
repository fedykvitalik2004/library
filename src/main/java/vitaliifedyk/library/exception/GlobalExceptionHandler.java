package vitaliifedyk.library.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ExceptionMessage handleNotFoundException(NotFoundException e) {
        return new ExceptionMessage(e.getMessage());
    }
}
