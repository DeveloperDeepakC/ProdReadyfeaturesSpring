package com.deepak.prodReadyFeatures.prod_ready_features.advice;

import com.deepak.prodReadyFeatures.prod_ready_features.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
//        ApiError apiError = new ApiError("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        log.info("General exception caught: " + ex.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        log.info("Resource not found !!!!!!!");
        System.out.println("Resource not found !!!!!!!");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(BadCredentialsException ex){
        ApiError apiError= new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        log.info("Resource not found !!!!!!!");
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(JwtException ex){
        ApiError apiError= new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        log.info("Invalid JWT");
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }
}
