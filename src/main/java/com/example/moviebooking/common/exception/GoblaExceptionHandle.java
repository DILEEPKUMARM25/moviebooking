package com.example.moviebooking.common.exception;

import com.example.moviebooking.common.resposeEntity.CommonRespose;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GoblaExceptionHandle {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CommonRespose> dataNotFound(DataNotFoundException data){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonRespose(true,data.getMessage(),null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonRespose> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new CommonRespose(true,"Validation Fails",errors) );
    }
}
