package pl.pjatk.clinic.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pjatk.clinic.exception.PeselException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handlePeselException(PeselException ex) {
        return new ResponseEntity<>("PeselException: Invalid pesel", HttpStatus.BAD_REQUEST);
    }

}
