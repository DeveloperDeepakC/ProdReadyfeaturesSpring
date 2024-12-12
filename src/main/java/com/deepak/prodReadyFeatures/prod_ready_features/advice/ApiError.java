package com.deepak.prodReadyFeatures.prod_ready_features.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }

}
