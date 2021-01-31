package pl.pjatk.clinic.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.pjatk.clinic.exception.PeselException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PeselException.class)
    public ResponseEntity<Object> handlePeselException(PeselException ex) {
        return new ResponseEntity<>("PeselException: Invalid pesel", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return new ResponseEntity<>("IllegalArgumentException: Incorrect argument/s.", HttpStatus.BAD_REQUEST);
    }

}
