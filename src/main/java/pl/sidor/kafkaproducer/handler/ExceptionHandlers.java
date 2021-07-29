package pl.sidor.kafkaproducer.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> getException() {
        return new ResponseEntity<>("Wykryto, nieoczekiwany błąd systemu", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
