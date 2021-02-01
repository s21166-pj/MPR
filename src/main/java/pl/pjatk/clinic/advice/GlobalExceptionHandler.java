package pl.pjatk.clinic.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.pjatk.clinic.exception.DoctorException;
import pl.pjatk.clinic.exception.PatientException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<Object> handlePeselException(PatientException ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorException.class)
    public ResponseEntity<Object> handlePeselException(DoctorException ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }
}
