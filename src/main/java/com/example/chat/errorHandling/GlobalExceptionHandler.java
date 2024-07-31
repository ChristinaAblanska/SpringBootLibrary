package com.example.chat.errorHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    //    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> BusinessNotFoundHandler(Exception e) {
//        LinkedHashMap<String, String> error = new LinkedHashMap<>();
//        error.put("Timestamp: ", String.valueOf(LocalDateTime.now()));
//        error.put("Error Message: ", "Something went wrong: " + e.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(BusinessNotFound.class)
    public ResponseEntity<String> BusinessNotFoundHandler(BusinessNotFound businessNotFound) {
        return new ResponseEntity<String>(businessNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(InactiveUserException.class)
//    public ResponseEntity<String> InactiveUserExceptionHandler(InactiveUserException inactiveUserException) {
//        return new ResponseEntity<String>(inactiveUserException.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        LinkedHashMap<String, String> validationErrors = new LinkedHashMap<>();
        for (ObjectError error: errors) {
            validationErrors.put(((FieldError)error).getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
