package vitaliifedyk.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleNotFoundException(RuntimeException e) {
        return new ExceptionMessage(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyUsed.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionMessage handleEmailAlreadyUsedException(RuntimeException e) {
        return new ExceptionMessage(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionMessage handleMethodNotAllowed(RuntimeException e) {
        return new ExceptionMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final BindingResult bindingResult = e.getBindingResult();
        final Map<Object, Object> errorMessages = new HashMap<>();

        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ExceptionMessage(errorMessages);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionMessage handleIllegalArgumentException(RuntimeException e) {
        return new ExceptionMessage(e.getMessage());
    }
}
