package ru.lapshina.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lapshina.exception.EntityNotFoundException;
import ru.lapshina.exception.NotFound;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<NotFound> exceptionHandler(EntityNotFoundException exception) {
        NotFound data = new NotFound();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<NotFound> exceptionHandler(Exception exception) {
        NotFound data = new NotFound();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
